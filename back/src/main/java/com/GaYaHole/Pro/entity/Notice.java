package com.GaYaHole.Pro.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notice{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int n_num;

    @Column(nullable = false, length=500)
    private String n_content;

    @Column(nullable = false, length=50)
    private String n_title;
}
