window.onload = function () {
    document.getElementById("form").onsubmit = function () {
        //调用用户校验方法  checkUsername():
        return checkPostName() && checkPostAge() && checkMissingtime() ;
    }
    // 给输入框绑定离焦事件
    document.getElementById("postName").onblur = checkPostName;
    document.getElementById("postAge").onblur = checkPostAge;
    document.getElementById("missingtime").onblur = checkMissingtime;

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
var strFullPath=window.document.location.href;
var strPath=window.document.location.pathname;
var pos=strFullPath.indexOf(strPath);
var prePath=strFullPath.substring(0,pos);
var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);

//校验姓名
function checkPostName() {
    var postName = document.getElementById("postName").value;
    //定义正则表达式
    var reg_postName = /^[\u4E00-\u9FA5\w]{1,20}$/;
    // 判断是否符合正则表达式的规则
    var flag = reg_postName.test(postName);
    //提示信息
    var s_postName = document.getElementById("s_postName");
    if (flag) {
        //提示正确
        s_postName.innerHTML = "<img width='35' height='25' src='"+prePath+postPath+"/imgs/user_vol_register_img/right.jpg'>";
    } else {
        //提示错误
        s_postName.innerHTML = "名称长度在1-20";
    }
    return flag;
}

//校验年龄
function checkPostAge() {
    var postAge = document.getElementById("postAge").value;
    var flag = 0;
    if (postAge >= 0 && postAge <= 120) {
        flag = 1;
    }
    //提示信息
    var s_postAge = document.getElementById("s_postAge");
    if (flag == 1) {
        //提示正确
        s_postAge.innerHTML = "<img width='35' height='25' src='"+prePath+postPath+"/imgs/user_vol_register_img/right.jpg'/>";
    } else {
        //提示错误
        s_postAge.innerHTML = "年龄在0-120";
    }
    return flag;
}

//校验失踪时间
function checkMissingtime() {
    var missingtime = document.getElementById("missingtime").value;
    //年月日格式
    var result = missingtime.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (result == null) return false;
    var d = new Date(result[1], result[3] - 1, result[4]);
    var flag=(d.getFullYear() == result[1] && d.getMonth() + 1 == result[3] && d.getDate() == result[4] );
    var s_missingtime = document.getElementById("s_missingtime");
    if (flag) {
        //提示正确
        s_missingtime.innerHTML = "<img width='35' height='25' src='"+prePath+postPath+"/imgs/user_vol_register_img/right.jpg'>";
    } else {
        //提示错误
        s_missingtime.innerHTML = "日期名格式有误";
    }
    return

    // var missingtime = document.getElementById("missingtime").value;
    // //定义正则表达式
    // var reg_missingtime = /^\d{4}-([1-9]|(1[0-2]))-[ ]$/;
    // // 判断是否符合正则表达式的规则
    // var flag = reg_missingtime.test(missingtime);
    // //提示信息
    // var s_missingtime = document.getElementById("s_missingtime");
    // if (flag) {
    //     //提示正确
    //     s_missingtime.innerHTML = "<img width='35' height='25' src='"+prePath+postPath+"/imgs/user_vol_register_img/right.jpg'>";
    // } else {
    //     //提示错误
    //     s_missingtime.innerHTML = "日期名格式有误";
    // }
    // return flag;
}

