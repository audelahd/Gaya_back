package com.GaYaHole.Pro.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {
    
    @Id
    private int r_num; //방 번호
    
    private int r_size; //인원 수
    
    @Column(nullable = false, length = 20)
    private String r_type; //방 종류 11
    
    private int r_price; //방 가격
}
