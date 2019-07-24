<%@ page pageEncoding="UTF-8" isELIgnored="false" %>

<%--巨幕--%>
<div class="jumbotron">
    <div class="container">
        <strong> <h2>欢迎来到持明法洲后台管理系统</h2> </strong>
    </div>
</div>

<%--轮播图--%>
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="${pageContext.request.contextPath}/img/1.png" class="img-responsive center-block">
            <div class="carousel-caption">
                ...
            </div>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/img/2.png" class="img-responsive center-block">
            <div class="carousel-caption">
                ...
            </div>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/img/3.png" class="img-responsive center-block">
            <div class="carousel-caption">
                ...
            </div>
        </div><div class="item">
        <img src="${pageContext.request.contextPath}/img/4.png" class="img-responsive center-block">
        <div class="carousel-caption">
            ...
        </div>
    </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>