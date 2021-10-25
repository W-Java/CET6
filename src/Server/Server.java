package Server;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Server extends JFrame{
    Serverwindow serverwindow = new Serverwindow();
    Server() throws Exception{
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("服务器");
        this.setSize(1081,597);
        add(serverwindow);
        this.setVisible(true);
    }

}
