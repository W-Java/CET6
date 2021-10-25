package Client.GUI;

import Client.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseListener_event implements MouseListener, MouseMotionListener {
    Wait_interface_JPanel wait_interface_jPanel;

    Client_JFrame client_jFrame;

    Login_interface_JPanel login_interface_jPanel;
    public MouseListener_event(Login_interface_JPanel login_interface_jPanel){
        this.login_interface_jPanel = login_interface_jPanel;

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int X = e.getX();
        int Y = e.getY();
        if(X>100&&X<347&&Y>201&&Y<240){
            login_interface_jPanel.login_exit.setIcon(login_interface_jPanel.loginicon_exit_black);
        }
        if(X>100&&X<347&&Y>316&&Y<357){//登录成功
            login_interface_jPanel.client_jFrame.remove(login_interface_jPanel);

            wait_interface_jPanel = new Wait_interface_JPanel(login_interface_jPanel.client_jFrame);
            Client_Talk_Thread client_thread = new Client_Talk_Thread(wait_interface_jPanel,login_interface_jPanel.jTextArea.getText());
            client_thread.start();
            wait_interface_jPanel.client_thread = client_thread;
            wait_interface_jPanel.thread.start();
            login_interface_jPanel.client_jFrame.add(wait_interface_jPanel);
            login_interface_jPanel.client_jFrame.revalidate();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int X = e.getX();
        int Y = e.getY();
        if(X>100&&X<347&&Y>201&&Y<240){
            login_interface_jPanel.reloading();
            login_interface_jPanel.login_name.setIcon(login_interface_jPanel.loginicon_name_black);
        }
        if(X>100&&X<347&&Y>376&&Y<419){//结束游戏
            login_interface_jPanel.login_exit.setIcon(login_interface_jPanel.loginicon_exit_black_moving);
            try {
                Thread.sleep(200);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.exit(0);
        }else if(X>100&&X<347&&Y>316&&Y<357){//登入
            login_interface_jPanel.login_login.setIcon(login_interface_jPanel.loginicon_login_black_moving);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int X,Y;
        X = e.getX();
        Y = e.getY();

        if(X>100&&X<347&&Y>316&&Y<357){
            login_interface_jPanel.login_login.setIcon(login_interface_jPanel.loginicon_login_black);

        }else{
            login_interface_jPanel.login_login.setIcon(login_interface_jPanel.loginicon_login);
        }
        if(X>100&&X<347&&Y>376&&Y<419){
            login_interface_jPanel.login_exit.setIcon(login_interface_jPanel.loginicon_exit_black);
        }else{
            login_interface_jPanel.login_exit.setIcon(login_interface_jPanel.loginicon_exit);
        }

    }
}
