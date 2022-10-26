package pl.edu.uj.kolos;

public class Circle extends Shape
{
    private double x, y, r;
    Circle(double x1, double y1, double r1)
    {
        x = x1;
        y = y1;
        r = r1;
        super.setName("Circle");
    }

    @Override
    public double calculateSurfaceArea()
    {
        return Math.PI * r * r;
    }

    @Override
    public double calculatePerimeter()
    {
        return 2 * Math.PI * r;
    }

    @Override
    public Shape scale(double scalingFactor) throws ScalingFactorException
    {
        if(scalingFactor > 0)
        {
            double r2 = r * scalingFactor;
            return new Circle(x, y, r2);
        }
        else throw new ScalingFactorException("Zly wspolczynnik");
    }
}
