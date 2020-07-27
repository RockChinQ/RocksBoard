package gui;

import main.Main;
import main.io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;

public class BoardPanel extends JPanel implements MouseListener, MouseMotionListener {
    public static final Color bg=new Color(30, 40, 30);
    public static final Color blue=new Color(80, 148, 247)
            ,green=new Color(43, 221, 96)
            ,yellow=new Color(255, 209, 69)
            ,orange=new Color(248, 111, 1)
            ,purple=new Color(174, 76, 252)
            ,white=new Color(255,255,255)
            ,gray=new Color(127,127,127)
            ,black=new Color(0,0,0);
    static final int alpha=80;
    public static final Color blue_a=new Color(80, 148, 247,alpha)
            ,green_a=new Color(43, 221, 96,alpha)
            ,yellow_a=new Color(255, 209, 69,alpha)
            ,orange_a=new Color(248, 111, 1,alpha)
            ,purple_a=new Color(174, 76, 252,alpha)
            ,white_a=new Color(255,255,255,alpha)
            ,gray_a=new Color(127,127,127,alpha)
            ,black_a=new Color(0,0,0,alpha);
    public ArrayList<Color> clMap=new ArrayList<Color>() ;//构造函数里面将以上的颜色添加进去
    public int clAmount=8;
    public int clNo=0;//选择的颜色

    //私有
    int pixel[][];
    int w=0,h=0;
    static final int REGION_SIZE=70;

    class Region extends JPanel{
        int sx=0,sy=0;
        int step=1;
        boolean update=true;
        public Region(int sx,int sy){
            this.sx=sx;
            this.sy=sy;
        }
        public void paint(Graphics g){

            g.setColor(bg);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            for(int i=0;i<this.getHeight();i+=step){
                for(int j=0;j<this.getWidth();j+=step){
                    try {
                        if (Main.mw.bp.pixel[i + sy][j + sx] == -1)
                            continue;
                        g.setColor(Main.mw.bp.clMap.get(Main.mw.bp.pixel[i + sy][j + sx]));
                        g.fillRect(j, i, step, step);
                    }catch (Exception e){}
                }
            }
            update=false;
        }
    }

    Region reg[][];
    public BoardPanel(int w,int h){
        this.setLayout(null);
        this.w=w;
        this.h=h;
        pixel=new int[h][w];
        clMap.add(blue);
        clMap.add(green);
        clMap.add(yellow);
        clMap.add(orange);
        clMap.add(purple);
        clMap.add(white);
        clMap.add(gray);
        clMap.add(black);

        clAmount= clMap.size();

        clMap.add(blue_a);
        clMap.add(green_a);
        clMap.add(yellow_a);
        clMap.add(orange_a);
        clMap.add(purple_a);
        clMap.add(white_a);
        clMap.add(gray_a);
        clMap.add(black_a);
        //初始化
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                pixel[i][j]=-1;
            }
        }
        //添加区块面板
        //长宽为30
        int regAmountX=(int)Math.ceil((float)w/REGION_SIZE),regAmountY=(int)Math.ceil((float)h/REGION_SIZE);
        reg=new Region[regAmountY][regAmountX];
        io.say("w,h:"+w+","+h+" rax,ray:"+regAmountX+","+regAmountY);
        for(int i=0;i<regAmountY;i++){
            for(int j=0;j<regAmountX;j++){
                reg[i][j]=new Region(j*REGION_SIZE,i*REGION_SIZE);
                reg[i][j].setSize(REGION_SIZE,REGION_SIZE);
                reg[i][j].setLocation(j*REGION_SIZE,i*REGION_SIZE);
                io.say(reg[i][j].getLocation()+"");
                this.add(reg[i][j]);
            }
        }

        this.setSize(w,h);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

       // this.repaint();
    }
