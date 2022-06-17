package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;


    // FetchType.EAGER 으로 설정 시 특히 JPQL을 실행할 때n+1 문제가 발생한다.
    // JPQL select o From order o; -> SQL select * from order  :
    @ManyToOne(fetch=FetchType.LAZY)   // FetchType.EAGER이 기본 -> 실무에서 모든 연관관계는 FetchType.LAZY 으로 설정해야 한다.
    @JoinColumn(name="member_id")
    private Member member;

    // CascadeType.ALL : OrderItems 를 여러개 persist 하고 order 를 persist 해야하는 동작을 order 만 persist 해줘도 된다.
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;        // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);   // 연관관계 편의 메서드
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
