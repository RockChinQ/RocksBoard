package gui;

import main.Main;
import main.io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorSelect extends JPanel {
    static final Color bg=new Color(220,220,220),sbg=new Color(180,180,180);
    class ColorRect extends JButton{
        public byte clno=0;
        boolean select=false;
        public void paint(Graphics g){
            g.setColor(select?sbg:bg);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.setColor(Main.mw.bp.clMap.get(clno));
            g.fillRect((int)(this.getWidth()*0.3/2),(int)(this.getHeight()*0.3/2),(int)(this.getWidth()*0.7),(int)(this.getHeight()*0.7));
        }
        public ColorRect(byte cln){
            this.clno=cln;
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selt.select=false;
                    selt=(ColorRect)e.getSource();
                    selt.select=true;
                    Main.mw.bp.clNo=cln;
                    Main.mw.ts.selt.selected=false;
                    Main.mw.ts.selt=Main.mw.ts.pen;
                    Main.mw.ts.selt.selected=true;
                    io.say("change cl:"+Main.mw.bp.clNo);


                    Main.mw.er.setVisible(false);
                    Main.mw.tc.setVisible(false);
                    Main.mw.cs.repaint();
                    Main.mw.ts.repaint();
                    Main.mw.requestFocus();
                }
            });
        }
    }

    class crAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //Main.mw.bp.clNo=clno;
            io.say("change cl:"+Main.mw.bp.clNo);
        }
    }

    ColorRect selt;

    public ColorSelect(){
        this.setLayout(null);
    }
    public void updateCom(){
        this.removeAll();
        for(byte i=0;i<Main.mw.bp.clAmount;i++){
            ColorRect crt=new ColorRect(i);
            crt.setSize(40,40);
            crt.setLocation(i*40,0);
            crt.repaint();
            this.add(crt);
            if(i==0){
                crt.select=true;
                selt=crt;
            }
        }
        this.repaint();
    }
}
