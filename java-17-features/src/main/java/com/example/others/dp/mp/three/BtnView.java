package com.example.others.dp.mp.three;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BtnView  extends JButton implements Command {
  private UIControlPanelMediator mediator;

    @Override
    public void execute() {
mediator.view();
    }

    public BtnView(ActionListener listener, UIControlPanelMediator mediator){

        super("View");
        addActionListener(listener);
        this.mediator = mediator;
        mediator.registerView(this);

    }
}
