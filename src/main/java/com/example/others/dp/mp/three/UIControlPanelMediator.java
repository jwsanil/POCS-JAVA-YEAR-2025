package com.example.others.dp.mp.three;

//mediator interface
public interface UIControlPanelMediator {

    void book();

    void view();

    void search();

    void registerView(BtnView v);

    void registerSearch(BtnSearch s);

    void registerBook(BtnBook b);

    void registerDisplay(LblDisplay d);
}
