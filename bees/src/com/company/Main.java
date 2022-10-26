package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Hive hive = new Hive(Integer.parseInt(args[0]));
        hive.start();
        hive.stop();
    }
}
