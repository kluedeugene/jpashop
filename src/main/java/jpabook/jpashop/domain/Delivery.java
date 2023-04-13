package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)       //배송정보, 상품 일대일
    private Order order;

    @Embedded
    private Address address;


    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;      //READY, COMP( 준비, 배송)
}
