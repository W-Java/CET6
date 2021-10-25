package Client.GUI;

import Client.Client_JFrame;
import Client.Client_Talk_Thread;
import Client.GUI.game_panel.Game_panel;

import javax.swing.*;

public class Wait_interface_JPanel extends JPanel implements Runnable{

    //图片导入
    ImageIcon backgroundicon = new ImageIcon("picture/加载界面2.png");

    ImageIcon waiticon1 = new ImageIcon("picture/正在匹配对手.png");
    ImageIcon waiticon2 = new ImageIcon("picture/正在匹配对手。.png");
    ImageIcon waiticon3 = new ImageIcon("picture/正在匹配对手。。.png");
    ImageIcon waiticon4 = new ImageIcon("picture/正在匹配对手。。。.png");
    ImageIcon batteryicon = new ImageIcon("picture/电池.gif");

    ImageIcon ok = new ImageIcon("picture/匹配成功!.png");
    ImageIcon ready1 = new ImageIcon("picture/准备开始1.png");
    ImageIcon ready2 = new ImageIcon("picture/准备开始2.png");
    ImageIcon ready3 = new ImageIcon("picture/准备开始3.png");
    ImageIcon go = new ImageIcon("picture/GO.png");



    ImageIcon[] waiting ={waiticon1,waiticon2,waiticon3,waiticon4};
    JLabel battery = new JLabel(batteryicon);
    JLabel background = new JLabel(backgroundicon);
    JLabel wait = new JLabel();

    int timer = 0;
    int timer2 =0;
    Client_JFrame client_jFrame;
    Client_Talk_Thread client_thread;
    public boolean isstart = false;
    Game_panel game_panel;
    Thread thread = new Thread(this);
    public Wait_interface_JPanel(Client_JFrame client_jFrame){
            this.client_jFrame = client_jFrame;                     //给等待界面传入Frame
            this.setLayout(null);                                   //清空现有流式布局
            background.setBounds(0,0,1282,723);
            wait.setBounds(200,200, 980,106);
            battery.setBounds(180,100,700,700);
            //this.add(bird);
            this.add(battery);
            this.add(wait);
            this.add(background);
    }

    @Override
    public void run() {

        game_panel = new Game_panel(client_thread);
        while(true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(isstart)
            {
                battery.setBounds(400,450,980,212);
                wait.setBounds(380,350,980,106);

                if(timer2<400)
                {
                    wait.setIcon(ok);
                    battery.setIcon(ready3);
                }
                else if(timer2<800)
                {
                    wait.setIcon(ok);
                    battery.setIcon(ready2);
                }
                else if(timer2<1200)
                {
                    wait.setIcon(ok);
                    battery.setIcon(ready1);
                }
                else if(timer2<1800)
                {
                    wait.setIcon(ok);
                    battery.setIcon(go);
                }
                else {
                    wait.setIcon(ok);
                    battery.setIcon(go);
                    System.out.println("isstart");
                    client_thread.game_panel = game_panel;
                    client_jFrame.remove(this);
                    client_jFrame.add(game_panel);
                    client_jFrame.revalidate();
                    isstart = false;
                }
                timer2++;
            }
            else {

                if (timer < 1000)
                    wait.setIcon(waiting[0]);
                else if (timer < 2000)
                    wait.setIcon(waiting[1]);
                else if (timer < 3000)
                    wait.setIcon(waiting[2]);
                else if (timer < 4000)
                    wait.setIcon(waiting[3]);
            }

            timer = (timer+1)%4000;
        }
    }
}
