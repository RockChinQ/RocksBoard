package gui;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolSelect extends JPanel implements ActionListener {
    static final Color select=new Color(134, 134, 134),unsele=new Color(220,220,220);

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==eraser&&this.selt==eraser){
            Main.mw.tc.setVisible(!Main.mw.tc.isVisible());
        }else{
            Main.mw.tc.setVisible(false);
        }

        this.selt.selected=false;
        this.selt=(ToolRect)e.getSource();
        this.selt.selected=true;
        Main.mw.er.setVisible(false);

        this.repaint();

        Main.mw.requestFocus();
    }

    class ToolRect extends JButton{
        ImageIcon ii,is;
        boolean selected=false;
        public ToolRect(ImageIcon ii,ImageIcon is){
            this.ii=ii;
            this.is=is;
        }
        public void paint(Graphics g){
            //g.setColor(selected?select:unsele);
            //g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.drawImage(selected?is.getImage():ii.getImage(),0,0,this);
        }
    }
    ImageIcon handi=new ImageIcon("resource\\hand.png");
    ImageIcon handis=new ImageIcon("resource\\hand_select.png");
    ImageIcon peni=new ImageIcon("resource\\pencil.png");
    ImageIcon eraseri=new ImageIcon("resource\\eraser.png");
    ImageIcon penis=new ImageIcon("resource\\pencil_select.png");
    ImageIcon eraseris=new ImageIcon("resource\\eraser_select.png");

    ToolRect hand=new ToolRect(handi,handis);
    ToolRect pen=new ToolRect(peni,penis);
    ToolRect eraser=new ToolRect(eraseri,eraseris);

    public ToolRect selt=pen;
    public ToolSelect(){
        this.setLayout(null);

        hand.setSize(40,40);
        hand.setLocation(0,0);
        this.add(hand);

        pen.setSize(40,40);
        pen.setLocation(40,0);
        pen.selected=true;
        this.add(pen);

        eraser.setSize(40,40);
        eraser.setLocation(80,0);
        this.add(eraser);

        hand.addActionListener(this);
        pen.addActionListener(this);
        eraser.addActionListener(this);
    }
}
