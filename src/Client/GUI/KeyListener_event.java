package Client.GUI;

import Client.Client_Talk_Thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListener_event implements KeyListener {
    Wait_interface_JPanel wait_interface_jPanel;
    Login_interface_JPanel login_interface_jPanel;
    KeyListener_event(Login_interface_JPanel login_interface_jPanel){
        //导入登录界面
        this.login_interface_jPanel = login_interface_jPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){//登录成功
            try {
                //设置休眠时间，使得界面变化更加流畅
                Thread.sleep(300);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            //将等待界面和昵称传入client_thread
            Client_Talk_Thread client_thread = new Client_Talk_Thread(wait_interface_jPanel,login_interface_jPanel.jTextArea.getText());
            //一直启动线程
            client_thread.start();

            //启动等待界面
            wait_interface_jPanel = new Wait_interface_JPanel(login_interface_jPanel.client_jFrame);    //给等待界面传入Frame
            wait_interface_jPanel.client_thread =client_thread;                                         //给等待界面传入Client_Thread
            login_interface_jPanel.client_jFrame.remove(login_interface_jPanel);
            login_interface_jPanel.client_jFrame.add(wait_interface_jPanel);
            login_interface_jPanel.client_jFrame.revalidate();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
