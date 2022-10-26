package pl.edu.uj.kolos;

import java.util.ArrayList;

public class BagOfShapes extends Shape
{
    ArrayList<Shape> arr = new ArrayList<>();

    @Override
    public double calculateSurfaceArea()
    {
        double s = 0;
        for(int i=0; i<arr.size(); i++)
        {
            s += arr.get(i).calculateSurfaceArea();
        }
        return s;
    }

    @Override
    public double calculatePerimeter()
    {
        double s = 0;
        for(int i=0; i<arr.size(); i++)
        {
            s += arr.get(i).calculateSurfaceArea();
        }
        return s;
    }

    @Override
    public Shape scale(double scalingFactor) throws ScalingFactorException
    {
        for(int i=0; i<arr.size(); i++)
        {
            arr.get(i).scale(scalingFactor);
        }
        return this;
    }
}
