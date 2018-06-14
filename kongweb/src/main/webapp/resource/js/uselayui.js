/*
 * Copyright [2018] [ kong&xiang ]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//一般直接写在一个js文件中
layui.use(['layer', 'form','element','laydate','laypage'], function(){
    var layer = layui.layer
        ,form = layui.form
        ,elem = layui.element
        ,laydate=layui.laydate;
    var laypage = layui.laypage;

    laypage.render({
        elem:'data_table_page',
        count:5000,
        limits:['prev', 'page', 'next','count','limit','refresh'],
        limit:20,
        theme: '#c00',
        jump:function(obj,first){
            if (!first) {
                //obj包含了当前分页的所有参数，比如：
                layer.alert(obj.curr);
                console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                console.log(obj.limit); //得到每页显示的条数
            }else{
                layer.alert(obj.curr);
            }
        }
    })

    //layer.msg('hello');
});