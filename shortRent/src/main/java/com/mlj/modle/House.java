package com.mlj.modle;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_house")
public class House {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "name_", nullable = false)
    private String name;//房屋名称

    private String picture;//房屋预览图片地址

    @Column(name = "house_type", nullable = false)
    private int houseType;//房屋类型 1：酒店  2：民宿  3：主题

    @Column(name = "rent_type", nullable = false)
    private int rentType;//出租类型 1：整租 2：单间 3：合租

    @Column
    private double area;//面积

    @Column
    private double price;//价格

    @Column(nullable = false)
    private String describ;//房屋描述

    @Column(nullable = false)
    private String rule;//使用规则

    @Column(name = "min_day", nullable = false)
    private int min_day;//最小天数

    @Column(nullable = false, name = "max_day")
    private int max_day;//最大天数

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime = new Date();//创建时间

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;//创建者

    @Column(nullable = false)
    private String address;

    public User getCreator() {
        return creator;
    }

    @Column(nullable = false)
    private int status = 1;//状态 1:未审核  2：已审核(未发布)  3：已发布 0：已拒绝 -1:已删除


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMax_day() {
        return max_day;
    }

    public void setMax_day(int max_day) {
        this.max_day = max_day;
    }

    public int getMin_day() {
        return min_day;
    }

    public void setMin_day(int min_day) {
        this.min_day = min_day;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getRentType() {
        return rentType;
    }

    public void setRentType(int rentType) {
        this.rentType = rentType;
    }

    public int getHouseType() {
        return houseType;
    }

    public void setHouseType(int houseType) {
        this.houseType = houseType;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
