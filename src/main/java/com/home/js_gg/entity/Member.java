package com.home.js_gg.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
public class Member {
    private String uid;

    private String upw;
    private String uname;

//    @CreationTimestamp
//    private LocalDateTime regdate;
//    @UpdateTimestamp
//    private LocalDateTime updatedate;
}
