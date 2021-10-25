package Server;

import java.io.*;

public class Server_Talk_Thread extends Thread{
    //用来和客户端交流的类
    //每一个对象都需要单独实例化一个此类，具体实例化在Waiting_Thread中

    InputStream is;
    OutputStream os;
    Waiting_Connection waiting_thread;
    Serverwindow serverwindow;
    int player;
    public Server_Talk_Thread(InputStream is, OutputStream os)                         //传入输入输出流
    {
        this.is = is;
        this.os = os;
    }
    public void run()
    {
        while(true)
        {
            try {
                String msg = read();
                String[] strs = msg.split("#");
                for (int i = 0; i < strs.length; i++) {
                if (strs[i].equals("score_self")) {                             //答对了
                    if (player == 1) {
                        waiting_thread.player2.write("enemy_life+#");
                        serverwindow.score1++;
                        serverwindow.right1++;
                    }
                    else if (player == 2) {
                        waiting_thread.player1.write("enemy_life+#");
                        serverwindow.score2++;
                        serverwindow.right2++;
                    }
                } else if (strs[i].equals("wrong_self")) {                      //答错了
                    if (player == 1) {
                        waiting_thread.player2.write("enemy_life-#");
                        serverwindow.score1--;
                        serverwindow.miss1++;
                    }
                    else if (player == 2) {
                        waiting_thread.player1.write("enemy_life-#");
                        serverwindow.score2--;
                        serverwindow.miss2++;
                    }
                } else if (strs[i].equals("-_+")) {                             //掉到地上
                    if (player == 1) {
                        waiting_thread.player2.write("enemy_life-#");
                        waiting_thread.player2.write("life+#");
                        serverwindow.wrong1++;
                        serverwindow.score1--;
                        serverwindow.score2++;
                    } else if (player == 2) {
                        waiting_thread.player1.write("enemy_life-#");
                        waiting_thread.player1.write("life+#");
                        serverwindow.wrong2++;
                        serverwindow.score2--;
                        serverwindow.score1++;
                    }
                } else if (strs[i].equals("Name")) {                            //名字
                    if (player == 1) {
                        serverwindow.name1 = strs[i+1];
                    } else if (player == 2) {
                        serverwindow.name2 = strs[i+1];
                    }
                } else if (strs[i].equals("word")) {                            //单词
                    if (player == 1) {
                        serverwindow.word1 = strs[i+1];
                    } else if (player == 2) {
                        serverwindow.word2 = strs[i+1];
                    }
                }
            }
                } catch(Exception e){
                    e.printStackTrace();
            }
        }
    }
    //自写读函数（解决中文乱码）
    public String read()
    {
        String msg = null;
        byte[] bytes1 = new byte[1024];
        byte[] bytes2;
        try {
            int len = is.read(bytes1);
            bytes2 = new byte[len];
            System.arraycopy(bytes1,0,bytes2,0,len);
            msg = new String(bytes2,"UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

    //自写写函数（解决中文乱码）
    public void write(String msg)
    {
        try {
            os.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
