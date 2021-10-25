package Client.GUI.game_panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    Game_panel game_panel;
    public Keyboard(Game_panel game_panel)
    {
        this.game_panel = game_panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if(keycode>=65&&keycode<=90) {
            char letter = (char) (keycode + 32);
            Characters characters = new Characters(String.valueOf(letter), game_panel);
            characters.word = game_panel.word;
            game_panel.characters.add(characters);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
