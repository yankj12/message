<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript">
var socket = new WebSocket("ws://localhost:8080/message/aa.do?aaa=11");
socket.onopen = function(){
    //浏览器socket开始进入
    console.log("open");
    //向服务器端发送信息
    socket.send("open+hello");
};
socket.onmessage = function(evt)
{
    console.log("message");
    console.log("onmessage:"+evt.data);
};
socket.onclose = function(evt){
    console.log("close");
    console.log("onclose:"+evt.data);
};
socket.onerror = function(evt)
{
    console.log("error");
    console.log("onerror:"+evt.data);
};
function send()
{
    //socket.send("sendMessage");
}
function closeWebSocket(){
     socket.close();
}

</script>
  </head>

  <body>
    This is my JSP page. <br>
     Welcome<br/>
   <button onclick="send()">Send</button>    <button onclick="closeWebSocket()">Close</button>
    <div id="message">
    </div>
  </body>
</html>
