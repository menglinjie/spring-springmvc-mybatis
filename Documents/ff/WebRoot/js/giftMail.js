/**
 *
 * 待做：添加商品单一价格形式，购买功能
 *
 *
 * Created by 12191 on 2017/5/13/ 0013.
 */

function waitLoading(){
    // 添加动画样式
    $("<div>").attr("id", "all").appendTo($("body"));
    var center = $("<div>").addClass("center1").appendTo($("body"));
    $("<label>").addClass("common lab1").appendTo(center);
    $("<label>").addClass("common lab2").appendTo(center);
    $("<label>").addClass("common lab3").appendTo(center);
    $("<label>").addClass("common lab4").appendTo(center);
}
function cleanWaitLoading(){
    // 消失动画
    $(".center1").animate({opacity:"0"});
    $(".center1").hide("fast");

    // 延时清除HTML内容
    setTimeout(function(){
        $("#all").remove();
        $(".center1").remove();
    }, 500)
}


var giftMessage;

$(document).ready(function () {
    waitLoading();
    $.ajax({
        type:"get",
        url:"../manage/selectAllGoods.action",
        data:{},
        async:true,
        cache:true,
        dataType:"json",
        success:function (data) {
           // console.log(data);
        	
            var leg =  data.length;
            console.log(leg);
            giftMessage = data;
            console.log(giftMessage);
            selGift();
            cleanWaitLoading();
        },
        error:function (data) {
            console.log(data);
        }
    });
});


//price类型选择
function  sel_price(p) {
    $("#sel_pirce_value").html(p.innerHTML);
    selGift();
}
//筛选条件选择
function sel_type(t) {
    $("#sel_type_value").html(t.innerHTML);
    giftMessage.sort(sortGiftMessage);
    selGift();
}


//根据giftMessage Json 信息 加载商品模块
function  selGift() {
    $("#GiftMain").empty();

    var leg =  giftMessage.length;
    for(i=0;i<leg;i++){
        var div = $(Gift(giftMessage[i]));
        $("#GiftMain").append(div);
    }
}
//商品模块
function Gift(giftMessage) {
    var id,name,price,discount,charm,messageAll,messageShort,sales,img;
    id = giftMessage.id;
    name = giftMessage.name;
    if($("#sel_pirce_value").text() =="人民币"){
        price = "￥"+Math.round((giftMessage.price_rmb*giftMessage.discount_rmb)*100)/100;
        discount = giftMessage.discount_rmb;
    }else {
        price = "P"+(giftMessage.price_point*giftMessage.discount_point);
        discount = giftMessage.discount_point;
    }
    if(discount == 1 )discount="推 荐";
    else discount = "折扣："+discount*100+"折";
    charm = giftMessage.charm;
    messageAll = giftMessage.message;
    if(messageAll.length>60)
        messageShort = messageAll.substr(0,59)+"...";
    else messageShort = messageAll;
    sales = giftMessage.sales;
    img = giftMessage.img;
    var urlAddress = "http://139.199.16.239:8080/demo/image/";
    var newElement="<div class='gift col-xs-12 col-sm-6 col-md-4 col-lg-3'><div class='clearfix'><div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'><img src='"+urlAddress+img+"' class='img-responsive' /></div><div class='caption col-xs-12 col-sm-12 col-md-12 col-lg-12'><div class='clearfix'><h4>"+name+"</h4><div class=' text-danger'><label>"+price+"(魅力值+<span>"+charm+"</span>)</label></div></div><p><span class='text_overflow1' title='"+messageAll+"'>"+messageShort+"</span></p><button type='button' class='btn btn-info pull-right' onclick='addGift_Car("+id+")'>点击购买</button></div></div><label class='gift_discount'>"+discount+"</label></div>";
    return newElement;
}

// 对商品条件排序
function  sortGiftMessage(x,y) {
    var type = $("#sel_type_value").text();
    var price = $("#sel_pirce_value").text();
    switch (type){
        case "全部":
            return (x.id>y.id)?1:-1;
        case "最热":
            return (x.sales>y.sales)?-1:1;
        case "最新":
            return (x.id>y.id)?-1:1;
        case "最贵":
            if(price =="人民币")
                return (x.price_rmb*x.discount_rmb>y.price_rmb*y.discount_rmb)?-1:1;
            else return (x.price_point*x.discount_point>y.price_point*y.discount_point)?-1:1;
        case "最便宜":
            if(price =="人民币")
                return (x.price_rmb*x.discount_rmb>y.price_rmb*y.discount_rmb)?1:-1;
            else return (x.price_point*x.discount_point>y.price_point*y.discount_point)?1:-1;
    }
}



