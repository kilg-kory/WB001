package ru.kilg.wb.domain.auth;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "roles")
@ToString(exclude = "roles")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String name;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<AuthGroup> roles;

    private String confirmCode;

}
