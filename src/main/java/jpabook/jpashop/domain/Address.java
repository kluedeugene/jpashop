package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable //어딘가에 내장이 될수있다
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() {
        //기본생성자 필요
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}