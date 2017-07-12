<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>HTML5</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
</head>
<body style="margin: 36px;">
    <img src="" />
    <!-- <h1 th:text="${host}">Hello World</h1>-->
    <h1>2017年</h1>

    <span>工程列表：</span>
    <select id="projectList">

    </select>
    <button id="findProjectBtn">确定</button>

    <br/>

    <span>请输入工程编号：</span><input type="text" id="projectNumber"/>&nbsp;<button id="mybtn">确定</button>
    <br/><br/>
    <div id="result" style="border: 1px solid #9b6ccc; height: 200px; width: 400px;"></div>

</body>

<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js" type="text/javascript" />

<script type="text/javascript">

    $(function() {



        $("#findProjectBtn").click(function () {
            $.post("findProjectInfo", {"mid2": 10086}, function (data) {
                alert(data.length);

                $("#projectList").empty();
                var appendHtml = "";



                    appendHtml += "<option ";

                    appendHtml += ">fff";

                    appendHtml += "</option>";
                //}


                $("#projectList").append(appendHtml);
            })
        });

        $("#mybtn").click(function () {
            $.get("hello", {"projectNumber": $("#projectNumber").val()}, function (data) {
                $("#result").empty();
                var html = "<p>";
                html += data;
                html += "</p>";
                $("#result").append(html);
            })
        });
        
    });

</script>


</html>