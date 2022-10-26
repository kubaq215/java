package pl.edu.uj.kolos;

import java.util.Scanner;

public class Main
{
    static int menu()
    {
        do
        {
            System.out.println("\n1. Dodaj kwadrat");
            System.out.println("2. Dodaj okrąg");
            System.out.println("3. Wyświetl pole powierzchni");
            System.out.println("4. Wyświetl obwód");
            System.out.println("5. Skaluj obiekty");
            System.out.println("6. Print names");
            System.out.println("7. Remove object of index");
            System.out.println("8. Exit");

            Scanner scan = new Scanner(System.in);
            int k = Integer.parseInt(scan.nextLine());
            if(k > 0 && k<9)
                return k;
            else
                System.out.println("Nieprawidlowy wybor");

        } while(true);
    }

    public static void main(String[] args) throws ScalingFactorException {
        BagOfShapes bag = new BagOfShapes();

        while (true)
            switch (menu())
            {
                case 1 ->
                        {
                            Scanner scan = new Scanner(System.in);
                            System.out.println("Podaj wspolrzedne: ");
                            System.out.print("x - ");
                            double xr = Double.parseDouble(scan.nextLine());
                            System.out.print("y - ");
                            double yr = Double.parseDouble(scan.nextLine());
                            System.out.print("dl boku - ");
                            double ar = Double.parseDouble(scan.nextLine());
                            Rectangular kwadrat = new Rectangular(xr, yr, ar, ar);
                            bag.arr.add(kwadrat);
                        }
                case 2 ->
                        {
                            Scanner scan = new Scanner(System.in);
                            System.out.println("Podaj wspolrzedne: ");
                            System.out.print("x - ");
                            double xr = Double.parseDouble(scan.nextLine());
                            System.out.print("y - ");
                            double yr = Double.parseDouble(scan.nextLine());
                            System.out.print("promien - ");
                            double rr = Double.parseDouble(scan.nextLine());
                            Circle okrag = new Circle(xr, yr, rr);
                            bag.arr.add(okrag);
                        }
                case 3 ->
                        {
                            System.out.println("Suma powierzchni = " + bag.calculateSurfaceArea());
                        }
                case 4 ->
                        {
                            System.out.println("Suma obwodow = " + bag.calculatePerimeter());
                        }
                case 5 ->
                        {
                            Scanner scan = new Scanner(System.in);
                            System.out.println("Podaj wspolczynnik: ");
                            double wsp = Double.parseDouble(scan.nextLine());
                            bag.scale(wsp);
                        }
                case 6 ->
                        {
                            for(int i=0; i<bag.arr.size(); i++)
                            {
                                System.out.print(bag.arr.get(i).getName() + " ");
                            }

                        }
                case 7 ->
                        {
                            Scanner scan = new Scanner(System.in);
                            System.out.println("Ktory element chcesz usunac?");
                            int el = Integer.parseInt(scan.nextLine());
                            if(el <= bag.arr.size())
                                bag.arr.remove(el);
                            else System.out.println("Nie ma takiego elementu");
                        }
                case 8 ->
                        {
                            System.exit(0);
                        }
            }
    }
}