//    public void paint(Graphics g){
//        long st=new Date().getTime();
//        io.say("repaint s:"+st);
//        g.setColor(bg);
//        g.fillRect(0,0,this.getWidth(),this.getHeight());
//        for(int i=0;i<h;i++){
//            for(int j=0;j<w;j++){
//                if(pixel[i][j]==-1)
//                    continue;
//                g.setColor(clMap.get(pixel[i][j]));
//                g.fillRect(j,i,1,1);
//            }
//        }
//        long deltat=(new Date().getTime()-st);
//        io.say(deltat+"ms done.");
//        g.setColor(Color.white);
//        g.drawString("uptime:"+deltat+" fps "+(float)1000/(float)deltat+"",10,20);
//    }


    void dot(Point p){
        int x=(int)p.getX(),y=(int)p.getY();
        if(Main.mw.ts.selt==Main.mw.ts.pen) {
            io.say("dot at" + (int) p.getX() + "," + (int) p.getY() + " cl:" + this.clMap.get(this.clNo));
            //pixel[(int) p.getY()][(int) p.getX()] = this.clNo;
            put((int) p.getX(),(int) p.getY());
            //this.getGraphics().setColor(this.clMap.get(this.clNo));
            //this.getGraphics().fillRect((int)p.getX(),(int)p.getY(),5,5);
        }else if(Main.mw.ts.selt==Main.mw.ts.eraser){
            //Main.mw.er.setVisible(true);
            //Main.mw.er.setLocation(x-10,y-10);
//            for(int i=(y-20)>0?y-20:0;i<((y+20)<pixel.length?y+20:pixel.length);i++){
//                for(int j=(x-20)>0?x-20:0;j<((x+20)<pixel[0].length?x+20:pixel.length);j++){
//                    pixel[i][j]=-1;
//                    reg[i/REGION_SIZE][j/REGION_SIZE].update=true;
//                }
//            }
            put((int) p.getX(),(int) p.getY());
        }
        //Main.mw.repaint();

        regRepaint();
        Main.mw.ts.repaint();
        Main.mw.ss.repaint();
        Main.mw.cs.repaint();
    }

    /*考虑粗细的放置*/
    void put(int x,int y){
        if(Main.mw.ts.selt==Main.mw.ts.pen) {
            int radius=Main.mw.ss.selt.v/2+1;
            for (int i = (y - radius) > 0 ? (y - radius) : 0
                 ; i < ((y + radius) < Main.mw.bp.getHeight() ? (y + radius) : Main.mw.bp.getHeight())
                    ; i++) {
                for (int j = (x - radius) > 0 ? (x - radius) : 0
                     ; j < ((x + radius) < Main.mw.bp.getWidth() ? (x + radius) : Main.mw.bp.getWidth())
                        ; j++) {
                    //statement: i==(y-radius)||i==(y+radius-1)||j==(x-radius)||j==(x+radius-1)
                    //抗锯齿
                    if((i==(y-radius)||i==(y+radius-1)||j==(x-radius)||j==(x+radius-1))){
                        if(pixel[i][j]==-1||pixel[i][j]>=clAmount){
                            pixel[i][j]=this.clNo+this.clAmount;
                        }else {
                            pixel[i][j]=this.clNo;
                        }
                    }else {
                        pixel[i][j]=this.clNo;
                    }
//                    if(pixel[i][j]==-1)
//                        pixel[i][j] = (i==(y-radius)||i==(y+radius-1)||j==(x-radius)||j==(x+radius-1))?(this.clNo+this.clAmount):this.clNo;
//                    else
//                        pixel[i][j]=this.clNo;
                    reg[i / REGION_SIZE][j / REGION_SIZE].update = true;
                }
            }
        }else if(Main.mw.ts.selt==Main.mw.ts.eraser){
            for(int i = Math.max((y - MainWindow.ERASER_RADIUS), 0); i<((y+MainWindow.ERASER_RADIUS)<pixel.length?y+MainWindow.ERASER_RADIUS:pixel.length); i++){
                for(int j = Math.max((x - MainWindow.ERASER_RADIUS), 0); j<((x+MainWindow.ERASER_RADIUS)<pixel[0].length?x+MainWindow.ERASER_RADIUS:pixel.length); j++){
                    pixel[i][j]=-1;
                    reg[i/REGION_SIZE][j/REGION_SIZE].update=true;
                }
            }
        }
    }

    Point lsp=new Point(-1,-1);
    void drag(int x,int y){

            //如果上一次有值
            if (lsp.getX() != -1 && lsp.getY() != -1) {
                line(x, y, (int) lsp.x, (int) lsp.y);
                //pixel[y][x]=this.clNo;
            } else {
                //pixel[y][x] = this.clNo;
                put(x,y);
            }
            lsp.setLocation(x, y);

        if(Main.mw.ts.selt==Main.mw.ts.eraser){
            Main.mw.er.setVisible(true);
            Main.mw.er.setLocation(x-Main.mw.er.getWidth()/2,y-Main.mw.er.getHeight()/2);
//            for(int i=(y-20)>0?y-20:0;i<((y+20)<pixel.length?y+20:pixel.length);i++){
//                for(int j=(x-20)>0?x-20:0;j<((x+20)<pixel[0].length?x+20:pixel.length);j++){
//                    pixel[i][j]=-1;
//                    reg[i/REGION_SIZE][j/REGION_SIZE].update=true;
//                }
//            }

        }
        //Main.mw.repaint();
        regRepaint();
        Main.mw.ts.repaint();
        Main.mw.ss.repaint();
        Main.mw.cs.repaint();
    }

    public void line(int x0,int y0,int x1,int y1) {
        //System.out.println("line");
        int dx=x1-x0,dyssb=y1-y0;
        //System.out.println("dx:"+dx+" dy:"+dyssb);
        if(Math.abs(dx)>Math.abs(dyssb)) {//长大于高
            //System.out.println("0");
            int unit=dx/Math.abs(dx);
            double step=(double)dyssb/(double)dx;
            //statement: unit>0&&x<x1||unit<0&&x>x1
            for(int x=x0;(unit>0&&x<x1||unit<0&&x>x1);x+=unit) {
                //pixel[(int) (y0+step*(x-x0))][x]=this.clNo;
                put(x,(int) (y0+step*(x-x0)));
            }
        }else if(Math.abs(dyssb)>=Math.abs(dx)) {
            //System.out.println("1");
            int unit=dyssb/Math.abs(dyssb);
            double step=(double)dx/(double)dyssb;
            //System.out.println("step:"+step);
            //statement: unit>0&&y<y1||unit<0&&y>y1
            for(int y=y0;(unit>0&&y<y1||unit<0&&y>y1);y+=unit) {
                //pixel[y][(int) (x0+step*(y-y0))]=clNo;
                put((int) (x0+step*(y-y0)),y);
            }
        }
        //System.out.println("============");
    }

    public void regRepaint(){
        try {
            for (int i = 0; i < reg.length; i++) {
                for (int j = 0; j < reg[0].length; j++) {
                    if (reg[i][j].update&&Main.mw.ts.selt==Main.mw.ts.pen)
                        reg[i][j].repaint();
                    else if(Main.mw.ts.selt==Main.mw.ts.eraser) {
                        if(!reg[i][j].update)
                            reg[i][j].step=3;
                        else
                            reg[i][j].step=1;
                        reg[i][j].repaint();
                    }
                }
            }
            Main.mw.requestFocus();
        }catch (Exception e){}
    }
    public void repaintCom(){
        Main.mw.ts.repaint();
        Main.mw.ss.repaint();
        Main.mw.cs.repaint();
//        Main.mw.cs.setVisible(true);
        Main.mw.tc.repaint();

    }
    public void resetStep(){
        for(int i=0;i<reg.length;i++){
            for(int j=0;j<reg[0].length;j++){
                reg[i][j].step=1;
                reg[i][j].repaint();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //repaintCom();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(Main.mw.ts.selt==Main.mw.ts.pen||Main.mw.ts.selt==Main.mw.ts.eraser) {
            dot(e.getPoint());
            lsp.setLocation(e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lsp.setLocation(-1,-1);
        //Main.mw.er.setVisible(false);
        if(Main.mw.ts.selt==Main.mw.ts.eraser)
            resetStep();
//
//        repaintCom();
        Main.mw.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(Main.mw.ts.selt==Main.mw.ts.pen||Main.mw.ts.selt==Main.mw.ts.eraser) {
            drag(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
