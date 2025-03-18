package com.example.others.dp.mp.three;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BtnSearch extends JButton implements  Command {

    private UIControlPanelMediator mediator;

    public BtnSearch(ActionListener actionListener,UIControlPanelMediator mediator){
        super("Search");
        addActionListener(actionListener);
        this.mediator = mediator;
        mediator.registerSearch(this);

    }

    @Override
    public void execute() {
mediator.search();
    }
}
