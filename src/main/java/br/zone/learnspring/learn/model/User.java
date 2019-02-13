package br.zone.learnspring.learn.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String username;

    @Size(min = 8, max = 15)
    private String password;

    @NotBlank
    private String name;

    private boolean status = true;

}
