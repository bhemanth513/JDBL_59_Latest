package com.example.redis.demo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Person implements Serializable {

    private Integer id;
    private String name;
    private Long age;
    private Boolean isINR;
    private Double creditScore;
}
