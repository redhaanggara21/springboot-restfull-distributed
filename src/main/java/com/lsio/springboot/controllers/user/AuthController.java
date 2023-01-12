package com.lsio.springboot.controllers.user;

import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lsio.springboot.dto.response.ApiResponse;
import com.lsio.springboot.dto.response.JwtResponse;
import com.lsio.springboot.dto.response.UserIdentityAvailability;
import com.lsio.springboot.dto.user.LoginForm;
import com.lsio.springboot.dto.user.SignUpForm;
import com.lsio.springboot.dto.user.TokenRefreshRequest;
import com.lsio.springboot.entities.user.User;
import com.lsio.springboot.entities.user.RefreshToken;
import com.lsio.springboot.entities.user.Role;
import com.lsio.springboot.entities.user.RoleName;
import com.lsio.springboot.entities.user.UserDevice;
import com.lsio.springboot.repositories.user.RoleRepository;
import com.lsio.springboot.repositories.user.UserRepository;
import com.lsio.springboot.services.exception.TokenRefreshException;
import com.lsio.springboot.services.user.RefreshTokenService;
import com.lsio.springboot.services.user.UserDeviceService;
import com.lsio.springboot.utils.security.JwtProvider;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    AuthenticationManager authenticationManager;
 
    @Autowired
    UserRepository userRepository;
 
    @Autowired
    RoleRepository roleRepository;
 
    @Autowired
    PasswordEncoder encoder;
 
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    private RefreshTokenService refreshTokenService;
    
    @Autowired
    private UserDeviceService userDeviceService;
 
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
    	
    	User user = userRepository.findByEmail(loginRequest.getEmail())
    			.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not found."));
    	
    	if (user.getActive()) {
    		Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            ); 
            SecurityContextHolder.getContext().setAuthentication(authentication); 
            String jwtToken = jwtProvider.generateJwtToken(authentication);
            userDeviceService.findByUserId(user.getId())
            .map(UserDevice::getRefreshToken)
            .map(RefreshToken::getId)
            .ifPresent(refreshTokenService::deleteById);

            UserDevice userDevice = userDeviceService.createUserDevice(loginRequest.getDeviceInfo());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken();
            userDevice.setUser(user);
            userDevice.setRefreshToken(refreshToken);
            refreshToken.setUserDevice(userDevice);
            refreshToken = refreshTokenService.save(refreshToken);
            return ResponseEntity.ok(new JwtResponse(jwtToken, refreshToken.getToken(), jwtProvider.getExpiryDuration()));
    	}
    	return ResponseEntity.badRequest().body(new ApiResponse(false, "User has been deactivated/locked !!"));
    }
 
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }
 
        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
 
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
 
        strRoles.forEach(role -> {
          switch(role) {
          case "admin":
            Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
            roles.add(adminRole);
            
            break;
          case "therapist":
                Role therapistRole = roleRepository.findByName(RoleName.ROLE_THERAPIST)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
                roles.add(therapistRole);
                
            break;
          default:
              Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
              roles.add(userRole);              
          }
        });
        
        user.setRoles(roles);
        user.activate();
        User result = userRepository.save(user);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();
 
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully!"));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshJwtToken(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {
    	
    	String requestRefreshToken = tokenRefreshRequest.getRefreshToken();
    	
    	Optional<String> token = Optional.of(refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshToken -> {
                    refreshTokenService.verifyExpiration(refreshToken);
                    userDeviceService.verifyRefreshAvailability(refreshToken);
                    refreshTokenService.increaseCount(refreshToken);
                    return refreshToken;
                })
                .map(RefreshToken::getUserDevice)
                .map(UserDevice::getUser)
                .map(u -> jwtProvider.generateTokenFromUser(u))
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Missing refresh token in database.Please login again")));
        return ResponseEntity.ok().body(new JwtResponse(token.get(), tokenRefreshRequest.getRefreshToken(), jwtProvider.getExpiryDuration()));
    }

    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }
}
