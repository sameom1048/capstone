package com.example.demo.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "GPU")
public class GPU {
    @Id // 기본키
    private String product_Name;

    @Column(name = "GPU_Benchmark")
    private String email;

    @Column(name = "price")
    private int price;

    @Column(name = "price_Performance")
    private double price_Performance;
}
