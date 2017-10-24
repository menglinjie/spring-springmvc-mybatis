package com.friendfinder.controller;

import com.friendfinder.model.Goods;
import com.friendfinder.service.AttachmentService;
import com.friendfinder.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 12191 on 2017/5/10/ 0010.
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AttachmentService attachmentService;

    @Transactional
    @RequestMapping(value = "/add.action",method = RequestMethod.POST)
    public void addGoods(@RequestBody Goods goods){
        goodsService.addGoods(goods);
    }
    @Transactional
    @RequestMapping(value = "/del.action",method = RequestMethod.POST)
    public void delGoods(Integer id){
    	Goods goods = new Goods();
    	goods.setId(id);
        goodsService.deleteGoods(goods);
    }
    @Transactional
    @RequestMapping(value = "/upd.action",method = RequestMethod.POST)
    public void updGoods(@RequestBody Goods goods){
        goodsService.updateGoods(goods);
    }

    /**
     * ***根据名字查询商品
     * @param name
     *localhost:8080/friendfinder/goods/selByName?name=name1
     *
     * {"charm":1,"discount_point":1.0,"discount_rmb":1.0,"id":1,"name":"name1","price_point":1,"price_rmb":1,"sales":0,"type":0}
     */
    @RequestMapping(value = "/selByName.action",method = RequestMethod.GET)
    public Goods selectByName(String name){
       return goodsService.selectGoodsByName(name);
    }

    /**
     * *****根据ID查询商品
     * @param id
     *localhost:8080/friendfinder/goods/selById?id=1
     *
     *{"charm":1,"discount_point":1.0,"discount_rmb":1.0,"id":1,"name":"name1","price_point":1,"price_rmb":1,"sales":0,"type":0}
     */
    @RequestMapping(value = "/selById.action",method = RequestMethod.GET)
    public Goods selectById(Integer id){
        return goodsService.selectGoodsById(id);
    }

    /**
     ****查看所有商品
     *localhost:8080/friendfinder/goods/selAll
     *
     *[{"charm":1,"discount_point":1.0,"discount_rmb":1.0,"id":1,"name":"name1","price_point":1,"price_rmb":1,"sales":0,"type":0},
     * {"charm":1,"discount_point":1.0,"discount_rmb":1.0,"id":2,"name":"name2","price_point":1,"price_rmb":1,"sales":0,"type":0},
     * {"charm":1,"discount_point":1.0,"discount_rmb":1.0,"id":3,"name":"name3","price_point":1,"price_rmb":1,"sales":0,"type":0}]
     *
     */
    @RequestMapping(value = "/selAll.action",method = RequestMethod.GET)
    public Goods[] selectAllGoods(){
        Goods[] goods = goodsService.selectAllGoods();
        int goodsLength = goods.length;
        Integer[] goodsFileId = new Integer[goodsLength];
        for(int i =0;i<goodsLength;i++){
//            try{
//            goods[i].setImg(attachmentService.showAttachment(Integer.parseInt(goods[i].getImg())).getFileName());
//            }catch (Exception e){
//                goods[i].setImg("f4e0c1e2-0671-47af-bbc7-3ef083261d3d048.jpg");
//            }
            goodsFileId[i] =Integer.parseInt(goods[i].getImg());
        }
        List<String> goodsFileName = attachmentService.selectFileName(goodsFileId);
        String[] goodsFileName2 = (String[]) goodsFileName.toArray(new String[0]);
        System.out.println(Arrays.toString(goodsFileName2));
        for(int i =0;i<goodsLength;i++){
//           if(goodsFileName2.length<=i){
//                goods[i].setImg("f4e0c1e2-0671-47af-bbc7-3ef083261d3d048.jpg");
//            }else {
//               goods[i].setImg(goodsFileName2[i]);
//           }
            try{
                goods[i].setImg(goodsFileName2[i]);
            }catch (Exception e){
                goods[i].setImg("f4e0c1e2-0671-47af-bbc7-3ef083261d3d048.jpg");
            }

        }
        return goods;
    }


}
