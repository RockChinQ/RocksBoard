package gui;

import main.io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame{
    public BoardPanel bp;
    public ColorSelect cs;
    public ToolSelect ts;
    public EraserRect er;
    public StrokeSelect ss;
    public ToolConfig tc;

    public static  final int ERASER_RADIUS=30;
    public MainWindow(){
        this.setUndecorated(true);
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(d);
        this.setLayout(null);


        bp=new BoardPanel((int)d.getWidth(),(int)d.getHeight());
        bp.setLocation(0,0);

        er=new EraserRect();
        er.setLocation(-1*ERASER_RADIUS*2,-1*ERASER_RADIUS*2);
        this.add(er);


        cs=new ColorSelect();
        cs.setSize(bp.clAmount*40,40);
        cs.setLocation((int)(d.getWidth()/2-cs.getWidth()/2),(int)(d.getHeight()-50));
        this.add(cs);

        ts=new ToolSelect();
        ts.setSize(120,40);
        ts.setLocation(cs.getX()-ts.getWidth()-10,(int)(d.getHeight()-50));
        this.add(ts);

        ss=new StrokeSelect();
        ss.setSize(120,40);
        ss.setLocation(cs.getWidth()+cs.getX()+10,(int)(d.getHeight()-50));
        this.add(ss);

        tc=new ToolConfig();
        tc.setLocation(ts.getX()-3,ts.getY()-55);
        tc.setVisible(false);
        this.add(tc);

        this.add(bp);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                //io.say(e.getKeyCode()+"");
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.setFocusable(true);
        this.requestFocus();
    }
}
