<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="../jqgrid/extend/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="../boot/js/jquery-3.3.1.min.js"></script>
    <script src="../boot/js/bootstrap.3.3.7.min.js"></script>
    <script src="../jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="../jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <title>持明法洲后台管理系统</title>
    <script>
        function formSubmit() {
            $.ajax({
                url:"${pageContext.request.contextPath}/admin/login",
                type:"post",
                dataType:"json",
                data:$("#loginForm").serialize(),
                success:function(data){
                    if(data.code==200){
                        location.href="${pageContext.request.contextPath}/jsp/main.jsp";
                    }else{
                        $("#message").html(data.message);
                    }
                }
            })
        }
    </script>
</head>
<body>
    <form id="loginForm" action="javascript:void(0)">
        <div class="form-group">
            <label for="examname">username</label>
            <input type="text" class="form-control" name="username" id="examname" placeholder="username">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password">
        </div>
        <button  class="btn btn-default" onclick="formSubmit()">Submit</button>
        <span id="message" style="color: red"></span>
    </form>
</body>
</html>