$(function () {
    cabinetList();

});

var cabinetList = function () {
    var url = "listCabinets";
    var type = "json";
    data = {};
    $.post(url,
        data,
        function(result,status){
            alert(result.cabinetsList)

        },
        type);
};
var delRemark = function (rId) {

    if(confirm("你确定？")){
        var url = "delRemark.do";
        var type = "json";
        data = {};
        data.rId = rId;

        $.post(url,
            data,
            function(result,status){
                if(status == "success"){
                    if(result.RespCode == "0000"){

                        location.reload();
                    }else{
                        alert(result.RespDesc.toString());
                    }
                }else if(status == null){
                    alert("网络错误");
                }else{
                    alert("登录失败");
                }
            },
            type);
    };
};
var showUpdate = function (rId) {
    var  table = document.getElementById("table");
    table.style.display="none";
    var div = document.getElementById("updateDiv");
    div.style.display="inline";

    var p = document.getElementById("pId");
    p.innerHTML = rId;
};
var updateRemark = function () {
    var url = "updateRemark.do";
    var type = "json";
    data = {};
    data.rId = document.getElementById("pId").innerHTML;
    data.rRemark =document.getElementById("remarkInput").value ;
    data.rTime = document.getElementById("dateInput").value;
    data.rCharge = document.getElementById("teacherNameInput").value;
    $.post(url,
        data,
        function(result,status){
            if(status == "success"){
                if(result.RespCode == "0000"){
                    alert("修改成功！");
                }else{
                    alert(result.RespDesc.toString());
                }
            }else if(status == null){
                alert("网络错误");
            }else{
                alert("登录失败");
            }
        },
        type);
};