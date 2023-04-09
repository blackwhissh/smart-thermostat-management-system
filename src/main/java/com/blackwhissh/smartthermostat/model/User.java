package com.blackwhissh.smartthermostat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "users")
@ToString
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_username", unique = true)
    private String username;

    @Column(name = "user_password")
    private String password;



    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
