/**
 * Created by zxw on 2017/7/9.
 */

$(function() {

    $.post("findProjectInfo", {}, function (data) {
        //alert(data.length);

        $("#projectList").empty();
        var appendHtml = "<option value=''></option>";

        for (var p = 0; p < data.length; p ++) {
            appendHtml += "<option value='";
            appendHtml += data[p].mainid;
            appendHtml += "'>"
            appendHtml += data[p].nodename;
            appendHtml += "</option>";
        }


        $("#projectList").append(appendHtml);
    });

    $("#projectList").change(function () {

        $.post("", {}, function () {

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