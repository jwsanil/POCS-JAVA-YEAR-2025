package com.example.others.dp.mp.three;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BtnBook extends JButton implements Command
{
    private UIControlPanelMediator mediator;
    public BtnBook(ActionListener actionListener, UIControlPanelMediator mediator){

        super("Book");
        this.addActionListener(actionListener);
        this.mediator = mediator;
        mediator.registerBook(this);
    }


    @Override
    public void execute() {
        mediator.book();

    }
}
