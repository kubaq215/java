package pl.edu.uj.kolos;

abstract class Shape
{
    private String name;

    public void setName(String n)
    {
        name = n;
    }

    public abstract double calculateSurfaceArea();

    public abstract double calculatePerimeter();

    public String getName()
    {
        return name;
    }

    public abstract Shape scale(double scalingFactor) throws ScalingFactorException;

}
