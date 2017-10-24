package com.mlj.action;

import com.mlj.modle.City;
import com.mlj.service.CityService;
import com.mlj.service.impl.CityServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CityAction extends ActionSupport {

    private int id;
    private String cityF;
    private String citiS;

    public void setCityF(String cityF) {
        this.cityF = cityF;
    }

    public void setCitiS(String citiS) {
        this.citiS = citiS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private CityService cityService = new CityServiceImpl();

    HttpServletResponse response = ServletActionContext.getResponse();

    /**
     * 查询一级城市
     *
     * @throws IOException
     */
    public String showF() throws IOException {
        System.out.println("进入action");
        response.setCharacterEncoding("UTF-8");
        JSONArray jsonArray = JSONArray.fromObject(cityService.showF());//list转换为json
        response.getWriter().print(jsonArray);
        return null;
    }

    /**
     * 根据父类id查
     *
     * @return
     * @throws IOException
     */
    public String showS() throws IOException {
        System.out.println("进入SSSSSSSSSSSSSaction");
        response.setCharacterEncoding("UTF-8");
        List<City> list = cityService.showByParentId(id);
        JSONArray jsonArray = JSONArray.fromObject(list);//list转换为json
        System.out.println(id);
        response.getWriter().print(jsonArray);
        return null;
    }
}
