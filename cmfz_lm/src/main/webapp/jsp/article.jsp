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
    <%--editer--%>
    <script src="${pageContext.request.contextPath}/jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>
    <title>持明法洲文章管理</title>

    <script type="text/javascript">
        var editer;
        $(function () {
            editer= KindEditor.create('#editor_id',
                {width : '700px',
                    uploadJson:"${pageContext.request.contextPath}/article/upload",
                    fileManagerJson:"${pageContext.request.contextPath}/article/showAll",
                    filePostName:'file'
                }
            );
        })

        $(function () {
            $("#tableAticle").jqGrid({
                styleUI:"Bootstrap",
                url:"${pageContext.request.contextPath}/guru/queryAll",
                datatype:"json",
                mtype:"post",
                autowidth:true,
                height : '100%',
                pager:"pagerAticle",
                viewrecords: true,
                rowNum: 3,
                rowList:[3,5,6],
                editurl:"${pageContext.request.contextPath}/guru/edit",
                multiselect:true,
                rownumbers:true,
                subGrid : true,
                subGridRowExpanded : function(subgrid_id, row_id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id
                        + "' class='scroll'></table><div id='"
                        + pager_id + "' class='scroll'></div>");
                    jQuery("#" + subgrid_table_id).jqGrid(
                        {
                            url : "${pageContext.request.contextPath}/article/queryAllByGuruId?guruId=" + row_id,
                            datatype : "json",
                            styleUI:"Bootstrap",
                            autowidth:true,
                            multiselect:true,
                            rownumbers:true,
                            loadonce: true,
                            viewrecords: true,

                            editurl:"${pageContext.request.contextPath}/article/edit?rowId=" + row_id,
                            colNames : ["编号","大师编号","标题","内容","出版日期","操作"],
                            colModel : [{
                                name:"id"
                            },{
                                name:"guruId",editable:true,edittype:"select",editoptions: {value:getOptionValue()}
                            },{
                                name:"title",editable:true
                            },{
                                name:"content",editable:true,edittype:"textarea"
                            },{
                                name:"publishTime",editable:true,edittype:"date"
                            },{
                                name:"id",formatter:function (cellvalue,options,row) {
                                    var id = cellvalue;
                                    return "<button class='btn btn-primary' onclick='queryDetail(\""+row.id+"\")'>文章预览</button>"
                                }
                            }
                            ],
                            rowNum : 10,
                            rowList:[3,5,6,10,15],
                            pager : pager_id,
                            sortname : 'num',
                            sortorder : "asc",
                            height : '100%'
                        });
                    jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit : false,
                            add : true,
                            del : true
                        });
                },
                colNames:["编号","姓名","信息","状态","性别"/*,"操作"*/],
                colModel:[{
                    name:"id"
                },{
                    name:"name",editable:true
                },{
                    name:"profile",
                    editable:true,edittype:"file",
                    formatter:function(cellvalue, options, rowObject){
                        return "<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/imgHeader/"+cellvalue+"'/>"
                    }
                },{
                    name:"status",editable:true
                },{
                    name:"sex",editable:true
                }/*,{
                       name: "id",formatter:function(cellvalue, options, rowObject){
                           return"<button class='btn btn-primary' onclick='upload()'>文件上传</button>"
                       }*/
                ]
            }).jqGrid("navGrid","#pagerAticle",{},{

                closeAfteredit : true,
                afterSubmit:function (response) {
                    //发送ajax
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/guru/upload",
                        fileElementId: "profile",
                        type:"post",
                        data:{"id":response.responseText},
                        success:function(){
                            $("#" + subgrid_table_id).trigger("reloadGrid");
                        }
                    })
                    return "[true]"
                }
            },{
                //添加
                closeAfteradd: true,
                afterSubmit:function (response) {
                    //发送ajax
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/guru/upload",
                        fileElementId: "profile",
                        type:"post",
                        data:{"id":response.responseText},
                        success:function(){
                            $("#" + subgrid_table_id).trigger("reloadGrid");
                        }
                    })
                    return "[true]"
                }
            })

            //根据上师id添加文章
            function getOptionValue() {
                var options = "";
                var i = 0;
                $.ajax({
                    async: false,  //千万要记住加这个属性配置
                    type: "post",
                    url: "${pageContext.request.contextPath}/guru/queryAll",
                    success: function (data) {
                        for (i; i < data.length; i++) {
                            if (i != data.length - 1) {
                                options += data[i].id + ":" + data[i].name + ";";
                            } else {
                                options += data[i].id + ":" + data[i].name;
                            }
                        }
                    }
                });
                return options;
            }
        })
        //发送ajax 请求，打开模态框
        function upload() {
            $('#myModal').modal({
                keyboard: false,
                show:true
            })
            $.post("${pageContext.request.contextPath}/guru/queryAll1",function (arr) {
                for (var i=0;i<arr.length;i++){
                    $("#queryAllMaster").append($("<option></option>").text(arr[i].name).val(arr[i].id));
                }
            },"json");
        }
        //       添加文章,向后台发请求
        //       添加文章,向后台发请求
        function addArticle() {
            $.post("${pageContext.request.contextPath}/article/add",
                $("#ArticleForm").serialize(),
                function () {
                }, "json")
        }

        //查询文章预览
        function queryDetail(id){
            $.post("${pageContext.request.contextPath}/article/queryOneById",{id:id},function (data) {

                $('#myModal').modal({
                    keyboard: false,
                    show:true
                })
                $("#queryAllMaster").append($("<option></option>").text(data.guru.name).val(data.guru.id));
                /*$("#editor_id").text(data.content)*/
                editer.html(data.content);
                $("#ttital").val(data.title);
                $("#Pdate").val(data.publishTime);
            },"json")
        }


    </script>
</head>
<body>
<div class="page-header">
    <h1> 文章管理</h1>
</div>

<%--添加模态框--%>
<!-- Button trigger modal -->
<button class="btn btn-primary" id="upload" onclick="upload()">
    文件上传
</button>
<!-- Large modal -->
<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 800px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加文章</h4>
            </div>
            <%--在模态框体内添加表单--%>
            <form class="form-horizontal" id="ArticleForm">
                <div class="modal-body">
                    <input type="text" class="form-control" id="ttital" name="title" placeholder="文章标题">

                    <%--下拉框查询所有上师--%>
                    <select id="queryAllMaster" class="form-control" onclick="getOptionValue()">

                    </select>

                    <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                     &lt;strong id="aa"></strong&gt;
                    </textarea>
                </div>
                <div class="modal-body">
                    <input type="date" id="Pdate" class="form-control" name="publishTime" placeholder="出版日期">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="addArticle()">Save changes</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<table id="tableAticle">

</table>
<div id="pagerAticle">

</div>




</body>
</html>