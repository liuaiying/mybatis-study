<%--
  Created by IntelliJ IDEA.
  User: 86158
  Date: 2020/11/3
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script  src="/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $.ajax({
            type : "post",
            url : "/queryAllDept",
            dataType:"json",
            success : function(data) {
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
    })
</script>
<body>
<form action="/insert" enctype="multipart/form-data" method="post">
    员工姓名：<input id="ename" type="text" name="ename"><br>
    工作：<input id="job" type="text" name="job"><br>
    经理：<input id="mgr" type="text" name="mgr"><br>
    入职日期：<input id="hiredate" type="date" name="hiredate" required><br>
    工资：<input id="sal" type="text" name="sal"><br>
    comm：<input id="comm" type="text" name="comm" ><br>
    员工图片：<input type="file" name="fileName"><br>
<%--    部门：<input id="deptno" type="text" name="deptno"><br>--%>
    部门：<select id="did" name="deptno"></select><br>
    <input type="submit" value="添加"/>
</form>
</body>
</html>
