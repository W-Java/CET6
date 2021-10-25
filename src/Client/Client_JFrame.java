package Client;

import Client.GUI.*;
import Client.GUI.game_panel.Game_panel;

import java.awt.*;
import javax.swing.*;

public class Client_JFrame extends JFrame {

    Client_JFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Login_interface_JPanel login_interface_jPanel = new Login_interface_JPanel(this);

        //修改鼠标样式
        Image image = Toolkit.getDefaultToolkit().getImage("picture/Cursor.png");
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(image,new Point(0, 0), null));

        this.setResizable(false);
        this.setTitle("客户端");
        this.setSize(1280,710);
        this.setVisible(true);
        //添加登陆界面
        this.add(login_interface_jPanel);

    }

}
