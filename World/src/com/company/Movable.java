package com.company;

public interface Movable
{
    void MoveTo(Possition possition, World world) throws Robot.OutsiteTheWorldException;

    void MoveBack();
}
