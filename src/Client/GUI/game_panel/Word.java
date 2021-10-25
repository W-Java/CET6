package Client.GUI.game_panel;

import Client.Client_Talk_Thread;

import java.io.*;
import java.util.ArrayList;

public class Word {
    ArrayList<String> words = new ArrayList<>();
    int x,y;
    int num1, num2;
    String word_;
    String china;
    String word;
    Client_Talk_Thread client_thread;
    char[] original_word;
    char[] change_word;
    String lack_letter1,lack_letter2;//缺失的字母

    public Word(Client_Talk_Thread client_thread)
    {
        this.client_thread = client_thread;
        BufferedReader bufferedReader = null;//存储六级词汇的记事本
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("六级词汇.txt"),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true){
            String word0 = null;
            try {
                word0 = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(word0 == null){   break;   }
            words.add(word0);//words存储全部的需要使用的六级词汇
        }
        set_words();
    }
     public void set_words()
    {
        if(words.size()>0) {
            String msg;
            x = (int) (Math.random() * 1000);
            y = 0;

            int number;
            do {
                number = (int) (Math.random() * words.size());//产生随机数
            } while (number >= words.size());

            msg = words.get(number);
            words.remove(msg);
            String[] word_china = msg.split("@");
            word = word_china[0];//英文
            original_word = word.toCharArray();
            china = word_china[1];//汉字部分
            while (true) {
                num1 = (int) (Math.random() * (word.length() - 1));
                num2 = (int) (Math.random() * (word.length() - 1));
                if (num1 != num2) {
                    break;
                }
            }
            //num1,num2随机生成的两个随机数
            lack_letter1 = String.valueOf(original_word[num1]);
            lack_letter2 = String.valueOf(original_word[num2]);
            StringBuilder stringBuilder = new StringBuilder(word);
            stringBuilder.replace(num1, num1 + 1, "_");
            stringBuilder.replace(num2, num2 + 1, "_");
            word_ = stringBuilder.toString();
            change_word = word_.toCharArray();
            client_thread.write("word#"+word);
        }
    }
    public void change_word(int pos)
    {
        change_word[pos] = original_word[pos];
        word_ = String.valueOf(change_word);
    }
}
