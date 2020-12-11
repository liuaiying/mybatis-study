<%--
  Created by IntelliJ IDEA.
  User: 86158
  Date: 2020/11/4
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<script  src="/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $.ajax({
            type : "post",
            url : "${pageContext.request.contextPath}/queryAllDept",
            dataType:"json",
            success : function(data) {
                var html=" <option value='0'>请选择</option>";
                for(var i=0;i<data.length;i++){
                    var v='${empBean.deptno}';
                    // alert(v)
                    if(v==data[i].deptno){
                        html+="<option value="+data[i].deptno+" selected>"+data[i].dname+"</option>"
                    }else {
                        html+="<option value="+data[i].deptno+">"+data[i].dname+"</option>"
                    }
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
<form action="${pageContext.request.contextPath}/update" enctype="multipart/form-data" method="post">
    <input id="img" type="hidden" name="img" value="${empBean.img}"><br>
    <input id="empno" type="hidden" name="empno" value="${empBean.empno }">
    员工姓名：<input id="ename" type="text" name="ename" value="${empBean.ename}"><br>
    工作：<input id="job" type="text" name="job" value="${empBean.job }"><br>
    经理：<input id="mgr" type="text" name="mgr" value="${empBean.mgr }"><br>
    入职日期：<input id="hiredate" type="date" name="hiredate" value="${empBean.hiredate }"><br>
    工资：<input id="sal" type="text" name="sal" value="${empBean.sal }"><br>
    comm:<input id="comm" type="text" name="comm" value="${empBean.comm }"><br>
    旧照片：<img height="50px" width="50px" src="/ssmupload/${empBean.img}"><br>
    新照片：<input type="file" name="fileName"><br>
    部门：<select id="did" name="deptno"></select><br>
    <input type="submit" value="修改"/>
</form>
</body>
</html>