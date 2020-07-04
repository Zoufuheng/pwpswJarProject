window.onload = function () {
    document.getElementById("form").onsubmit = function () {
        //调用用户校验方法  checkUsername():
        return checkUsername() && checkUserNickname() && checkUserPassword() && checkUserAge() && checkUserPhone();
    }
    // 给输入框绑定离焦事件
    document.getElementById("username").onblur = checkUsername;
    document.getElementById("userNickname").onblur = checkUserNickname;
    document.getElementById("userPassword").onblur = checkUserPassword;
    document.getElementById("userAge").onblur = checkUserAge;
    document.getElementById("userPhone").onblur = checkUserPhone;
    // document.getElementById("email").onblur = checkEmail;

    // start验证码功能
    $(function () {
        var show_num = [];
        draw(show_num);
        $("#canvas").on('click', function () {
            draw(show_num);
        })
        $(".btn").on('click', function () {
            var val = $(".input-val").val().toLowerCase();
            var num = show_num.join("");
            if (val == '') {
                alert('请输入验证码！');
            } else if (val == num) {
                // alert('提交成功！');
                // $(".input-val").val('');

            } else {
                alert('验证码错误！请重新输入！');
                $(".input-val").val('');
                // draw(show_num);
            }
        })
    })

//生成并渲染出验证码图形
    function draw(show_num) {
        var canvas_width = $('#canvas').width();
        var canvas_height = $('#canvas').height();
        var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
        var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
        canvas.width = canvas_width;
        canvas.height = canvas_height;
        var sCode = "a,b,c,d,e,f,g,h,i,j,k,m,n,p,q,r,s,t,u,v,w,x,y,z,A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
        var aCode = sCode.split(",");
        var aLength = aCode.length;//获取到数组的长度
        for (var i = 0; i < 4; i++) { //这里的for循环可以控制验证码位数（如果想显示6位数，4改成6即可）
            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
            // var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
            var deg = Math.random() - 0.5; //产生一个随机弧度
            var txt = aCode[j];//得到随机的一个内容
            show_num[i] = txt.toLowerCase();
            var x = 10 + i * 20;//文字在canvas上的x坐标
            var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
            context.font = "bold 23px 微软雅黑";
            context.translate(x, y);
            context.rotate(deg);
            context.fillStyle = randomColor();
            context.fillText(txt, 0, 0);
            context.rotate(-deg);
            context.translate(-x, -y);
        }
        for (var i = 0; i <= 5; i++) { //验证码上显示线条
            context.strokeStyle = randomColor();
            context.beginPath();
            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.stroke();
        }
        for (var i = 0; i <= 30; i++) { //验证码上显示小点
            context.strokeStyle = randomColor();
            context.beginPath();
            var x = Math.random() * canvas_width;
            var y = Math.random() * canvas_height;
            context.moveTo(x, y);
            context.lineTo(x + 1, y + 1);
            context.stroke();
        }
    }

//得到随机的颜色值
    function randomColor() {
        var r = Math.floor(Math.random() * 256);
        var g = Math.floor(Math.random() * 256);
        var b = Math.floor(Math.random() * 256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }

// end验证码功能
}

//获取路径
var strFullPath = window.document.location.href;
var strPath = window.document.location.pathname;
var pos = strFullPath.indexOf(strPath);
var prePath = strFullPath.substring(0, pos);
var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);

//校验用户名
function checkUsername() {
    var username = document.getElementById("username").value;
    //定义正则表达式
    var reg_username = /^\w{4,12}$/;
    // 判断是否符合正则表达式的规则
    var flag = reg_username.test(username);
    //提示信息
    var s_username = document.getElementById("s_username");
    if (flag) {
        //提示正确
        s_username.innerHTML = "<img width='35' height='25' src='" + prePath + postPath + "/imgs/user_vol_register_img/right.jpg'>";
    } else {
        //提示错误
        s_username.innerHTML = "昵称长度在4-12";
    }
    return flag;
}

//校验昵称
function checkUserNickname() {
    var userNickname = document.getElementById("userNickname").value;
    //定义正则表达式
    var reg_userNickname = /^[\u4E00-\u9FA5\w]{1,12}$/;
    // 判断是否符合正则表达式的规则
    var flag = reg_userNickname.test(userNickname);
    //提示信息
    var s_userNickname = document.getElementById("s_userNickname");
    if (flag) {
        //提示正确
        s_userNickname.innerHTML = "<img width='35' height='25' src='" + prePath + postPath + "/imgs/user_vol_register_img/right.jpg'>";
    } else {
        //提示错误
        s_userNickname.innerHTML = "昵称格式有误";
    }
    return flag;
}

//校验密码
function checkUserPassword() {
    var userPassword = document.getElementById("userPassword").value;
    //定义正则表达式
    var reg_userPassword = /^\w{6,12}$/;
    // 判断是否符合正则表达式的规则
    var flag = reg_userPassword.test(userPassword);
    //提示信息
    var s_userPassword = document.getElementById("s_userPassword");
    if (flag) {
        //提示正确
        s_userPassword.innerHTML = "<img width='35' height='25' src='" + prePath + postPath + "/imgs/user_vol_register_img/right.jpg' />";
    } else {
        //提示错误
        s_userPassword.innerHTML = "密码应在6-12位";
    }
    return flag;
}

//校验年龄
function checkUserAge() {
    var userAge = document.getElementById("userAge").value;
    var flag = 0;
    if (userAge >= 10 && userAge <= 100) {
        flag = 1;
    }
    //提示信息
    var s_userAge = document.getElementById("s_userAge");
    if (flag == 1) {
        //提示正确
        s_userAge.innerHTML = "<img width='35' height='25' src='" + prePath + postPath + "/imgs/user_vol_register_img/right.jpg'/>";
    } else {
        //提示错误
        s_userAge.innerHTML = "年龄在10-100";
    }
    return flag;
}

//校验省份

//校验城市

//校验电话号码
function checkUserPhone() {
    var userPhone = document.getElementById("userPhone").value;
    //定义正则表达式
    var reg_userPhone = /^1[3456789]\d{9}$/;
    // 判断是否符合正则表达式的规则
    var flag = reg_userPhone.test(userPhone);
    //提示信息
    var s_userPhone = document.getElementById("s_userPhone");
    if (flag) {
        //提示正确
        s_userPhone.innerHTML = "<img width='35' height='25' src='" + prePath + postPath + "/imgs/user_vol_register_img/right.jpg' />";
    } else {
        //提示错误
        s_userPhone.innerHTML = "电话号码格式有误";
    }
    return flag;
}

//校验email
// function checkEmail() {
//     var email = document.getElementById("email").value;
//     //定义正则表达式
//     var reg_email = /^\w{1,20}\@\w{1,10}\.\w{1,10}$/;
//     // 判断是否符合正则表达式的规则
//     var flag = reg_email.test(email);
//     //提示信息
//     var s_email = document.getElementById("s_email");
//     if (flag) {
//         //提示正确s
//         s_email.innerHTML = "<img width='35' height='25' src='"+prePath+postPath+"/imgs/user_vol_register_img/right.jpg'/>";
//     } else {
//         //提示错误
//         s_email.innerHTML = "email格式有误";
//     }
//     return flag;
// }

