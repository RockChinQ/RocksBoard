package gui;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolConfig extends JPanel {
    static final Color bg=new Color(220,220,220),sbg=new Color(184,184,184);
    class TConfigRect extends JButton{
        //ToolConfigRectDo tcrd;
        ImageIcon ii;
        TConfigRect(ToolConfigRectDo tcrd,ImageIcon ii){
            this.ii=ii;
            this.addActionListener((event)->{tcrd.action();Main.mw.requestFocus();});
        }
        public void paint(Graphics g){
            g.setColor(bg);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.drawImage(ii.getImage(),0,0,this);
        }
    }

    static final int ERASER_MENU=0;

    int mode=0;

    ToolConfigRectDo clear_all=()->{
        for(int i=0;i< Main.mw.bp.getHeight();i++){
            for(int j=0;j<Main.mw.bp.getWidth();j++){
                Main.mw.bp.pixel[i][j]=-1;
            }
        }
        Main.mw.tc.setVisible(false);
        Main.mw.repaint();
    };
    TConfigRect clearAll=new TConfigRect(clear_all,new ImageIcon("resource\\clearall.png"));
    public ToolConfig(){
        this.setLayout(null);
        this.setSize(200,45);
        this.setBackground(null);
        this.setOpaque(false);

        clearAll.setSize(45,45);
        clearAll.setLocation(80,0);
        this.add(clearAll);
    }
}
