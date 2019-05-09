$(
    initData()
)

//加载初始化数据
function initData()
{
    $.ajax
    ({
        url: "listHT",//后台请求的数据，
        dataType: "json",//数据格式
        type: "get",//请求方式
        success: function (result) {  //如果请求成功，返回数据。
            $("#h8").text(result.temData)
            $("#h6").text(result.humData)
            $("#h10").text(result.timeData)
            if (result.humData > 70 && result.humData < 75 || result.temData > 90 && result.temData < 95) {

                alert("温湿度异常预警");
            }
            else if (result.humData > 75 || result.temData > 75) {

                addWarning(result.temData,result.humData,result.timeData);

                alert("温湿度异常");
            }
        }

    })
}
function addWarning(hum,tem,time){
    $.ajax({
        dataType: "json",
        url: "/addWarning?tem="+tem+"&hum="+hum+"&time="+time,
        type: "get",
        success: function (data) {


        }


    })
}


