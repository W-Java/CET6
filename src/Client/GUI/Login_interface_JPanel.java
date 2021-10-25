package Client.GUI;

import Client.Client_JFrame;

import javax.swing.*;
import java.awt.*;

public class Login_interface_JPanel extends JPanel {
    Client_JFrame client_jFrame;
    public Login_interface_JPanel(Client_JFrame client_jFrame){
        //传入Frame
        this.client_jFrame = client_jFrame;

        //设置坐标大小
        background.setBounds(0,0,1280,720);
        login_name.setBounds(400,50,451,546);
        login_login.setBounds(400,50,451,546);
        login_exit.setBounds(400,50,451,546);
        login_rest.setBounds(400,50,451,546);

        jTextArea.setBounds(560,261,145,39);
        this.setLayout(null); //清空现有流式布局
        jTextArea.setOpaque(false);
        jTextArea.setFont(new Font("微软雅黑", Font.BOLD, 18));  //设置当前字体。


        //添加组件
        this.add(login_rest);
        this.add(login_name);
        this.add(login_exit);
        this.add(login_login);
        this.add(background);

        //添加鼠标键盘监听
        MouseListener_event mouseListener_event = new MouseListener_event(this);
        KeyListener_event keylistener_event1 = new KeyListener_event(this);
        login_rest.addMouseListener(mouseListener_event);
        login_name.addMouseListener(mouseListener_event);
        login_exit.addMouseListener(mouseListener_event);
        login_login.addMouseListener(mouseListener_event);
        login_rest.addMouseMotionListener(mouseListener_event);
        login_name.addMouseMotionListener(mouseListener_event);
        login_exit.addMouseMotionListener(mouseListener_event);
        login_login.addMouseMotionListener(mouseListener_event);

        jTextArea.addKeyListener(keylistener_event1);
    }
    //图片导入
    ImageIcon backgroundicon = new ImageIcon("picture/背景.gif");
    ImageIcon loginicon_name = new ImageIcon("picture/昵称.png");
    ImageIcon loginicon_name_black = new ImageIcon("picture/昵称（不含昵称）.png");
    ImageIcon loginicon_login = new ImageIcon("picture/登入.png");
    ImageIcon loginicon_login_black = new ImageIcon("picture/登入（黑）.png");
    ImageIcon loginicon_login_black_moving = new ImageIcon("picture/登入（黑_动态）.png");
    ImageIcon loginicon_exit = new ImageIcon("picture/退出游戏.png");
    ImageIcon loginicon_exit_black = new ImageIcon("picture/退出游戏（黑）.png");
    ImageIcon loginicon_exit_black_moving = new ImageIcon("picture/退出游戏（黑_动态）.png");
    ImageIcon loginicon_rest = new ImageIcon("picture/登陆界面（全空）.png");

    //组件定义
    JLabel background = new JLabel(backgroundicon);
    JLabel login_name = new JLabel(loginicon_name);
    JLabel login_login = new JLabel(loginicon_login);
    JLabel login_exit = new JLabel(loginicon_exit);
    JLabel login_rest = new JLabel(loginicon_rest);
    JTextArea jTextArea = new JTextArea(1,8);

    //重加载（解决组件覆盖的问题）
    public void reloading (){
        this.remove(login_name);
        this.remove(login_rest);
        this.remove(login_exit);
        this.remove(login_login);
        this.remove(background);

        this.add(jTextArea);
        this.add(login_rest);
        this.add(login_name);
        this.add(login_exit);
        this.add(login_login);
        this.add(background);
    }


}
