package com.company;

public class Bee extends Thread
{
    final int id;
    private int entry = 0, exit = 0;
    private boolean location = false;

    public Bee(int x)
    {
        id = x;
    }

    public void move()
    {
        location = !location;
        if(location)
        {
            entry++;
            System.out.println("Pszczola " + id + " wlatuje do ula.");
        }
        else
        {
            exit++;
            System.out.println("Pszczola " + id + " wylatuje z ula.");
        }
    }

    public void run()
    {
        while(true)
        {
            if(Hive.first.taken && Hive.second.taken)
            {
                System.out.println("Pszczola " + id + " oba wejscia zajete, czekam.");

                synchronized(this)
                {
                    synchronized(Hive.Entrance.queue)
                    {
                        Hive.Entrance.queue.add(this);
                    }
                    try { wait(); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
            else if(Hive.first.taken == false)
            {
                synchronized(Hive.first)
                {
                    Hive.first.taken = true;
                    move();
                    Hive.first.taken = false;
                    synchronized(Hive.Entrance.queue)
                    {
                        if(!Hive.Entrance.queue.isEmpty())
                            synchronized(Hive.Entrance.queue.peek())
                            {
                                Hive.Entrance.queue.remove().notify();
                            }
                    }
                }
            }
            else if(Hive.second.taken == false)
            {
                System.out.println("Pszczola " + id + " wejscie nr 1 zajete, sprawdzam drugie.");
                synchronized(Hive.second)
                {
                    Hive.second.taken = true;
                    move();
                    Hive.second.taken = false;
                    synchronized(Hive.Entrance.queue)
                    {
                        if(!Hive.Entrance.queue.isEmpty())
                            synchronized(Hive.Entrance.queue.peek())
                            {
                                Hive.Entrance.queue.remove().notify();
                            }
                    }
                }
            }

            int time;

            if(location)
            {
                time = (int) Math.floor(Math.random()*(5-1+1)+1);
                try { sleep(time * 1000L); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
            else
            {
                time = (int) Math.floor(Math.random()*(10-5+1)+5);
                try { sleep(time * 1000L); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    public String wypisz()
    {
        return "Pszczola " + id + " wleciala do ula " + entry + " razy i " + exit + " razy z niego wyleciala.\n";
    }
}

