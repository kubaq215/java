package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class Hive
{
    class Entrance extends Thread
    {
        boolean taken;
        public Entrance()
        {
            taken = false;
            start();
        }
        static Queue<Bee> queue = new ArrayDeque<>();
    }

    static Entrance first;
    static Entrance second;

    private Bee[] bees;

    public Hive(int number_of_bees)
    {
        first = new Entrance();
        second = new Entrance();

        bees = new Bee[number_of_bees];
        for(int i=1; i<=number_of_bees; i++)
            bees[i-1] = new Bee(i);
    }

    public void start() throws InterruptedException {
        for(Bee bee : bees)
            bee.start();
        Thread.sleep(1000 * 20);
        for(Bee bee : bees)
            bee.stop();
    }

    public void stop() throws InterruptedException, IOException
    {
        FileWriter writer = new FileWriter("Dane_pszczol.txt");
        for(Bee bee : bees)
        {
            bee.join();
            writer.write(bee.wypisz());
        }
        writer.close();
    }
}
