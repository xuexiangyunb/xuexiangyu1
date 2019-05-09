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
            $("#h10").text(result.timeData)
            $("#h6").text(result.humData)
            $("#h8").text(result.temData)
            if (result.humData > 70 && result.humData < 75 || result.temData > 90 && result.temData < 95) {

                alert("温湿度异常预警");
            }
            else if (result.humData > 75 || result.temData > 75) {

                $.ajax({
                    dataType: "json",
                    url: "/addWarning",
                    type: "get",

                    success: function (data) {


                    }


                })

                alert("湿度异常");
            }
        }

    })
}


