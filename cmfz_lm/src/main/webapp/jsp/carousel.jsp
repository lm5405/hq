<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<div class="page-header">
    <h2>轮播图管理</h2>
</div>
<script>

    $(function () {
        $("#carouselTable").jqGrid({
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/carousel/queryAll",
            datatype:"json",
            colNames:["编号","名称","图片","状态","上传时间"],
            colModel:[
                {name:"id"},
                {name:"title",editable:true},
                {name:"imgPath",editable:true,edittype:"file",formatter:function(cellvalue, options, rowObject){
                        return "<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/carouselPic/"+cellvalue+"'/>";
                    }
                },
                {name:"status",editable:true},
                {name:"createTime",editable:true,edittype:"date"}],
            pager:"#carouselPager",
            rowNum :3,
            rowList:[3,5,10],
            viewrecords:true,
            autowidth:true,
            editurl:"${pageContext.request.contextPath}/carousel/edit",
            height:"100%",
            multiselect:true,
            rownumbers:true
        }).jqGrid("navGrid","#carouselPager",{},{
            //修改部分
            closeAfterEdit:true,
            afterSubmit:function (response) {
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/carousel/upload",
                    fileElementId:"imgPath",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function () {
                        $("#carouselTable").trigger("reloadGrid");
                    }
                })
                return "[true]";
            }
        },{
            closeAfterAdd:true,
            //添加部分
            afterSubmit:function (response) {
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/carousel/upload",
                    fileElementId:"imgPath",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function () {
                        $("#carouselTable").trigger("reloadGrid");
                    }
                })
                return "[true]";
            }
         },{

        })
    })
</script>
<table id="carouselTable"></table>
<div id="carouselPager"></div>