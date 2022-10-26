package com.company;

import java.util.Scanner;

public class Main
{
    static int menu()
    {
        do
        {
            System.out.println("\n1. moveTo");
            System.out.println("2. moveBack");
            System.out.println("3. Print robot location");
            System.out.println("4. Exit");

            Scanner scan = new Scanner(System.in);
            int k = Integer.parseInt(scan.nextLine());
            if(k > 0 && k<5)
                return k;
            else
                System.out.println("Nieprawidlowy wybor");

        } while(true);
    }

    public static void main(String[] args) throws World.BoundaryException, Robot.OutsiteTheWorldException
    {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        World world = new World(x, y);
        Robot robot = new Robot();

        while(true)
            switch (menu())
            {
                case 1 ->
                {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Podaj pozycje");
                    System.out.print("x - ");
                    double xr = Double.parseDouble(scan.nextLine());
                    System.out.print("y - ");
                    double yr = Double.parseDouble(scan.nextLine());
                    Possition p = new Possition(xr, yr);
                    robot.MoveTo(p, world);
                }
                case 2 -> robot.MoveBack();
                case 3 -> robot.getPosition().print();
                case 4 -> System.exit(0);
            }
    }
}
