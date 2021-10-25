package Client.GUI.game_panel;

import Client.Client_Talk_Thread;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Game_panel extends JPanel {
    ImageIcon backgroundicon = new ImageIcon("picture/游戏界面.png");
    ImageIcon wordtableicon = new ImageIcon("picture/泡泡2.png");
    ImageIcon Chineseicon = new ImageIcon("picture/得分框.png");
    ImageIcon scoreicon = new ImageIcon("picture/真得分框.png");
    ImageIcon wordicon = new ImageIcon("picture/泡泡1.png");

    ArrayList<String> right = new ArrayList<>();
    ArrayList<String> wrong = new ArrayList<>();
    ArrayList<String> miss  = new ArrayList<>();

    public Client_Talk_Thread client_thread;
    Client_Screen_Refresh thread_game_panel = new Client_Screen_Refresh(this);
    Keyboard keyboard = new Keyboard(this);
    Word word;
    public int life=20;
    public int enemy_life=20;
    ArrayList<Characters> characters = new ArrayList<>();
    int timer = 0;
    public Game_panel(Client_Talk_Thread client_thread)
    {
        this.client_thread = client_thread;
        addKeyListener(keyboard);
        thread_game_panel.start();
        word = new Word(client_thread);
    }
    public void paint(Graphics g)
    {
        //背景
        backgroundicon.paintIcon(this,g,0,0);
        //单词
        wordtableicon.paintIcon(this,g,word.x,word.y);
        g.setColor(Color.BLACK);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        int word_len = word.word_.length();
        g.drawString(word.word_,word.x+(-6*word_len+70)+5,word.y+70);
        move();
        if(word.y>=710)
        {//落地
            miss.add(word.word);
            word.set_words();
            client_thread.write("word#"+word.word+"#");
            life--;
            //传输
            client_thread.write("wrong_self#");
        }

        //汉义
        Chineseicon.paintIcon(this,g,1100,500);
        int china_len=word.china.length();
        g.drawString(word.china,(int)(-6.5*china_len+1169.5),575);

        //字母
        for(int i=0;i<characters.size();i++)
        {
            Characters c = characters.get(i);
            g.setFont(new Font("微软雅黑",Font.BOLD,25));
            g.setColor(Color.red);
            wordicon.paintIcon(this,g,c.x-16,c.y-26);
            g.drawString(c.str,c.x,c.y);
            c.move();//移动
            if(isHit(c,word.x+63,word.y+68))
            if(judge(c))   //错误
            {
                wrong.add(word.word);
                word.set_words();
                characters.clear();
                life--;
                enemy_life++;
                client_thread.write("-_+#");//自己负对方正
                //传输
            }
        }
        g.setColor(Color.BLUE);
        g.setFont(new Font("微软雅黑",Font.BOLD,10));
        //生命值
        scoreicon.paintIcon(this,g,900,0);
        g.setFont(new Font("微软雅黑",Font.BOLD,25));
        g.setColor(Color.BLACK);
        g.drawString("敌方生命值："+String.valueOf(enemy_life),920,75);
        g.drawString("当前生命值："+String.valueOf(life),920,44);
        if(life==0||enemy_life==0)   //游戏结束
        {
            try {
                PrintStream ps1 = new PrintStream("Right.txt");
                PrintStream ps2 = new PrintStream("Wrong.txt");
                PrintStream ps3 = new PrintStream("Miss.txt");
                for(String str:right)
                {
                    ps1.println(str);
                }
                for(String str:wrong)
                {
                    ps2.println(str);
                }
                for(String str:miss)
                {
                    ps3.println(str);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        timer = (timer+1)%1000;

    }
    public void move()
    {
        word.y++;
    }
    public boolean isHit(Characters c,int x,int y)
    {
        int d = (c.x-x)*(c.x-x)+(c.y-y)*(c.y-y);
        if(d<=3969)
        {
            characters.remove(c);
            return true;
        }
        return false;
    }
    public boolean judge(Characters c)
    {
        boolean result = true;
        String letter = c.str;
        if(letter.equals(word.lack_letter1))
        {
            word.change_word(word.num1);
            word.lack_letter1=null;
            result = false;
        }
        else if(letter.equals(word.lack_letter2))
        {
            word.change_word(word.num2);
            word.lack_letter2=null;
            result = false;
        }
        if(word.lack_letter1==null&&word.lack_letter2==null)   //得分
        {
            right.add(word.word);
            word.set_words();
            life++;
            client_thread.write("score_self#");
            //传输
        }
        return result;
    }
}
