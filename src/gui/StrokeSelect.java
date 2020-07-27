package gui;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StrokeSelect extends JPanel {
    static  final Color bg=new Color(220,220,220),sbg=new Color(184,184,184);
    class StrokeRect extends JButton{
        int v=1;
        boolean select=false;
        public void paint(Graphics g){
            g.setColor(select?sbg:bg);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.setColor(Color.black);
            g.fillRect(this.getWidth()/2-v/2,this.getHeight()/2-v/2,v,v);
        }
        public StrokeRect(int v){
            this.setSize(40,40);
            this.v=v;
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selt.select=false;
                    selt=(StrokeRect)e.getSource();
                    selt.select=true;

                    Main.mw.ss.repaint();
                    Main.mw.requestFocus();
                }
            });
        }
    }
    StrokeRect s_1=new StrokeRect(2);
    StrokeRect s_5=new StrokeRect(7);
    StrokeRect s_10=new StrokeRect(15);

    StrokeRect selt;

    public StrokeSelect(){
        this.setLayout(null);

        s_1.setLocation(0,0);
        this.add(s_1);
        s_5.setLocation(40,0);
        selt=s_5;
        selt.select=true;
        this.add(s_5);
        s_10.setLocation(80,0);
        this.add(s_10);
    }

}
