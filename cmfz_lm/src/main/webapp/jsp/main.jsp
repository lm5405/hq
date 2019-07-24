<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>

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
</head>
<body>
    <%--标题导航栏--%>
    <jsp:include page="top.jsp"></jsp:include>
    <%--页面主体--%>
    <div class="container-fluid">
        <%--1行2列--%>
            <div class="row">
                <%--1列-功能菜单--%>
                <div class="col-sm-2">
                    <jsp:include page="left.jsp"></jsp:include>
                </div>
                    <div class="col-sm-10" id="contentDiv">
                        <jsp:include page="right.jsp"></jsp:include>
                    </div>
    </div>
    <%--页尾--%>
            <hr/>
     <div>
            <nav class="navbar navbar-default navbar-fixed-bottom">
                <div class="text-center" style="height: 50px;line-height: 50px">
                    <span>@百知教育 baizhi@zparkhr.com.cn</span>
                </div>
            </nav>
     </div>
</body>
</html>