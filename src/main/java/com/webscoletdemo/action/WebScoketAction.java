package com.webscoletdemo.action;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceFeature;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

public class WebScoketAction extends WebSocketServlet {  
    private static final long serialVersionUID = -4853540828121130946L;  
    private static List<MyMessageInbound> mmiList = new ArrayList<MyMessageInbound>();  

    @Override  
    protected MyMessageInbound createWebSocketInbound(String str,  
            HttpServletRequest request) {  
        request.getParameter("");       
        return new MyMessageInbound();  
    }  

    private class MyMessageInbound extends MessageInbound {  
        WsOutbound myoutbound;  
        //开启websocket
        public void onOpen(WsOutbound outbound) {  
            try {  
                System.out.println("Open Client.");  
                this.myoutbound = outbound;  
                mmiList.add(this);  
                outbound.writeTextMessage(CharBuffer.wrap("Hello!"));  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  

        //关闭websocket
        public void onClose(int status) {  
            System.out.println("Close Client.");  
            mmiList.remove(this);  
        }  
       //当服务器端收到信息的时候,就对所有的连接进行遍历并且把收到的信息发送给所有用户  
        public void onTextMessage(CharBuffer cb) throws IOException {  
            System.out.println("Accept Message : " + cb);  
            for (MyMessageInbound mmib : mmiList) {  
                CharBuffer buffer = CharBuffer.wrap(cb);  
                mmib.myoutbound.writeTextMessage(buffer);  
                mmib.myoutbound.flush();  
            }  
        }  


        public void onBinaryMessage(ByteBuffer bb) throws IOException {  

        }  
    }  
}  