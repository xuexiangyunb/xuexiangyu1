$(
    initData()
)



//加载初始化数据
function initData()
{
    $.ajax({
        url : "listHT",//后台请求的数据，
        dataType : "json",//数据格式
        type : "get",//请求方式
        success : function(result) {  //如果请求成功，返回数据。
            $("#h6").text(result.humData)
            $("#h8").text(result.temData)

        },
    })
}