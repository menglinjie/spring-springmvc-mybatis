package com.mlj.controller;

import com.mlj.modle.House;
import com.mlj.modle.Order_house;
import com.mlj.modle.User;
import com.mlj.modle.order;
import com.mlj.service.HouseService;
import com.mlj.service.OrderService;
import com.mlj.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private HouseService houseService;

    /**
     * 查看房东订单
     *
     * @param page
     * @param status
     * @param creatorId
     * @return
     */
    @RequestMapping("/getByD")
    public ModelAndView getByD(@RequestParam int page,
                               @RequestParam int status,
                               @RequestParam String creatorId) {
        PageBean orderD = orderService.getByD(page, status, creatorId);
//        List<Order_house> list = orderD.getList();
//        for (Order_house order : list
//                ) {
//            System.out.println(order.getHouse().getCreator().getName() + "=============");
//            System.out.println(order.getOrder().getInTime() + "========");
//        }
        return new ModelAndView("order-d", "orderD", orderD);
    }

    /**
     * 查看房东成交订单
     *
     * @param page
     * @param status
     * @param creatorId
     * @return
     */
    @RequestMapping("/getByDy")
    public ModelAndView getByDy(@RequestParam int page,
                                @RequestParam int status,
                                @RequestParam String creatorId) {
        PageBean orderDy = orderService.getByD(page, status, creatorId);
        return new ModelAndView("order", "orderDy", orderDy);
    }

    /**
     * 查看房客订单
     *
     * @param page
     * @param status
     * @param orderoId
     * @return
     */
    @RequestMapping("getByK")
    public ModelAndView getByK(@RequestParam int page,
                               @RequestParam int status,
                               @RequestParam String orderoId) {
        PageBean orderK = orderService.getByK(page, status, orderoId);
        return new ModelAndView("order-k", "orderK", orderK);
    }

    /**
     * 查看房客订单(已受理)
     *
     * @param page
     * @param status
     * @param orderoId
     * @return
     */
    @RequestMapping("getByKy")
    public ModelAndView getByKy(@RequestParam int page,
                                @RequestParam int status,
                                @RequestParam String orderoId) {
        PageBean orderKy = orderService.getByK(page, status, orderoId);
        return new ModelAndView("order-k-y", "orderKy", orderKy);
    }


    /**
     * 下订单
     *
     * @param inTime
     * @param outTime
     * @param num
     * @param houseId
     * @param request
     * @return
     */
    @RequestMapping("/save")
    public ModelAndView save(@RequestParam String inTime,
                             @RequestParam String outTime,
                             @RequestParam int num,
                             @RequestParam String houseId,
                             HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        try {
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date intime1 = simpleDateFormat.parse(inTime);
            Date outtime1 = simpleDateFormat.parse(outTime);

            Order_house order_house = new Order_house();
            House house = houseService.get(houseId);
            order order = new order();
            order.setInTime(intime1);
            order.setOutTime(outtime1);
            order.setOnePrice(house.getPrice());
            order.setTotlePrice(house.getPrice() * num);
            order.setOrdero(user);

            order_house.setHouse(house);
            order_house.setOrder(order);
            orderService.save(order, order_house);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/order/getByK?page=1&status=0&orderoId=" + user.getId());
    }

    /**
     * 取消订单/退订（状态置-1）
     *
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateStatus")
    public ModelAndView updateStatus(@RequestParam String id,
                                     @RequestParam int status,
                                     HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        orderService.updateStatus(status, id);
        return new ModelAndView("redirect:/order/getByK?page=1&status=0&orderoId=" + user.getId());
    }

    /**
     * 受理订单
     *
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateStatusd")
    public ModelAndView updateStatusd(@RequestParam String id,
                                      @RequestParam int status,
                                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        orderService.updateStatus(status, id);
        return new ModelAndView("redirect:/order/getByD?page=1&status=0&creatorId=" + user.getId());
    }
}
