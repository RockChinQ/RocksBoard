package main;

import gui.MainWindow;

public class Main {
    public static MainWindow mw;
    public static void main(String[] args){

        //System.out.println("launch");
        mw=new MainWindow();
        mw.cs.updateCom();
    }
}
