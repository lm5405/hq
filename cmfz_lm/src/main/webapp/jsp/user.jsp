<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/extend/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.3.3.7.min.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>
    <script src="${pageContext.request.contextPath}/echarts/echarts.js"></script>
    <script type="text/javascript" src="https://www.echartsjs.com/gallery/vendors/echarts/map/js/china.js?_v_=1553896255267"></script>
    <script type="text/javascript" src="https://github.com/Luna829/incubator-echarts/blob/master/map/js/china.js" ></script>
    <title>用户管理系统</title>
    <script type="text/javascript">
         $(function () {
             $("#userTable").jqGrid({
                 styleUI:"Bootstrap",
                 url:"${pageContext.request.contextPath}/user/queryAll",
                 datatype:"json",
                 mtype:"post",
                 pager:"userpager",
                 rowNum:3,
                 rowList:[2,3,5,8],
                 viewrecords:true,
                 autowidth:true,
                 loadonce: true,
                 height:"100%",
                 viewrecords:true,
                 autowidth:true,
                 reloadAfterSubmit:true,
                 editurl:"${pageContext.request.contextPath}/user/edit",
                 multiselect:true,
                 colNames:["编号","电话","密码","盐","用户名","省份","城市","性别","个人签名","头像","状态","注册时间"/*,"修改状态"*/],
                 colModel:[{
                     name:"id"
                 },{
                     name:"phone",editable:true
                 },{
                     name:"password",editable:true
                 },{
                     name:"salt",editable:true
                 },{
                     name:"dharmaName",editable:true
                 },{
                     name:"province",editable:true
                 },{
                     name:"city",editable:true
                 },{
                     name:"gender",editable:true
                 },{
                     name:"personalSign",editable:true
                 },{
                     name:"profile",edittype:"file",editable:true,
                     formatter:function(cellvalue, options, rowObject){
                         return"<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/userImg/"+cellvalue+"'>"
                     }
                 },{
                     name:"status",editable:true,edittype:"select",editoptions:{value: {"1":"正常", "2":'冻结'}},
                 },{
                     name:"registTime",edittype:"date",editable:true
                 }]
             }).jqGrid("navGrid","#userpager",{},{
                 closeAfterEdit: true,
                 afterSubmit:function (response) {
                     $("#userTable").trigger("reloadGrid");
                     //发送ajax
                     $.ajaxFileUpload({
                         url:"${pageContext.request.contextPath}/user/upload",
                         fileElementId: "profile",
                         type:"post",
                         data:{"id":response.responseText},
                         success:function(){
                             $("#userTable").trigger("reloadGrid");
                         }
                     })
                     return "[true]"

                 }

             },{
                 closeAfteradd: true,
                 afterSubmit:function (response) {
                     //发送ajax
                     $.ajaxFileUpload({
                         url:"${pageContext.request.contextPath}/user/upload",
                         fileElementId: "profile",
                         type:"post",
                         data:{"id":response.responseText},
                         success:function(){
                             $("#userTable").trigger("reloadGrid");
                         }
                     })
                     return "[true]"
                     $("#userTable").trigger("reloadGrid");
                 }
             })
         })
       $(function () {
           var myChart = echarts.init(document.getElementById('main'));
           $.post("${pageContext.request.contextPath}/user/queryNumber",function (result) {
               var count = result.count;
               var month = result.month;
               // 指定图表的配置项和数据
               var option = {
                   title: {
                       text: '每月注册人数'
                   },
                   tooltip: {},
                   legend: {
                       data:'月份'
                   },
                   xAxis: {
                       data: month
                   },
                   yAxis: {},
                   series: [{
                       name: '月份',
                       type: 'bar',
                       data: count
                   }]
               };

               // 使用刚指定的配置项和数据显示图表。
               myChart.setOption(option);
           },"json")
       })

       $(function () {
           $.post("${pageContext.request.contextPath}/user/queryAll",function (data) {

           },"json")
       })
       $(function () {
           var myChart = echarts.init(document.getElementById('main1'));
       })

/*地图模块*/
         $(function () {
             var myChart = echarts.init(document.getElementById('provinceAndGender'));
             $.get("${pageContext.request.contextPath}/user/queryProvinceAndGender",function (result) {
                 option = {
                     title : {
                         text: '注册人数',
                         subtext: '纯属虚构',
                         left: 'center'
                     },
                     tooltip : {
                         trigger: 'item'
                     },
                     legend: {
                         orient: 'vertical',
                         left: 'left',
                         data:['男','女']
                     },
                     visualMap: {
                         min: 0,
                         max: 2500,
                         left: 'left',
                         top: 'bottom',
                         text:['高','低'],           // 文本，默认为数值文本
                         calculable : true
                     },
                     toolbox: {
                         show: true,
                         orient : 'vertical',
                         left: 'right',
                         top: 'center',
                         feature : {
                             mark : {show: true},
                             dataView : {show: true, readOnly: false},
                             restore : {show: true},
                             saveAsImage : {show: true}
                         }
                     },
                     series : [
                         {
                             name: '男',
                             type: 'map',
                             mapType: 'china',
                             roam: false,
                             label: {
                                 normal: {
                                     show: false
                                 },
                                 emphasis: {
                                     show: true
                                 }
                             },
                             data:
                             result.map1
                             /* {name: '天津',value: Math.round(Math.random()*1000)},*/
                         },
                         {
                             name: '女',
                             type: 'map',
                             mapType: 'china',
                             label: {
                                 normal: {
                                     show: false
                                 },
                                 emphasis: {
                                     show: true
                                 }
                             },
                             data:result.map2
                         }
                     ]
                 };
                 // 使用刚指定的配置项和数据显示图表。
                 myChart.setOption(option);
             },"json")
         })



    </script>

</head>
<body>

    <form action="${pageContext.request.contextPath}/user/importFile" method="post" enctype="multipart/form-data" id="fileFrom">
        <input type="file" name="file" >
        <%--<input type="button" class="btn btn-primary" id="btnimport" onclick="importFile()" value="一键导入">--%>
        <input type="submit">
    </form>

    <a class="btn btn-primary" id="btn" href="${pageContext.request.contextPath}/user/export">一键导出</a>
    <table id="userTable"></table>
    <div id="userpager"></div>
    <div id="main1" style="width: 600px;height:400px;"></div>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<%--栅格系统调整div 格式--%>
        <div class="row">
            <div class="col-md-6">
                <div id="provinceAndGender" style="width: 600px;height:400px;"></div>
            </div>
            <div class="col-md-6">
                <div id="main" style="width: 600px;height:400px;"></div>
            </div>
        </div>


</body>
</html>