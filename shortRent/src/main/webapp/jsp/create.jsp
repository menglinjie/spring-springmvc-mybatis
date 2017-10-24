<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>创建房屋</title>
    <style type="text/css">
        table {
            margin-top: 45px;
            margin-left: 332px;
        }

        a {
            color: #F75000;
            text-decoration: none;
            font-size: 18px;
        }

        #button {
            color: #FFFFFF;
            background-color: #5FBA00;
            cursor: pointer;
            float: inherit;
            width: 75px;
            height: 28px;
            border-color: #459A00;
            border-style: solid;
            border-width: 1px;
            font-family: 微软雅黑;
            font-size: 15px;
            font-weight: bold;
        }

    </style>
</head>
<body>
<form action="/sr/house/save" enctype="multipart/form-data" method="post">
    <table>
        <caption><span style="font-size: 30px;color: #F75000">创建房屋</span></caption>
        <tr>
            <td><br>
                <span style="color: red">*</span>房屋名称:&nbsp;&nbsp;
            </td>
            <td><br>
                <input type="text" name="name"/>
            </td>
            <td><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                全额退款日:&nbsp;&nbsp;
            </td>
            <td><br>
                <input type="date"/>
            </td>
        </tr>
        <tr>
            <td>
                发票:&nbsp;&nbsp;
            </td>
            <td>
                <input type="radio"/>提供<input type="radio"/>不提供
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color: red">*</span>日租价:&nbsp;&nbsp;
            </td>
            <td>
                <input type="number" name="price"/>元/天
            </td>
        </tr>
        <tr>
            <td>
                <span style="color: red">*</span>出租类型:&nbsp;&nbsp;
            </td>
            <td>
                <input type="radio" name="rentType" value="1"/>整租
                <input type="radio" name="rentType" value="2"/>单间
                <input type="radio" name="rentType" value="3"/>合租
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color: red">*</span>房屋描述:&nbsp;&nbsp;
            </td>
            <td>
                <textarea name="describ"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                <span style="color: red">*</span>房屋类型:&nbsp;&nbsp;
            </td>
            <td>
                <input type="radio" name="houseType" value="1"/>酒店
                <input type="radio" name="houseType" value="2"/>民宿
                <input type="radio" name="houseType" value="3"/>主题
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color: red">*</span>使用规则:&nbsp;&nbsp;
            </td>
            <td>
                <textarea name="rule"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                <span style="color: red">*</span>面积:&nbsp;&nbsp;
            </td>
            <td>
                <input type="number" name="area"/>
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                设施服务:&nbsp;&nbsp;
            </td>
            <td>
                <textarea></textarea>
            </td>
        </tr>
        <tr>
            <td>
                可住人数:&nbsp;&nbsp;
            </td>
            <td>
                <select>
                    <option value="">1</option>
                    <option value="">2</option>
                    <option value="">3</option>
                </select>
            </td>
            <%--<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
            <%--<span style="color: red">*</span>地址:&nbsp;&nbsp;--%>
            <%--</td>--%>
            <%--<td>--%>
            <%--<textarea name="address"></textarea>--%>
            <%--</td>--%>
        </tr>
        <tr>
            <td>
                床位数:&nbsp;&nbsp;
            </td>
            <td>
                <select>
                    <option value="">1</option>
                    <option value="">2</option>
                    <option value="">3</option>
                </select>
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                付款规则:&nbsp;&nbsp;
            </td>
            <td>
                <select>
                    <option value="">严格</option>
                    <option value="">中等</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                床型:&nbsp;&nbsp;
            </td>
            <td>
                <select>
                    <option value="">双人床</option>
                    <option value="">大床房</option>
                </select>
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color: red">*</span>房屋图片:&nbsp;&nbsp;
            </td>
            <td>
                <input type="file" name="picture"/>
            </td>
        </tr>
        <tr>
            <td>
                卫生间数：&nbsp;&nbsp;
            </td>
            <td>
                <select>
                    <option value="">1</option>
                    <option value="">2</option>
                </select>
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color: red">*</span>地址：
            </td>
            <td>
                <textarea name="address"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                入住时间：
            </td>
            <td>
                <select>
                    <option value="">12:00</option>
                    <option value="">14:00</option>
                </select>
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                退房时间：
            </td>
            <td>
                <select>
                    <option value="">12:00</option>
                    <option value="">14:00</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <span style="color: red">*</span>最小天数：
            </td>
            <td>
                <input type="number" name="min_day"/>天
            </td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color: red">*</span>最大天数：
            </td>
            <td>
                <input type="number" name="max_day"/>天
            </td>
        </tr>
        <tr>
            <td colspan="4"><br>
                <input type="submit" id="button" value="提交审核"/>&nbsp;&nbsp;
                <a href="jsp/list.jsp">返回</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>