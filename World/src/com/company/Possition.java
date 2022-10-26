package com.company;

public class Possition
{
    private double x, y;
    Possition(double x1, double y1) { x = x1; y = y1; }

    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public void setX(double x1)
    {
        x = x1;
    }
    public void setY(double y1)
    {
        y = y1;
    }
    public void print()
    {
        System.out.println("Position - " + "(" + x + ", " + y + ")");
    }
}
