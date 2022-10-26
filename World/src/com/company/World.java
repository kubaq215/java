package com.company;

public class World
{
    double width, lenght;

    static class BoundaryException extends Exception
    {
        public BoundaryException(String errMess)
        {
            super(errMess);
        }
    }

    World(double x, double y) throws BoundaryException
    {
        if(x <= 0 || y <= 0)
            throw new BoundaryException("Ujemne parametry swiata");
        else
        {
            width = x;
            lenght = y;
        }
    }
}
