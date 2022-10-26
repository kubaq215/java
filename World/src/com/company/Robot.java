package com.company;

import java.util.ArrayList;

public class Robot implements Movable
{
    ArrayList<Possition> pos = new ArrayList<>();

    Robot()
    {
        Possition p = new Possition(0, 0);
        pos.add(p);
    }

    public Possition getPosition()
    {
        return pos.get(pos.size() - 1);
    }

    static class OutsiteTheWorldException extends Exception
    {
        public OutsiteTheWorldException(String errMess)
        {
            super(errMess);
        }
    }

    public void MoveTo(Possition possition, World world) throws OutsiteTheWorldException
    {
        if(possition.getX() <= world.width && possition.getY() <= world.lenght && possition.getY() >= 0 && possition.getX() >= 0)
            pos.add(possition);
        else
            throw new OutsiteTheWorldException("Pozycja poza granicami swiata");
    }

    public void MoveBack()
    {
        if(pos.size() > 1)
            pos.remove(getPosition());
        else
            System.out.println("Nie ma juz gdzie sie cofnac");
    }


}
