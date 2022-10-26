package com.company;

import java.util.Locale;

public class Polibiusz implements Algorithm
{
    public String crypt(String word)
    {
        String cword = "";
        int row, col;
        word = word.toUpperCase(Locale.ROOT);
        for(int i=0; i<word.length(); i++)
        {
            row = (int) Math.ceil((word.charAt(i) - 'A') / 5) + 1;
            col = ((word.charAt(i) - 'A') % 5) + 1;
            cword += row + "" + col;
        }
        return cword;
    }

    public String decrypt(String word)
    {
        String dcword = new String();
        int[][] a = new int[6][5];
        int k = 65;
        for(int i=0; i<6; i++)
            for(int j=0; j<5; j++)
            {
                a[i][j] = k;
                k++;
            }
        for(int i=0; i<word.length(); i+=2)
        {
            int row = word.charAt(i) - '0' - 1;
            int col = word.charAt(i+1) - '0' - 1;
            dcword += (char) a[row][col];
        }
        return dcword;
    }
}
