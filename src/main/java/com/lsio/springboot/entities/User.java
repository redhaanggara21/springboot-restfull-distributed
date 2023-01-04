package com.lsio.springboot.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users")
public class User extends Auditable<String> implements Serializable {
    @Id
	@Column(name="user_id")
	private int userId;

    @Column(name="full_name")
	private String fullname;
	
	@Column(name="user_name")
	private String userName;

    @Column(name="email")
	private String email;
		
	@Column(name="password")
	private String password;

    @Column(name="active")
	private Boolean active;
	
	@Column(name="role")
	private String role;

    public User() {
        super();
    }

    public User(int userId, String fullname, String userName, String email, String password, Boolean active, String role) {
        super();
        this.userId = userId;
        this.fullname = fullname;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.role = role;
    }
    
    
    

}
