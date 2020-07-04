window.onload = function () {
    document.getElementById("form").onsubmit = function () {
        return checkVolname() && checkVolIdCard() && checkVolPhone() && checkVoldetailAddr() && checkVolEmail() && checkVolQQ();
    }
    // 给输入框绑定离焦事件
    document.getElementById("volname").onblur = checkVolname;
    document.getElementById("volIdCard").onblur = checkVolIdCard;
    document.getElementById("volPhone").onblur = checkVolPhone;
    document.getElementById("voldetailAddr").onblur = checkVoldetailAddr;
    // document.getElementById("volProfession").onblur = checkVolProfession;
    document.getElementById("volEmail").onblur = checkVolEmail;
    document.getElementById("volQQ").onblur = checkVolQQ;

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
//校验志愿者姓名
function checkVolname() {
    var volname = document.getElementById("volname").value;
    var flag = false;
    //提示信息
    var s_volname = document.getElementById("s_volname");
    if (volname==null||volname==""||volname=='undefined') {
        //提示错误
        s_volname.innerHTML = "姓名不能为空";
    } else {
       //提示正确
        s_volname.innerHTML = "<img width='35' height='25' src='"+prePath+postPath+"/imgs/user_vol_register_img/right.jpg'>";
        flag=true;
    }
    return flag;
}

//校验身份证号码
function checkVolIdCard() {
    var volIdCard = document.getElementById("volIdCard").value;
    //每一位的校验
    var wi;
    var ai;
    var sum=0;
    for(var i=0;i<18;i++){
        if(volIdCard.charAt(i)=="x"||volIdCard.charAt(i)=="X"){
            ai=10;
        }else{
            ai=parseInt(volIdCard.charAt(i));
        }
        wi=(Math.pow(2,17-i))%11;
        sum+=ai*wi;
    }
    var flag=false;
    //提示信息
    var s_volIdCard = document.getElementById("s_volIdCard");
    if (sum%11==1) {
        //提示正确
        s_volIdCard.innerHTML = "<img width='35' height='25' src='"+prePath+postPath+"/imgs/user_vol_register_img/right.jpg' />";
        flag=true;
    } else {
        //提示错误
        s_volIdCard.innerHTML = "身份证格式有误";
    }
    return flag;
}

//校验手机号码
function checkVolPhone() {
    var volPhone = document.getElementById("volPhone").value;
    //定义正则表达式
    var reg_volPhone = /^1[3456789]\d{9}$/;
    // 判断是否符合正则表达式的规则
    var flag = reg_volPhone.test(volPhone);
    //提示信息
    var s_volPhone = document.getElementById("s_volPhone");
    if (flag) {
        //提示正确
        s_volPhone.innerHTML = "<img width='35' height='25'src='"+prePath+postPath+"/imgs/user_vol_register_img/right.jpg' />";
    } else {
        //提示错误
        s_volPhone.innerHTML = "电话号码格式有误";
    }
    return flag;
}

//校验详细地址
function checkVoldetailAddr() {
    var voldetailAddr = document.getElementById("voldetailAddr").value;
    //提示信息
    var s_voldetailAddr = document.getElementById("s_voldetailAddr");
    var flag=false;
    if (voldetailAddr==null||voldetailAddr==""||voldetailAddr=='undefined') {
        //提示错误
        s_voldetailAddr.innerHTML = "详细地址不能为空";
    } else {
        //提示正确
        s_voldetailAddr.innerHTML = "<img width='35' height='25' src='"+prePath+postPath+"/imgs/user_vol_register_img/right.jpg' />";
        flag=true;
    }
    return flag;
}

//校验省份

//校验城市

//校验职业
// function checkVolProfession() {
//     var volProfession = document.getElementById("volProfession").value;
//     var flag = false;
//     //提示信息
//     var s_volProfession = document.getElementById("s_volProfession");
//     if (volProfession==""||volProfession==null||volProfession=='undefined') {
//         //提示错误
//         s_volProfession.innerHTML = "职业不能为空";
//     } else {
//         //提示正确
//         s_volProfession.innerHTML = "<img width='35' height='25' src='"+prePath+postPath+"/imgs/user_vol_register_img/right.jpg' />";
//         flag=true;
//     }
//     return flag;
// }
//校验email
function checkVolEmail() {
    var volEmail = document.getElementById("volEmail").value;
    //定义正则表达式
    var reg_volEmail = /^\w{1,20}\@\w{1,10}\.\w{1,10}$/;
    // 判断是否符合正则表达式的规则
    var flag = reg_volEmail.test(volEmail);
    //提示信息
    var s_volEmail = document.getElementById("s_volEmail");
    if (flag) {
        //提示正确s
        s_volEmail.innerHTML = "<img width='35' height='25' src='"+prePath+postPath+"/imgs/user_vol_register_img/right.jpg' />";
    } else {
        //提示错误
        s_volEmail.innerHTML = "email格式有误";
    }
    return flag;
}

//校验QQ
function checkVolQQ() {
    var volQQ = document.getElementById("volQQ").value;
    //定义正则表达式
    var reg_volQQ = /^\d{5,12}$/;
    // 判断是否符合正则表达式的规则
    var flag = reg_volQQ.test(volQQ);
    //提示信息
    var s_volQQ = document.getElementById("s_volQQ");
    if (flag) {
        //提示正确s
        s_volQQ.innerHTML = "<img width='35' height='25'src='"+prePath+postPath+"/imgs/user_vol_register_img/right.jpg' />";
    } else {
        //提示错误
        s_volQQ.innerHTML = "QQ格式有误";
    }
    return flag;
}