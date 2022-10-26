package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Test
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<>();
        while(sc.hasNext())
        {
            String word = sc.next();
            if(map.containsKey(word))
            {
                int count = map.get(word);
                map.put(word, count + 1);
            }
            else map.put(word, 1);
        }
    }
}