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
        async : false,//是否异步请求
        success : function(result) {  //如果请求成功，返回数据。
            var  html="";
            for(var i=0;i<result.length;i++){
                html +="<span>"+result.humdata+"</span>";
            }
            $("#h6").html(html);
        },
    })
}