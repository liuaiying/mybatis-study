<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 徐慧怡
  Date: 2020/11/17
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>主页面</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<script  src="/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $.ajax({
            type : "post",
            url : "${pageContext.request.contextPath}/queryAllDept",
            success:function(data) {
                var html=" <option value='0'>请选择</option>";
                for(var i=0;i<data.length;i++){
                    html+="<option value="+data[i].deptno+">"+data[i].dname+"</option>"
                }
                $("#did").html(html);
            },
            error : function() {
                alert("出现错误")
            },
        });
    });

    function select(){
        var ename = $("#ename").val();
        var job = $("#job").val();
        var deptno =  $("#did").val();
        location.href="/queryAll?ename="+ename+"&job="+job+"&deptno="+deptno;
    }

    function xuanze(flag) {
        $('input[name=ids]').each(
            function () {
                $(this).prop('checked',flag);
            }
        )
    }
</script>
<body onload="cen()">
<div class="container">
    <div class="col-md-12 column">
        <div class="page-header">
            <h1>
                员工信息管理系统  <small>员工列表</small>
            </h1>
        </div>
    </div>
    <a href="/jsp/addEmp.jsp"><button type="button" class="btn btn-primary btn-lg active">添加员工信息</button></a>
    姓名：<input type="text" id="ename"> &nbsp;
    工作：<input type="text" id="job"> &nbsp;
    部门：<select id="did" name="deptno"></select>&nbsp;
    <button onclick="select()">查询</button><br><br>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form action="/delDataFilesByIds" method="post">
                <input type="submit" value="删除已选" style="height: 35px" width="30px">
            <table class="table table-hover table-striped">
                第${page}页  &nbsp; 共${pages}页
                <thead>
                <tr>
                    <th><input type="checkbox"  onclick="xuanze(this.checked)"></th>
                    <th class="active">员工编号</th>
                    <th class="success">员工姓名</th>
                    <th class="warning">工作</th>
                    <th class="danger">经理</th>
                    <th class="info">入职时间</th>
                    <th class="active">工资</th>
                    <th class="success">comm</th>
                    <th class="danger">员工图片</th>
                    <th class="warning">部门名称</th>
                    <th class="warning">部门城市</th>
                    <th class="danger">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="emp" items="${empBeans}" varStatus="number">
                    <tr>
                        <td><input type="checkbox" id="ids" name="ids" value="${emp.empno}"></td>
                        <td class="active">${number.index+1}</td>
                        <td class="success">${emp.ename}</td>
                        <td class="warning">${emp.job}</td>
                        <td class="danger">${emp.mgr}</td>
                        <td class="info">${emp.hiredate}</td>
                        <td class="active">${emp.sal}</td>
                        <td class="success">${emp.comm}</td>
                        <td class="danger"><img height="50px" width="50px" src="/ssmupload/${emp.img}"></td>
                        <td class="warning">${emp.deptBean.dname}</td>
                        <td class="warning">${emp.deptBean.loc}</td>
                        <td class="danger">
                            <a href="/queryById?empno=${emp.empno}"><button type="button">修改</button></a>
                            <a href="/delete?empno=${emp.empno}"><button type="button">删除</button></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </form>
            <center>
            <a href="${pageContext.request.contextPath}/queryAll">首页</a>
            <a href="${pageContext.request.contextPath}/queryAll?page=${page-1}" ${page!=1?"":"hidden"}>上一页</a>
            <a href="${pageContext.request.contextPath}/queryAll?page=${page+1}" ${page+1<=pages?"":"hidden"}>下一页</a>
            <a href="${pageContext.request.contextPath}/queryAll?page=${pages}">尾页</a>
            </center>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    function cen() {
        $("td,th,tr").addClass("text-center");
    }
</script>
</html>

