package Client;

import Client.GUI.Wait_interface_JPanel;
import Client.GUI.game_panel.Game_panel;

import java.io.*;
import java.net.Socket;

public class Client_Talk_Thread extends Thread{
    //客户端接受信息的类

    Socket socket;
    InputStream is;
    OutputStream os;
    Wait_interface_JPanel wait_interface_jPanel;
    public Game_panel game_panel;

    //传入昵称，等待界面
    public Client_Talk_Thread(Wait_interface_JPanel wait_interface_jPanel, String name)
    {
        //传入等待界面
        this.wait_interface_jPanel = wait_interface_jPanel;
        try {

            //服务器连接客户端
            socket = new Socket("127.0.0.1",9999);
            is = socket.getInputStream();
            os = socket.getOutputStream();

            //写入昵称
            write("Name#"+name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //一直等待来自另一个客户端（服务器转接）的消息
    public void run()
    {
        while (true)
        {
            try {
                String msg = read();                            //接收来自服务器的全部消息
                String[] strs = msg.split("#");           //将消息用“#”分离
                if(strs[0].equals("Start"))                     //开始游戏
                {
                    wait_interface_jPanel.isstart = true;
                }
                else if(strs[0].equals("enemy_life+"))          //敌人生命+1
                {
                    game_panel.enemy_life++;
                }
                else if(strs[0].equals("enemy_life-"))          //敌人生命-1
                {
                    game_panel.enemy_life--;
                }
                else if(strs[0].equals("life+"))                //自己生命+1
                {
                    game_panel.life++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //自写读函数（解决中文乱码问题）
    public String read()
    {
        String msg = null;
        byte[] bytes1 = new byte[1024];                             //中间数组
        byte[] bytes2;                                              //最终数组
        try {
            int len = is.read(bytes1);
            bytes2 = new byte[len];
            System.arraycopy(bytes1,0,bytes2,0,len);  //将数组1拷贝到数组2中
            msg = new String(bytes2,"UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    //自写写函数（解决中文乱码问题）
    public void write(String msg)
    {
        try {
            os.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
