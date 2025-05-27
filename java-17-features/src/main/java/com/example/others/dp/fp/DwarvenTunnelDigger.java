package com.example.others.dp.fp;





public class DwarvenTunnelDigger extends DwarvenMineWorker {

    @Override
    public void work() {
        System.out.println("{} creates another promising tunnel."+ name());
    }

    @Override
    public String name() {
        return "Dwarven tunnel digger";
    }
}

