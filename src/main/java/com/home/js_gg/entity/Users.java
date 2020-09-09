package com.home.js_gg.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_info")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_seq;
    private String user_id;
    private String user_pwd;
    private String user_name;

//    @CreationTimestamp
//    private LocalDateTime regdate;
//    @UpdateTimestamp
//    private LocalDateTime updatedate;
}
