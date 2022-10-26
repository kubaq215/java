package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Choose the number of elevators - ");
        int numberOfElevators = s.nextInt();
	    Administrator admin = new Administrator(numberOfElevators);
        System.out.println("Current configuration:");
        System.out.println(admin.status());
        while(true)
        {
            System.out.println("---------------------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("1. Call for an elevator");
            System.out.println("2. Request the floor from the inside of an elevator");
            System.out.println("3. Get status of all of the elevators");
            System.out.println("4. Push forward time by 1 step");
            System.out.println("5. Exit");
            System.out.print("Enter your answer here - ");
            int choice = s.nextInt();
            switch(choice)
            {
                case 1:
                    System.out.print("Enter the floor number: ");
                    int floor = s.nextInt();
                    System.out.println("In which direction do you want to go?");
                    System.out.println("1. Up\n2. Down");
                    System.out.print("Enter direction (1/2): ");
                    int direction = s.nextInt();
                    if(direction == 1) admin.requestPickup(Direction.UP, floor);
                    else admin.requestPickup(Direction.DOWN, floor);
                    break;
                case 2:
                    System.out.print("Enter the elevator ID: ");
                    int id = s.nextInt();
                    System.out.print("Enter the floor number: ");
                    int floorToGo = s.nextInt();
                    admin.requestFloor(id, floorToGo);
                case 3:
                    System.out.println(admin.status());
                    break;
                case 4:
                    admin.step();
                    break;
                case 5:
                    System.out.println("Ending..");
                    return;
            }
        }
    }
}
