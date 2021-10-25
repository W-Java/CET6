package Client.GUI.game_panel;

import Client.GUI.game_panel.Game_panel;

public class Client_Screen_Refresh extends Thread{
    Game_panel game_panel;
    public Client_Screen_Refresh(Game_panel game_panel)
    {
        this.game_panel = game_panel;
    }
    public void run()
    {
        while(true)
        {
            game_panel.repaint();
            game_panel.requestFocus();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
