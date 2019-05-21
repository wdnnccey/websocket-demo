<html>
<body>
轨迹
</body>
<audio id="notice" loop="loop">
   <source src="http://fs.w.kugou.com/201905211603/af65d55785b7e97732644d0900fdf8b9/G146/M04/17/18/MocBAFx43JWAdxDBAC92YHGZWaY748.mp3" type="audio/mpeg">
</audio>
<script>
    var websocket =null;
    if('WebSocket' in window){
        websocket = new WebSocket('ws://127.0.0.1:8080/webSocket');
    }else {
        alert('您的浏览器不支持websocket。');
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    }

    websocket.onclose = function (event) {
        console.log('连接关闭');
    }

    websocket.onmessage = function (event) {
        console.log('收到消息：'+event.data)
        alert(event.data);
        var myAuto = document.getElementById('notice');
        console.log('开始播放音乐！15秒后停止...');
        myAuto.play();
        setTimeout(function () {
            myAuto.pause();
            console.log('音乐停止...')
        },15000)
    }

    websocket.onerror = function () {
        alert('websocket通信发生错误');

    }

    websocket.onbeforeunload = function(){
        websocket.close();
    }
</script>
</html>