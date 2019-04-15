function loginindex(){

    if(document.getElementById("name").value==''||document.getElementById("pwd").value==''){
        alert("请输入用户名和密码");
    }else{
        var name,pass;
        name= document.getElementById("name").value;
        pass= document.getElementById("pwd").value;
        $.ajax({
            async:false,
            type: "get",
            url: "login",//注意路径  (后台登陆验证的方法)
            data:{"name":name,"password":pass},
            dataType:"text",
            success:function(data){
                if(data=="1"){
                    window.location.href="admin";
                }else{
                    alert("用户名或密码错误，登录失败");
                }
            }
        });
    }}
