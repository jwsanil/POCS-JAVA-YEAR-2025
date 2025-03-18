package com.example.others.dp.mp.three;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LblDisplay extends JButton implements Command {


    private UIControlPanelMediator mediator;

    public LblDisplay(UIControlPanelMediator mediator){

        super(" Just restart ..");
        this.mediator = mediator;
        mediator.registerDisplay(this);
        setFont(new java.awt.Font("Arial", Font.BOLD, 24));
    }
    @Override
    public void execute() {
mediator.book();
    }
}
