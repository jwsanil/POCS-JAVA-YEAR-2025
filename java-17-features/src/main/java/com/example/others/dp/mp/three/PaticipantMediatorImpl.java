package com.example.others.dp.mp.three;

public class PaticipantMediatorImpl implements  UIControlPanelMediator {

    private BtnView btnView;
    private BtnSearch btnSearch;
    private BtnBook btnBook;
    private  LblDisplay display;

    @Override
    public void registerView(BtnView v) {
        this.btnView=v;

    }

    @Override
    public void registerSearch(BtnSearch s) {
this.btnSearch =s;
    }

    @Override
    public void registerBook(BtnBook b) {
        this.btnBook = b;

    }

    @Override
    public void registerDisplay(LblDisplay d) {
        display=d;
    }

    @Override
    public void book() {
        btnBook.setEnabled(false);
        btnView.setEnabled(true);
        btnSearch.setEnabled(true);

        display.setText("Booking ..");

    }

    @Override
    public void view() {

        btnView.setEnabled(false);
        btnSearch.setEnabled(true);
        btnBook.setEnabled(true);
        display.setText("Viewing...");

    }

    @Override
    public void search() {


        btnSearch.setEnabled(false);
        btnView.setEnabled(true);
        btnBook.setEnabled(true);
        display.setText("Searching..");
    }

}
