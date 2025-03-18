package com.example.others.dp.mp.three;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {
    private  UIControlPanelMediator uiControlPanelMediator= new PaticipantMediatorImpl();

    public App(){
        JPanel panel = new JPanel();
        panel.add(new BtnView(this,uiControlPanelMediator));
        panel.add(new BtnBook(this, uiControlPanelMediator));
        panel.add(new BtnSearch(this,uiControlPanelMediator));
        getContentPane().add(new LblDisplay(uiControlPanelMediator),"North");
        getContentPane().add(panel, "South");
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Command command = (Command)e.getSource();
        command.execute();

    }
    public  static  void main(String[] args){
        new App();



    }
}
