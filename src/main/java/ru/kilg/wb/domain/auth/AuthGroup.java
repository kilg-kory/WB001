package ru.kilg.wb.domain.auth;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class AuthGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private String authGroup;


}
