<%--
  ~ Copyright [2018] [ kong&xiang ]
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<%--
  Created by IntelliJ IDEA.
  User: lantoev
  Date: 2018/6/12
  Time: 下午4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>注册登录界面</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css"/>
    <style>
        #container,#row{
            height: 20px; ;
            background-color: greenyellow;
        }
        #left{
            background-color: darkcyan;
            height: 100px;
        }
        #right{
            height: 100px;
            background-color: darkblue;
        }
        .space-div{
            background-color: greenyellow;
            height: 100px;

        }
    </style>
</head>
<body>
<div id="row" class="layui-row layui-col-space1">
    <div id="left" class="layui-col-lg6">6/12 </div>

</div>

<div class="space-div layui-col-md4 layui-col-md-offset4">
    1/3 offset3
</div>

<div class="layui-row layui-col-space8">
    <div class="space-div layui-col-md4">
        1/3
    </div>
    <div class="space-div layui-col-md4">
        1/3
    </div>
    <div class="space-div layui-col-md4">
        1/3
    </div>
</div>


    <div class="layui-row ">
        <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
            移动：6/12 | 平板：6/12 | 桌面：4/12
        </div>
        <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
            移动：6/12 | 平板：6/12 | 桌面：4/12
        </div>
        <div class="layui-col-xs4 layui-col-sm12 layui-col-md4">
            移动：4/12 | 平板：12/12 | 桌面：4/12
        </div>
        <div class="layui-col-xs4 layui-col-sm7 layui-col-md8">
            移动：4/12 | 平板：7/12 | 桌面：8/12
        </div>
        <div class="layui-col-xs4 layui-col-sm5 layui-col-md4">
            移动：4/12 | 平板：5/12 | 桌面：4/12
        </div>
    </div>
    <div class="layui-fluid" style="background-color: darkgoldenrod">
        常规布局（以中型屏幕桌面为例）：
        <div class="layui-row">
            <div class="layui-col-md9">
                你的内容 9/12
            </div>
            <div class="layui-col-md3">
                你的内容 3/12
            </div>
        </div>
    </div>


<div class="layui-progress" lay-showPercent="true">
    <div class="layui-progress-bar layui-bg-red" lay-percent="1/3"></div>
</div>

<div class="layui-progress" lay-showPercent="yes">
    <div class="layui-progress-bar layui-bg-blue" lay-percent="30%"></div>
</div>

<div class="layui-progress layui-progress-big" lay-showPercent="yes">
    <div class="layui-progress-bar layui-bg-green" lay-percent="50%"></div>
</div>

<!-- panel -->
<div class="layui-bg-gray layui-card">
    <div class="layui-card-header">卡片面板</div>
    <div class="layui-card-body">
        卡片式面板面板通常用于非白色背景色的主体内<br>
        从而映衬出边框投影
    </div>
</div>
<i class="layui-icon layui-icon-face-smile" style="font-size: 30px; color: #1E9FFF;"></i>

<div class="layui-collapse layui-bg-orange" lay-accordion>
    <div class="layui-colla-item">

        <h2 class="layui-colla-title">杜甫<i class="layui-icon layui-icon-face-smile" style="font-size: 30px; color: #1E9FFF;"></i>
        </h2>
        <div class="layui-colla-content layui-show">内容区域</div>
    </div>
    <div class="layui-colla-item">

        <h2 class="layui-colla-title ">李清照</h2>
        <div class="layui-colla-content ">内容区域
            <i class="layui-colla-icon layui-icon-face-smile"></i></div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">鲁迅</h2>
        <div class="layui-colla-content ">内容区域</div>
    </div>
</div>
<div class="layui-col-md12 layui-col-lg12">
<div class="layui-side-scroll " style="width:100%;height: 900px;">
    <embed src="http://www.umlchina.com/training/umlchina_01_overview.pdf"
           id="review" style="width:100%;height: 80%;" >
    </embed>
</div>
</div>

<div  id="data_table_page"></div>

</body>

</html>
<!-- js -->
<c:import url="base/js-dependency.jsp"/>
