package com.company;

import java.io.IOException;
import java.util.Objects;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Algorithm b = new ROT11();
        Algorithm c = new Polibiusz();
        Cryptographer a = new Cryptographer();
        if(Objects.equals(args[2], "crypt"))
        {
            if (Objects.equals(args[3], "rot"))
                a.cryptfile(args[0], args[1], b);
            else if (Objects.equals(args[3], "poli"))
                a.cryptfile(args[0], args[1], c);
            else
                System.out.println("Nieprawidlowa nazwa algorytmu szyfrujacego.\n" +
                        "Dla polibiusza wpisz \"poli\" a dla rot wpisz \"rot\"");
        }
        else if(Objects.equals(args[2], "decrypt"))
        {
            if (Objects.equals(args[3], "rot"))
                a.decryptfile(args[0], args[1], b);
            else if (Objects.equals(args[3], "poli"))
                a.decryptfile(args[0], args[1], c);
            else
                System.out.println("Nieprawidlowa nazwa algorytmu szyfrujacego.\n" +
                        "Dla polibiusza wpisz \"poli\" a dla rot wpisz\"rot\"");
        }
        else System.out.println("Jako 3 argument podaj \"crypt\" lub \"decrypt\".");
    }

}
