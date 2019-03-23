var lock = "close";
function OpenWeb(obj) {
    var a = obj.id.split("*");
    var id = a[0];
    var url = a[1];
    if (lock == "open"){
        document.getElementById(obj.id).remove();
        Delete(id);
    } else if (lock == "close"){
        window.open(url);
    }
}
//Delete
function Delete(id) {
    const identify = localStorage.getItem("identify");
    const url = url_path+'DeleteUrl';
    $.post(
        url,
        {
            identify:identify,
            id:id
        },
        function(data){
            if (data['status']=='finished')
            {
                alert("Success！Refresh to See New View >>>")
            }
            else
            {
                alert("Fail with Exception!")
            }
        });
}
$(document).ready(function(){
    $('#DeleteUrl').click(function () {
        if (lock == "open"){
            location.href="https://www.gingko.tech";
        } else if (lock == "close"){
            document.getElementById("DeleteUrl").innerText="删除模式";
            lock = "open";
        }
    })

    $('#send').click(function(){
        var select =$('input:radio:checked').val();
        //alert(image_picture);
        var url_send = url_path + "AddUrl";
        var name = document.getElementById("url_name").value;
        var url = document.getElementById("url").value;
        var identify = localStorage.getItem("identify");

        $.post(url_send,
             {
                 identify: identify,
                 img: image_picture,
                 name: name,
                 url: url,
                 type: select
             },
            function(data) {
                if (data.status == "finished")
                {
                    alert("Success! Refresh now!");
                    location.href="https://www.gingko.tech";
                }
                else if (data.status == "unfinished")
                {
                    alert("Fail！Something Goes Wrong!");
                }
            }
        );
    });
    //View
    var flag = localStorage.getItem("identify");
    if (flag === null){
        document.getElementById("name_page").innerText="Login or Sign";
    }else {
        document.getElementById("name_page").innerText=localStorage.getItem("nickname");
    }

    $("#name_page").click(function () {
        if (flag === null){
            $("#login").fadeToggle(500);
        }
        else{
            $("#right").fadeToggle(500);
            $("#upload").fadeOut(500);
        }
    });

    $("#addUrl").click(function () {
        $("#upload").fadeToggle(500);
    });

    //Weather	
     const url = "https://free-api.heweather.com/s6/weather/forecast?key=79ec14a04e8e4a68be2d9391fbf49aff&location="+returnCitySN["cip"];
     $.get(url,function(data){
     var add = data['HeWeather6'][0]['basic'];
		var country = add['cid'].substr(0,2);
		if(country == 'US'){
			document.getElementById("address").innerText = add['cnty']+"/"+add['location'];
		}else{
			document.getElementById("address").innerText = add['cnty']+"/"+add['admin_area']+"/"+add['parent_city'];
		}
        	var add = data['HeWeather6'][0]['daily_forecast'][0];
        	document.getElementById("temp").innerText = "白天："+add['cond_txt_d']+"/夜晚："+add['cond_txt_n']+"/温度："+add['tmp_min']+"~"+add['tmp_max']+"度/风向："+add['wind_dir']+",风力："+add['wind_sc']+"级/湿度："+add['hum'];
    });

    //Login
    $("#lgbutton").click(function(){
        const mail = document.getElementById("mail").value;
        const passport = document.getElementById("passport").value;
        const url = url_path+'Login';
        $.post(
            url,
            {
                mail:mail,
                passport:passport
            },
            function(data){
                if (data['status']=='success')
                {
                    localStorage.setItem("identify",data['identify']);
                    localStorage.setItem('nickname',data['nickname']);
                    alert("Login Success！Refresh Page Now >>>")
                    location.href="https://www.gingko.tech";
                }
                else if (data['status'] == '404')
                {
                    alert("Mail is not Signed");
                }
                else if (data['status'] == 'fail')
                {
                    alert("Please Ensure Your Passport")
                }else {
                    alert("Little Bug on Server")
                }
            });
    });
    //Sign
    $("#sgbutton").click(function(){
        const mail = document.getElementById("mail").value;
        const passport = document.getElementById("passport").value;
        const nickname = document.getElementById("nickname").value;
        const url = url_path+'Sign';
        $.post(
            url,
            {
                mail:mail,
                passport:passport,
                nickname:nickname
            },
            function(data){
                alert(data['status']);
                if (data['status']=='finished')
                {
                    localStorage.setItem('identify',data['identify']);
                    localStorage.setItem('nickname',nickname);
                    alert("Success！Refresh Page Now >>>")
                    location.href="https://www.gingko.tech";
                }
                else if (data['status'] == 'exist')
                {
                    alert("Mail has been Used");
                }
                else
                {
                    alert("Little Bug on Server")
                }
            });
    });

    //Change Music List
    if (document.getElementById("musicList").value ==""){
        document.getElementById("changeMusic").innerText = "获取歌单";
    }
    $("#changeMusic").mouseleave(function () {
        if (document.getElementById("musicList").value != ""){
            document.getElementById("changeMusic").innerText = "确认";
        }
    })
    $("#changeMusic").click(function () {
        const identify = localStorage.getItem("identify");
        const musicid = document.getElementById("musicList").value;
        const url = url_path+'UpdateMusic';
        if (musicid == ""){
            window.open("https://music.163.com/#/discover/playlist");
        } else{
        $.post(
            url,
            {
                identify:identify,
                musicid:musicid
            },
            function(data){
                if (data['status']=='finished')
                {
                    localStorage.setItem("musicList",musicid);
                    alert("Success！Refresh MusicList Now >>>")
                    document.getElementById("music").src="https://www.gingko.tech/gingko/html/music.html?t="+Math.random();
                }else if (data['status']=='unfinished'){
                    alert("Failure！Task is Unfinished >>>")
                }
                else
                {
                    alert("Fail with Exception!")
                }
            });
	}
    });
    //Change nickName
    $("#changeNick").click(function () {
        const identify = localStorage.getItem("identify");
        const nickname = document.getElementById("changeNickname").value;
        const url = url_path+'UpdateNickname';
        $.post(
            url,
            {
                identify:identify,
                nickname:nickname
            },
            function(data){
                if (data['status']=='finished')
                {
                    window.localStorage['nickname'] = nickname;
                    alert("Success！Refresh Page Now >>>")
                    location.href="https://www.gingko.tech";
                }else if (data['status']=='unfinished'){
                    alert("Exception happen!")
                } else
                {
                    alert("Fail with Exception!")
                }
            });
    });

    //Clock
    setInterval(function () {
        const d = new Date();
        let s = d.getSeconds();
        let h = d.getHours();
        let min = d.getMinutes();
        const y = d.getFullYear();
        const m = d.getMonth();
        const day = d.getDay();
        const date_t = d.getDate();
        if (s<10){
            s='0'+s;
        }
        if (min<10){
            min='0'+min;
        }
        if (h<10){
            h='0'+h;
        }
        document.getElementById("second").innerText=s;
        document.getElementById("minute").innerText=min;
        document.getElementById("hour").innerText=h;
        var a = ["日","一","二","三","四","五","六"];
        document.getElementById("date").innerText=y+"/"+(m+1)+"/"+date_t+"   "+"星期"+a[day];
    },1000);

    $("#exit").click(function () {
        localStorage.removeItem("identify");
        location.href = 'index.html';
    });

    $("#left").click(function () {
        $("#right").fadeOut(500);
        $("#upload").fadeOut(500);
    })

    //Load Info
    const BS_content = document.getElementById('bs_tab');
    const GS_content = document.getElementById('gs_tab');
    const identify = localStorage.getItem("identify");
    if (identify===null){
        alert("Login for more Function!");
    }else {
        $(function(){
            getInfo(BS_content,"bs");
        });

        $(function(){
            getInfo(GS_content,"gs");
        });
    }

    function getInfo(obj,type) {
        $.ajax({
            url: url_path+"GetUrl",
            data: {identify: identify,type:type},
            method: 'post',
            dataType: 'json',
            success: function(data) {
                var json_data = data.data.result;
                var row = json_data.length;
                //alert(row)
                var index;
                var i=0;
                for(index=0;index<row;index++) {
                    var tr = document.createElement("tr");
                    var cum;
                    for (cum = 0; cum < 4; cum++) {
                        var td = document.createElement("td");
                        td.setAttribute("id", json_data[i]['id']+"*"+json_data[i]['url']);
                        td.setAttribute("class", "tds");
                        td.setAttribute("onclick", "OpenWeb(this)");
                        var html = '';
                        html = html + "<a>\n" +
                            "             <div style=\"height: 50px;width: 100%\">\n" +
                            "                 <img src=\"" + json_data[i].img + "\" id='img'>\n" +
                            "              </div>\n" +
                            "              <div style=\"margin-top: 15px;font-size: small;font-family: 'Arial Narrow';color: red\"><label>" + json_data[i].name + "</label></div>\n" +
                            "           </a>\n";
                        td.innerHTML = html;
                        tr.appendChild(td);
                        i=i+1;
                        if (!(i < row)) {
                            break;
                        }
                    }
                    obj.appendChild(tr);
                }
            }
        });
    }
});
