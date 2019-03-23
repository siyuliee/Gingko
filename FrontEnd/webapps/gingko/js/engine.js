const identify = localStorage.getItem("identify");
function engine(obj) {
    var val = document.getElementById(obj).value;
    $.post("https://www.gingko.tech/Gingko/servlet/SetEngine",
        {
            type:obj,
            url:val,
            identify:identify
        },function (data) {
             if(data.data=='finished'){
	      		alert("Success!Refresh to Function");
		}else if(data.result=='unfinished'){
			alert("Fail for SomeErr");
		}else{
			alert("Unknown Err");
		}
    });
}
//BackGround
let b_index = localStorage.getItem('b_index');
const background = document.getElementById("content");
if (b_index===null)
{
    localStorage.setItem('max','9');
    background.background="https://www.gingko.tech/gingko/img/"+1+".jpg";
    localStorage.setItem('b_index','1');
}
else
{
    background.background="https://www.gingko.tech/gingko/img/"+b_index+".jpg";
}

//KeyBoard
document.onkeydown=function(event){
    const e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode==27){ // 按 Esc
        //要做的事情
        location.href=this.location;
    }
    if(e && e.keyCode == 13){ // enter 键
        //大搜索框
        Search();
    }
    if (e.keyCode == 66 && e.ctrlKey)
    {
        Baidu();
    }
    if (e.keyCode == 71 && e.ctrlKey)
    {
        Google();
    }
    if ((e.keyCode == 37 && e.ctrlKey)||(e.keyCode == 37 && e.shiftKey))
    {
        //减一

        b_index =localStorage.getItem('b_index');
        let index = parseInt(b_index);
        if (index>1)
        {
            index = index - 1;
        }
        else
        {
            index = window.localStorage['max'];
        }
        localStorage.setItem("b_index",index);
        background.background="https://www.gingko.tech/gingko/img/"+index+".jpg";
    }
    if ((e.keyCode == 39 && e.ctrlKey)||(e.keyCode == 39 && e.shiftKey))
    {
        //加一

        b_index =localStorage.getItem('b_index');
        var index = parseInt(b_index);
        var max = parseInt(localStorage.getItem('max'))
        if (index<max)
        {
            index = index + 1;
        }
        else
        {
            index = '1';
        }
        localStorage.setItem("b_index",index);
        background.background="https://www.gingko.tech/gingko/img/"+index+".jpg";
    }
}
$('#BSR').on('click','',function(){
    Baidu();
});
$('#GSR').on('click','',function(){
    Google();
});
var Enter;
var EnterS;
var left;
var leftS;
var right;
var rightS;
if(identify === null){
	Enter = "https://www.baidu.com";
	EnterS = "https://www.baidu.com/s?ie=utf-8&wd=*";
	document.getElementById("Wuhan").placeholder = "百度搜索";
	left = "https://xueshu.baidu.com/";
	leftS = "https://xueshu.baidu.com/s?wd=*";
	document.getElementById("baidu").placeholder = "百度学术";
	right = "https://a.glgoo.top/";
	rightS = "https://a.glgoo.top/scholar?hl=zh-CN&q=*";
	document.getElementById("google").placeholder="Google 学术";
}else{
$.post("https://www.gingko.tech/Gingko/servlet/Engine",
    {
        identify:identify
    },
    function (data) {
    var info = data.data.result[0];
   // alert(JSON.stringify(info))
    	Enter = info.E;
    	EnterS = info.ES;
    	document.getElementById("Wuhan").placeholder=info.ESC;
    	left = info.B;
    	leftS = info.BS;
    	document.getElementById("baidu").placeholder=info.BSC;
    	right = info.G;
    	rightS = info.GS;
	document.getElementById("google").placeholder=info.GSC;
    });
}
function Baidu() {
    const B_key = document.getElementById("baidu");
    if (B_key.value == '')
    {
        window.open(left);
    }
    else
    {
        var KW= B_key.value;
        var url = leftS.split("*");
        window.open(url[0] + KW + url[1]);
    }
}
function Google() {
    const G_key = document.getElementById("google");
    if (G_key.value == '')
    {
        window.open(right);
    }
    else
    {
        var KW = G_key.value;
        var url = rightS.split("*");
        window.open(url[0] + KW + url[1]);
    }
}
function Search() {
    const Key = document.getElementById("Wuhan");
    if (Key.value == '')
    {
        window.open(Enter);
    }
    else
    {
        var keyword = Key.value;
        var url = EnterS.split("*");
        window.open(url[0] + keyword + url[1]);
    }
}
