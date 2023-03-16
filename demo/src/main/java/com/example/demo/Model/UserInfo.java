package com.example.demo.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "userInfo")
public class UserInfo {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CPU")
    private String CPU;

    @Column(name = "GPU")
    private String GPU;

    @Column(name = "RAM")
    private String RAM;

}