//购物车商品数量调整事件
function upd_car(){
    car_pay();
}


//添加按钮响应事件
function addGift_Car(id) {
  //  console.log("add "+id);
    for(var i=0,l=giftMessage.length;i<l;i++){
       if(giftMessage[i].id==id){
           gift_add(giftMessage[i]);
       }
    }
    $("#car_remove").attr("disabled",false);
    $("#car_buy").attr("disabled",false);
    car_pay();
}
//购物车商品添加响应事件
function gift_add(giftMessage) {
    if($("#gift"+giftMessage.id).length>0){
        // console.log($("#gift_num"+giftMessage.id).val());
        $("#gift_num"+giftMessage.id).val(parseInt($("#gift_num"+giftMessage.id).val())+1);
        return;
    }
    $("#gift_car_main").append(Gift_car(giftMessage));
}
//购物车商品模版
function Gift_car(giftMessage) {
    var name = giftMessage.name;
    var price = ($("#sel_pirce_value").text()=="人民币")?(Math.round((giftMessage.price_rmb*giftMessage.discount_rmb)*100)/100+"元"):(giftMessage.price_point*giftMessage.discount_point+"积分");

    var id = giftMessage.id;
    var newElement=	"<div class='gift ' id='gift"+id+"'><div class='clearfix'><h4 class='pull-left'>"+name+"</h4><h4 class='pull-right'id='gift_price"+id+"'>单价:<span id='gift_price_value"+id+"'>"+price+"</span></h4></div><div class='input-group col-xs-12 col-sm-12 col-md-12 col-lg-12'><label class='input-group-addon'>商品个数:</label><input type='number' class='form-control' id='gift_num"+id+"' value='1' onclick='upd_car()'><span class='input-group-btn'><button class='btn btn-default' type='button' onclick='car_remove("+id+")'>移除</button></span></div></div>"
    return newElement;
}
//购物车总价格响应事件
function car_pay() {
    var price_rmb = 0;
    var price_point = 0;
    $("input[id^='gift_num']").each(function () {
        var num = $(this).val();
        var id = this.id.toString().charAt(this.id.length-1);
        var price = $("#gift_price_value"+id).text();

        if(isContains(price,"元")){
            var p = price.replace(/[^0-9.]/ig,"");
            price_rmb += Math.round(p*num*100)/100;
        }else {
            var p = price.replace(/[^0-9.]/ig,"");
            price_point +=p*num;
        }

    })
    $("#car_price").html("总价格:"+price_rmb+"元  "+price_point+"积分") ;
}
function isContains(str, substr) {
    return str.indexOf(substr) >= 0;
}



//购物车移除按钮事件
function car_remove(id) {
    $("#gift"+id).remove();
    car_pay();
}
//购物车清空按钮响应事件
function car_remove_all() {
    $("div[id^='gift']").each(function () {
        if(this.id.length<7)
            $(this).remove();
    })
    $("#car_price").html("您的购物车空空如也~");
    $("#car_remove").attr("disabled",true);
    $("#car_buy").attr("disabled",true);
}



function car_buy_all() {
    var json ={};
    var userId = getCookie("userId");
    json["userId"]=userId;
    json["list"] =[];
    $("input[id^='gift_num']").each(function () {
        var num = $(this).val();
        var id = this.id.substr(this.id.toString().lastIndexOf("m")+1);
        var price = $("#gift_price_value"+id).text();
        var type;
        if(isContains(price,"元")){
            type= 0;
        }else {
            type= 1;
        }
        var obj ={};
        obj.id = id;
        obj.num = num;
        obj.type = type;
        json["list"].push(obj);
    })
    console.log(json);
    if(json["list"].length<1)return;
    waitLoading();
    $.ajax({
        type:"post",
        url:"../user/giftBy.action?giftList="+json,
        data:JSON.stringify(json),
        dataType:"json",
        contentType:'application/json;charset=UTF-8',
        async:true,
        cache:true,
        success:function (data) {
            console.log(data);
            alert(data.message);
            cleanWaitLoading();
        },
        error:function (data) {
            console.log(data);
        }
    });
}
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}