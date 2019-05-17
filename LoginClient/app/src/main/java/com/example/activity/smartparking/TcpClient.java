package com.example.activity.smartparking;

//客户端程序
import android.util.Log;
import android.widget.Toast;

import java.net.*;
import java.io.*;

public class TcpClient{

    public  void tcpConnection(String str1,String str2){
        try{
            Log.d("test",str1);
            //创建Socket
            Socket socket = new Socket("148.70.192.71",4700);

            //建立连接
            InputStreamReader reader = new InputStreamReader(new FileInputStream(str1));
            BufferedReader bufferedReader = new BufferedReader(reader);

            PrintWriter Socout = new PrintWriter(socket.getOutputStream());

            //进行通信
            String userData = bufferedReader.readLine();

//            //用户数据存储到服务器的数据库中
//            while(!userData.equals(null)){
//                userData = bufferedReader.readLine();
//                Socout.flush();
//            }
            //关闭IO和Socket
            Socout.close();
            socket.close();
        }catch(Exception e){
            System.out.println("Error:" + e);
        }
    }
}
