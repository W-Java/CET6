package Client.GUI.game_panel;

public class Characters  {
    String str = "";
    int x,y;
    int speedx,speedy;
    Game_panel game_panel;
    Word word;
    public Characters(String str,Game_panel game_panel)
    {
        this.game_panel = game_panel;
        this.str = str;
        x=640;
        y=700;
    }
    public void move()
    {
        speedx = (x-(word.x+63))/30;
        speedy = (y-(word.y+68))/30;
        if(speedy<0.5) {
            game_panel.characters.remove(this);
            if(game_panel.judge(this))
            {
                word.set_words();
                game_panel.characters.clear();
                game_panel.life--;
                game_panel.enemy_life++;
                game_panel.client_thread.write("-_+#");//自己负对方正
                //传输
            }
        }
        x-= speedx;
        y-= speedy;
        System.out.println(x);
    }
}
