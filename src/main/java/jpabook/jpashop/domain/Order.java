package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")  //테이블을 안적어주면 관례로 order가 된다?
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")         //외래키의 이름이 member_id가 된다고 보면됨
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToOne(fetch = LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")       //연관관계의 주인으로!
    private Delivery delivery;

    private LocalDateTime orderDate;        //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;     //주문상태 [ORDER, CANCEL]

    //연관관계 편의 메소드 //
    public void setMember(Member member) {

        this.member = member;
        member.getOrders().add(this);
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

