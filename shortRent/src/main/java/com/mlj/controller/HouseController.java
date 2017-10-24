package com.mlj.controller;

import com.mlj.modle.House;
import com.mlj.modle.User;
import com.mlj.service.HouseService;
import com.mlj.service.UserService;
import com.mlj.util.PageBean;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;
    @Autowired
    private UserService userService;

    /**
     * 所有房屋（不同状态）
     *
     * @param page
     * @param status
     * @return
     */
    @RequestMapping("/getList")
    public ModelAndView getList(@RequestParam int page,
                                @RequestParam int status) {
        PageBean houses = houseService.getList(page, status);
        ModelAndView modelAndView = new ModelAndView("uncheck", "houses", houses);
        return modelAndView;
    }

    /**
     * 所有房屋（所有状态）
     *
     * @param page
     * @return
     */
    @RequestMapping("/getListNOStatus")
    public ModelAndView getListNOStatus(@RequestParam int page) {
        PageBean housesNOState = houseService.getAllListNoState(page);
        ModelAndView modelAndView = new ModelAndView("house", "housesNoState", housesNOState);
        return modelAndView;
    }

    /**
     * 房屋统计
     *
     * @return
     */
    @RequestMapping("/getTotleCount")
    public ModelAndView getTotleCount() {
        int[] totleHouse = houseService.getTotleCount();
        return new ModelAndView("totleHouse", "totleHouse", totleHouse);
    }

    /**
     * 查找房东的房屋
     * 1:未审核  2：已审核(未发布)  3：已发布
     *
     * @param creatorId
     * @param status
     * @param page
     * @return
     */
    @RequestMapping("/getByUser")
    public ModelAndView getByUser(@RequestParam String creatorId,
                                  @RequestParam int status,
                                  @RequestParam int page) {
        PageBean myHouse = houseService.getListByUser(page, status, creatorId);
        return new ModelAndView("publishHouse", "myHouse", myHouse);
    }

    /**
     * 查找房东的房屋
     *
     * @param creatorId
     * @param page
     * @return
     */
    @RequestMapping("/getAllByUser")
    public ModelAndView getAllByUser(@RequestParam String creatorId,
                                     @RequestParam int page) {
        PageBean myAllhouse = houseService.getAllByUser(page, creatorId);
        return new ModelAndView("myHouse", "myAllHoouse", myAllhouse);
    }

    /**
     * 条件查询房屋
     *
     * @param page
     * @param param
     * @return
     */
    @RequestMapping("/getByParam")
    public ModelAndView getByParam(@RequestParam int page,
                                   @RequestParam String param) {
        PageBean searchHouse = houseService.getListByParam(page, param);
        return new ModelAndView("index", "searchHouse", searchHouse);
    }

    /**
     * 查找单个房屋
     *
     * @param id
     * @return
     */
    @RequestMapping("/get")
    public ModelAndView get(@RequestParam String id) {
        House house = houseService.get(id);
        return new ModelAndView("details", "house", house);
    }


    /**
     * 查找单个房屋（修改）
     *
     * @param id
     * @return
     */
    @RequestMapping("/getu")
    public ModelAndView getu(@RequestParam String id) {
        House houseu = houseService.get(id);
        return new ModelAndView("update", "houseu", houseu);
    }

    /**
     * 创建房屋
     *
     * @return
     */
    @RequestMapping("/save")
    public ModelAndView save(@RequestParam String name,
                             @RequestParam int houseType,
                             @RequestParam int rentType,
                             @RequestParam double area,
                             @RequestParam double price,
                             @RequestParam String describ,
                             @RequestParam String rule,
                             @RequestParam int min_day,
                             @RequestParam int max_day,
                             @RequestParam String address,
                             HttpServletRequest request,
                             @RequestParam("picture") MultipartFile picture) {
        House house1 = new House();
        User user = (User) request.getSession().getAttribute("user");
        house1.setArea(area);
        house1.setDescrib(describ);
        house1.setHouseType(houseType);
        house1.setMax_day(max_day);
        house1.setMin_day(min_day);
        house1.setName(name);
        house1.setPrice(price);
        house1.setRentType(rentType);
        house1.setRule(rule);
        house1.setAddress(address);
        house1.setCreator(user);

        // 如果文件不为空，写入上传路径
        if (!picture.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/image/");
            //上传文件名
            String filename = picture.getOriginalFilename();
            System.out.println(filename + "============");
            File filepath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            try {
                // 先尝试压缩并保存图片
                Thumbnails.of(picture.getInputStream()).scale(1f).outputQuality(0.25f).toFile(new File(path + File.separator + filename));
                // picture.transferTo(new File(path + File.separator + filename));
            } catch (IOException e) {
                e.printStackTrace();
            }

            house1.setPicture(filename);
            houseService.save(house1);

        }
        return new ModelAndView("redirect:/house/getAllByUser?page=1&creatorId=" + user.getId());
    }


    /**
     * 修改房屋信息
     *
     * @return
     */
    @RequestMapping("/update")
    public ModelAndView update(@RequestParam String houseId,
                               @RequestParam String name,
                               @RequestParam int houseType,
                               @RequestParam int rentType,
                               @RequestParam double area,
                               @RequestParam double price,
                               @RequestParam String describ,
                               @RequestParam String rule,
                               @RequestParam int min_day,
                               @RequestParam int max_day,
                               @RequestParam String address,
                               HttpServletRequest request,
                               @RequestParam("picture") MultipartFile picture) {
        House house = new House();
        User user = (User) request.getSession().getAttribute("user");
        house.setArea(area);
        house.setDescrib(describ);
        house.setHouseType(houseType);
        house.setMax_day(max_day);
        house.setMin_day(min_day);
        house.setName(name);
        house.setPrice(price);
        house.setRentType(rentType);
        house.setRule(rule);
        house.setAddress(address);
        house.setCreator(user);
        house.setId(houseId);

        // 如果文件不为空，写入上传路径
        if (!picture.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/image/");
            //上传文件名
            String filename = picture.getOriginalFilename();
            System.out.println(filename + "============");
            File filepath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            try {
                // 先尝试压缩并保存图片
                Thumbnails.of(picture.getInputStream()).scale(1f).outputQuality(0.25f).toFile(new File(path + File.separator + filename));
                // picture.transferTo(new File(path + File.separator + filename));
            } catch (IOException e) {
                e.printStackTrace();
            }

            house.setPicture(filename);

        }

        houseService.update(house);
        return new ModelAndView("redirect:/house/getAllByUser?page=1&creatorId=" + user.getId());
    }

    /**
     * 修改状态（审核）
     *
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateStatus")
    public ModelAndView updateStatus(@RequestParam String id,
                                     @RequestParam int status) {
        houseService.updateStatus(id, status);
        return new ModelAndView("redirect:/house/getList?page=1&status=1");
    }

    /**
     * 修改状态（发布）
     *
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateStatus1")
    public ModelAndView updateStatus1(@RequestParam String id,
                                      @RequestParam int status,
                                      HttpServletRequest request) {
        houseService.updateStatus(id, status);
        User user = (User) request.getSession().getAttribute("user");
        return new ModelAndView("redirect:/house/getByUser?page=1&status=2&creatorId="
                + user.getId());

    }

}
