package com.company;

import java.util.Objects;

public class Pesel
{
    private final String numer;
    public Pesel(String p1) { numer = p1; }

    public boolean compare(Pesel P)
    {
        return Objects.equals(numer, P.numer);
    }

    public static void check(String s)
    {
        if(s.length() != 11)
            System.out.println("Niepoprawna ilość cyfr.");
        else
        {
            int[] tab = new int[s.length()];

            for (int i = 0; i < s.length(); i++)
                tab[i] = s.charAt(i) - '0';

            int S;
            S = tab[0]*1 + tab[1]*3 + tab[2]*7 + tab[3]*9 + tab[4]*1 + tab[5]*3 + tab[6]*7 + tab[7]*9 + tab[8]*1 + tab[9]*3;
            S = S % 10;
            S = 10 - S;
            S = S % 10;

            if (S == tab[10])
                System.out.println("Poprawny pesel");
            else
                System.out.println("Niepoprawny pesel");
        }
    }
}
