package com.company;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj pesel: ");
        String pesel1 = scan.nextLine();
        Pesel a = new Pesel(pesel1);
        Pesel.check(pesel1);
        System.out.println("Podaj drugi pesel:");
        String pesel2 = scan.nextLine();
        Pesel b = new Pesel(pesel2);
        if(a.compare(b))
            System.out.println("Pesele są takie same");
        else
            System.out.println("Pesele są różne");
    }
}
