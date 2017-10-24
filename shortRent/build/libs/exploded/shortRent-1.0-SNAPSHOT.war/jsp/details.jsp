<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>房屋详情</title>
    <style type="text/css">
        #head {
            width: 100%;
            height: 110px;
            background: #FFDCB8;
        }

        #content {
            width: 100%;
            height: 500px;
        }

        #con-left {
            width: 50%;
            height: 100%;
            float: left;
            margin-left: 60px;
        }

        #con-right {
            width: 40%;
            height: 100%;
            float: right;
        }

        td {
            width: 215px;
            height: 30px;
        }

        tr {
            background: #FFDCB8
        }

        .tr-2 {
            background: #FFFAF4;
        }

        #button {
            color: #FFFFFF;
            background-color: #5FBA00;
            cursor: pointer;
            /*float: left;*/
            width: 300px;
            height: 40px;
            border-color: #459A00;
            border-style: solid;
            border-width: 1px;
            font-family: 微软雅黑;
            font-size: 18px;
            font-weight: bold;
        }

    </style>
</head>
<body>
<div id="head">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <span style="font-size: 30px;color: #F75000" ;>${requestScope.house.name}</span>
    <br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <span>地址：${house.name}</span>
</div>
<div id="content">
    <div id="con-left">
        <form action="/sr/order/save?houseId=${house.id}" method="post">
            <br>
            房屋图片:<br>
            ${house.picture}
            <br><br>
            <span>房屋描述:</span><br>
            ${house.describ}
            <br><br>
            <span>房屋使用规则:</span><br>
            ${house.rule}
            <br><br>
            <span style="font-size: 25px;color: red">￥${house.price}/晚</span><br><br>
            入住时间:&nbsp;<input type="date" name="inTime"/>
            退房时间:&nbsp;<input type="date" name="outTime"/><br>
            入住天数:&nbsp;<input type="number" name="num"/><br><br>
            <input type="submit" id="button" value="立即预定"/>
        </form>
    </div>
    <div id="con-right">
        <br>
        <span>
            房屋类型：${house.houseType}
        </span>
        <br>
        <table>
            <tr>
                <td>
                    出租类型：
                </td>
                <td>
                    ${house.rentType}
                </td>
            </tr>
            <tr class="tr-2">
                <td>
                    面积：
                </td>
                <td>
                    ${house.price}
                </td>
            </tr>
            <tr>
                <td>
                    联系方式：
                </td>
                <td>
                    ${house.creator.phone}
                </td>
            </tr>
            <tr class="tr-2">
                <td>
                    最少天数：
                </td>
                <td>
                    ${house.min_day}
                </td>
            </tr>
            <tr>
                <td>
                    最多天数：
                </td>
                <td>
                    ${house.max_day}
                </td>
            </tr>
            <tr class="tr-2">
                <td>
                    房东：
                </td>
                <td>
                    ${house.creator.name}
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>