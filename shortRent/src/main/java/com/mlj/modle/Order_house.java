package com.mlj.modle;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_order_house")
public class Order_house {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private order order;//订单

    @ManyToOne
    @JoinColumn(nullable = false, name = "house_id")
    private House house;


    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public com.mlj.modle.order getOrder() {
        return order;
    }

    public void setOrder(com.mlj.modle.order order) {
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
