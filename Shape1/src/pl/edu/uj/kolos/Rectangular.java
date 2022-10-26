package pl.edu.uj.kolos;

public class Rectangular extends Shape
{
    private double x, y, a, h;
    Rectangular(double x1, double y1, double a1, double h1)
    {
        x = x1;
        y = y1;
        a = a1;
        h = h1;
        super.setName("Rectangular");
    }

    @Override
    public double calculateSurfaceArea()
    {
        return a * h;
    }

    @Override
    public double calculatePerimeter()
    {
        return 2 * (a+h);
    }

    @Override
    public Shape scale(double scalingFactor) throws ScalingFactorException
    {
        if(scalingFactor > 0)
        {
            double a2 = a * scalingFactor;
            double h2 = h * scalingFactor;
            return new Rectangular(x, y, a2, h2);
        }
        else throw new ScalingFactorException("Zly wspolczynnik");
    }
}
