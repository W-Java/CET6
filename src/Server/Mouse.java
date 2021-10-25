package Server;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
    //鼠标监听，点击强制退出

    Serverwindow serverwindow;
    Rectangle out = new Rectangle(400,500,260,50);
    public Mouse(Serverwindow serverwindow)
    {
        this.serverwindow = serverwindow;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //鼠标点击，按钮下去
        Point p = new Point(e.getX(),e.getY());
        if(out.contains(p))
        {
            serverwindow.outy=505;
        }
        else
        {
            serverwindow.outy=500;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //鼠标释放，按钮复位
        Point p = new Point(e.getX(),e.getY());
        if(out.contains(p))
        {
            serverwindow.outy=500;
        }
        else
        {
            serverwindow.outy=500;
        }
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
        //鼠标移动进区域，按钮变颜色
        Point p = new Point(e.getX(),e.getY());
        if(out.contains(p))
        {
            serverwindow.choose_out=2;
        }
        else
        {
            serverwindow.choose_out=1;
        }
    }
}
