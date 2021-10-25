package Server;

import javax.swing.*;
import java.awt.*;

public class Serverwindow extends JPanel {
    //服务器界面

    ImageIcon background = new ImageIcon("picture/服务器界面.png");
    ImageIcon out1 = new ImageIcon("picture/强制关闭游戏（未点击）.png");
    ImageIcon out2 = new ImageIcon("picture/强制关闭游戏（点击）.png");
    ImageIcon information = new ImageIcon("picture/用户信息.png");
    ImageIcon right = new ImageIcon("picture/正确.png");
    ImageIcon wrong = new ImageIcon("picture/错误.png");
    ImageIcon unfounded = new ImageIcon("picture/未答.png");
    ImageIcon server = new ImageIcon("picture/服务器.png");
    ImageIcon word = new ImageIcon("picture/当前单词.png");
    ImageIcon score = new ImageIcon("picture/得分.png");

    int outx,outy;                       //按钮的位置
    int choose_out = 1;
    Waiting_Connection waiting_thread;
    String name1;
    String name2;
    String word1;
    String word2;

    //得分情况的初始化
    int right1=0;
    int right2=0;
    int wrong1=0;
    int wrong2=0;
    int miss1=0;
    int miss2=0;
    int score1=20;
    int score2=20;
    Server_Screen_Refresh thread_sw = new Server_Screen_Refresh(this);
    Mouse mouse = new Mouse(this);
    public Serverwindow()
    {
        thread_sw.start();
        waiting_thread = new Waiting_Connection(this);               //
        waiting_thread.start();

        //添加鼠标监听
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        outx = 400;
        outy = 500;
    }
    public void paint(Graphics g)
    {
        background.paintIcon(this,g,0,0);
        server.paintIcon(this,g,450,0);
        information.paintIcon(this,g,0,80);
        word.paintIcon(this,g,250,80);
        right.paintIcon(this,g,500,80);
        wrong.paintIcon(this,g,625,80);
        unfounded.paintIcon(this,g,750,80);
        score.paintIcon(this,g,900,80);
        if(choose_out==1)
        {
            out1.paintIcon(this,g,outx,outy);                             //鼠标在按钮之外
        }
        else if(choose_out==2)
        {
            out2.paintIcon(this,g,outx,outy);                             //鼠标在按钮之内
        }
        if(name1!=null)                                                     //画name1
        {
            g.setFont(new Font("微软雅黑",Font.BOLD,35));
            g.setColor(Color.WHITE);
            g.drawString(name1,40,200);
        }
        if(name2!=null)                                                     //画name2
        {
            g.setFont(new Font("微软雅黑",Font.BOLD,35));
            g.setColor(Color.WHITE);
            g.drawString(name2,40,300);
        }
        if(word1!=null&&waiting_thread.isstart==true)                       //画word1
        {
            g.setFont(new Font("微软雅黑",Font.BOLD,35));
            g.setColor(Color.WHITE);
            g.drawString(word1,280,200);
        }
        if(word2!=null&&waiting_thread.isstart==true)                       //画word2
        {
            g.setFont(new Font("微软雅黑",Font.BOLD,35));
            g.setColor(Color.WHITE);
            g.drawString(word2,280,300);
        }
                                                                            //画得分情况
        g.setFont(new Font("微软雅黑",Font.BOLD,35));
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(right1),540,200);
        g.drawString(String.valueOf(right2),540,300);
        g.drawString(String.valueOf(wrong1),670,200);
        g.drawString(String.valueOf(wrong2),670,300);
        g.drawString(String.valueOf(miss1),790,200);
        g.drawString(String.valueOf(miss2),790,300);
        g.drawString(String.valueOf(score1),940,200);
        g.drawString(String.valueOf(score2),940,300);
    }
}
