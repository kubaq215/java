package com.company;

import java.io.*;
import java.util.Scanner;

public class Cryptographer
{
    public void cryptfile(String fin, String fout, Algorithm algorithm) throws IOException
    {
        File filein = new File(fin);
        FileWriter writer = new FileWriter(fout);
        Scanner scan = new Scanner(filein);
        ROT11 a = new ROT11();
        if(algorithm.equals(a)) scan.useDelimiter("[^A-Za-z0-9]+");
        else scan.useDelimiter("[^A-Za-z]+");
        while(scan.hasNext())
        {
            writer.write(algorithm.crypt(scan.next()) + " ");
        }
        writer.close();
        scan.close();
    }

    public void decryptfile(String fin, String fout, Algorithm algorithm) throws IOException {
        File filein = new File(fin);
        FileWriter writer = new FileWriter(fout);
        Scanner scan = new Scanner(filein);
        scan.useDelimiter(" ");
        int i = 1;
        while(scan.hasNext())
        {
            writer.write(algorithm.decrypt(scan.next()) + " ");
            if(i % 6 == 0)
                writer.write("\n");
            i++;
        }
        writer.close();
        scan.close();
    }

}
