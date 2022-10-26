package com.company;

public class ROT11 implements Algorithm
{
    static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public String crypt(String word)
    {
        String cword = "";
        for(int i=0; i<word.length(); i++)
        {
            char symbol = alphabet.charAt((alphabet.indexOf(alphabet.charAt(alphabet.indexOf(word.charAt(i)))) + 11) % 62);
            cword += symbol;
        }
        return cword;
    }

    public String decrypt(String word)
    {
        String dcword = "";
        for(int i=0; i<word.length(); i++)
        {
            int k = alphabet.indexOf(alphabet.charAt(alphabet.indexOf(word.charAt(i)))) - 11;
            char symbol;
            if(k >= 0) symbol = alphabet.charAt(k);
            else symbol = alphabet.charAt(62 + k);
            dcword += symbol;
        }
        return dcword;
    }
}
