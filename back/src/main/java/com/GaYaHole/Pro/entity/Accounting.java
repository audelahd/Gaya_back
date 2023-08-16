package com.GaYaHole.Pro.entity;


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
public class Accounting{

    @Id
    private int pay_number;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private User id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reservation_num")
    private Reservation reservation_num;
}
