package com.mlj.modle;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "t_order")
public class order {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String number = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + (int) (Math.random() * 10000);//订单号

    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "in_time", nullable = false)
    private Date inTime;//入住时间

    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "out_time", nullable = false)
    private Date outTime;//退房时间

    @Column(name = "one_price")
    private double onePrice;//单价

    @Column(name = "totle_price")
    private double totlePrice;//总价

    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "create_time", nullable = false)
    private Date createTime = new Date();//预定时间

    @Column(nullable = false)
    private int status = 0;//订单状态 1：已受理 2：已拒绝 0：未受理 -1:已删除

    @ManyToOne
    @JoinColumn(nullable = false, name = "order_id")
    private User ordero;



    public User getOrdero() {
        return ordero;
    }

    public void setOrdero(User ordero) {
        this.ordero = ordero;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getTotlePrice() {
        return totlePrice;
    }

    public void setTotlePrice(double totlePrice) {
        this.totlePrice = totlePrice;
    }

    public double getOnePrice() {
        return onePrice;
    }

    public void setOnePrice(double onePrice) {
        this.onePrice = onePrice;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
