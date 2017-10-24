package com.mlj.test;

import com.mlj.modle.City;
import com.mlj.modle.User;
import com.mlj.service.CityService;
import com.mlj.service.UserService;
import com.mlj.service.impl.CityServiceImpl;
import com.mlj.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

public class TestService {

    UserService userService = new UserServiceImpl();
    CityService cityService = new CityServiceImpl();

    @Test
    public void testInsertUser() {
//       准备测试数据
        User user = new User();
        user.setEmail("2411988749@qq.com");
        user.setPassword("11111111");

        userService.add(user);
    }

    @Test
    public void testShowUser() {

//        准备测试数据
        String email = "2411988749@qq.com";

        boolean isExit = userService.show(email);
        System.out.println(isExit);
    }

    @Test
    public void testShowCity() {

        System.out.println("测试查询一级城市");
        List<City> cities = cityService.showF();
        System.out.println("查询结束" + cities.size());
        for (City city : cities
                ) {
            System.out.println(city.getName());
        }
    }

    @Test
    public void testShowBYParentId() {

        List<City> cities = cityService.showByParentId(1);

        for (City c : cities
                ) {
            System.out.println(c.getName());
        }
    }

    @Test
    public void testShowCityAll() {
        List<City> cities = cityService.showS();
        for (City c : cities
                ) {
            System.out.println(c.getName());
        }
    }
}
