<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/echarts/echarts.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="https://www.echartsjs.com/gallery/vendors/echarts/map/js/china.js?_v_=1553896255267"></script>
    <script type="text/javascript" src="https://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">
        $(function () {
            var myChart = echarts.init(document.getElementById('provinceAndGender'));
            var goEasy = new GoEasy({
                appkey: 'BC-eae9b507657d4c769b70907caebfa6c1'
            });
            //
            goEasy.subscribe({
                channel:'demo_channel',
                onMessage: function(message){
                    var data = JSON.parse(message.content);
                    alert(data)
                    // 指定图表的配置项和数据
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
                }
            )
        }
        )


    </script>


</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="provinceAndGender" style="width: 600px;height:400px;"></div>


</body>
</html>