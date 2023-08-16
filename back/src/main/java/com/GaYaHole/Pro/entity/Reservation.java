package com.GaYaHole.Pro.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservation_num;

    private int total_price;

    private String order_id;

    private Date check_in;

    private Date check_out;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private User id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="r_num")
    private Room r_num;

    private String option_code;

}
