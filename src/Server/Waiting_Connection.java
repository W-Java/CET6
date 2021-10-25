package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Waiting_Connection extends Thread {
    //等待连接

    ServerSocket serverSocket;
    Socket socket;
    ArrayList<Server_Talk_Thread> users = new ArrayList<>();               //存两个玩家
    int times=1;
    Server_Talk_Thread player1;
    Server_Talk_Thread player2;
    Serverwindow serverwindow;
    boolean isstart = false;                                            //是否连接
    public Waiting_Connection(Serverwindow serverwindow)                //传入服务器界面
    {
        this.serverwindow = serverwindow;
        try {
            serverSocket = new ServerSocket(9999);             //给端口号
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run()                                               //循环等待，等待其被连接
    {
        while(true)
        {
            try {
                InputStream is;
                OutputStream os;
                socket = serverSocket.accept();                     //循环等待

                //连接上了
                is = socket.getInputStream();
                os = socket.getOutputStream();
                Server_Talk_Thread talk_thread = new Server_Talk_Thread(is,os);   //传入输入输出流
                talk_thread.serverwindow = serverwindow;
                talk_thread.player = times;                         //检测玩家的次序
                times++;
                talk_thread.waiting_thread = this;                  //将自己传入talk_thread
                talk_thread.start();                                //开始一直等待客户端的消息
                users.add(talk_thread);                             //将实例化的“用户”添加到users中
                is_start();                                         //判断用户人数是否达到开始人数
                /*if(users.size()==1)
                {
                    Talk_Thread player1 = users.get(0);
                    String msg = "Start#";
                    player1.write(msg);
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void is_start()
    {
        if(users.size()==2)
        {
            player1 = users.get(0);                                 //两个player实际上是talk_thread
            player2 = users.get(1);
            String msg = "Start#";                                  //传递开始游戏的消息
            player1.write(msg);
            player2.write(msg);
            isstart = true;
        }
    }
}
