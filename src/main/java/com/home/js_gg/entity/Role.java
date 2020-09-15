package com.home.js_gg.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role_info")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_seq", nullable = false, updatable = false)
    private Long roleSeq;
    @Column(name = "role_name")
    private String roleName;
    @ManyToOne
    @JoinColumn(name = "users")
    @JsonIgnore
    Users users;
}
