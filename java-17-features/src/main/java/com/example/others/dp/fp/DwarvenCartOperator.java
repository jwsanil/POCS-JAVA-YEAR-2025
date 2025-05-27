package com.example.others.dp.fp;


public class DwarvenCartOperator extends DwarvenMineWorker {

    @Override
    public void work() {
       System.out.println("{} moves gold chunks out of the mine."+ name());
    }

    @Override
    public String name() {
        return "Dwarf cart operator";
    }
}
