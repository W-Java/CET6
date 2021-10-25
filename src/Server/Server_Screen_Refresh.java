package Server;

public class Server_Screen_Refresh extends Thread {
    Serverwindow serverwindow;
    //传入服务器界面
    public Server_Screen_Refresh(Serverwindow serverwindow)
    {
        this.serverwindow = serverwindow;
    }
    public void run()
    {
        while(true)
        {                                                   //屏幕刷新率为1ms
            serverwindow.repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
