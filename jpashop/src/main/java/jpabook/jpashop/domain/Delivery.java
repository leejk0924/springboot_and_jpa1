package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy="delivery")
    private Order order;

    @Embedded
    private Address address;


    @Enumerated(EnumType.STRING)        // EnumType.ORDINAL 사용시 enum 추가할 떄 오류 발생 (순서 변경 떄문에) -> 그래서 STRING 사용
    private DeliverStatus status;           // READY, COMP
}
