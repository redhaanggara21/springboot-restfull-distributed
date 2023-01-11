package com.lsio.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class SpringbootApplication {
	
	private static final String dateFormat = "yyyy-MM-dd";
	private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	// @Bean
	// public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer(){
	// 	return builder ->{
	// 		builder.simpleDateFormat(dateFormat);
	// 		builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
	// 		builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
	// 		builder.serializers(new ZonedDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
	// 	};
	// }

	// @Bean
	// public CorsFilter corsFilter(){
	// 	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	// 	final CorsConfiguration config = new CorsConfiguration();
	// 	config.setAllowCredentials(true);
	// 	List<String> originList = Arrays.asList("http://localhost:8080");

	// 	config.setAllowedOrigins(originList);
	// 	config.addAllowedHeader("*");
	// 	config.addAllowedMethod("OPTIONS");
	// 	config.addAllowedMethod("HEAD");
	// 	config.addAllowedMethod("GET");
	// 	config.addAllowedMethod("PUT");
	// 	config.addAllowedMethod("POST");
	// 	config.addAllowedMethod("DELETE");
	// 	config.addAllowedMethod("PATCH");
	// 	source.registerCorsConfiguration("/**", config);

	// 	return new CorsFilter();
	// }
}
