package com.company;


import java.util.Collections;
import java.util.List;

public interface ElevatorSystem
{
    void requestPickup(Direction direction, int floor);
    void requestFloor(int ID, int targetFloor);
    void step();
    List<List<Integer>> status();
}
