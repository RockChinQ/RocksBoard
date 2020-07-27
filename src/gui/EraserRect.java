package gui;

import javax.swing.*;
import java.awt.*;

public class EraserRect extends JPanel {
    public EraserRect(){
        this.setSize(MainWindow.ERASER_RADIUS*2,MainWindow.ERASER_RADIUS*2);

    }
    public void paint(Graphics g){
        g.setColor(new Color(220,220,220));
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(new Color(147, 147, 147));
        g.fillRect((int)(this.getWidth()*0.3/2),(int)(this.getHeight()*0.3/2),(int)(this.getWidth()*0.7),(int)(this.getHeight()*0.7));
    }
}
