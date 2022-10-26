package com.company;

import org.junit.Test;
import org.junit.Assert;

public class Tests
{
    @Test
    public void CheckRequestOrder() {
        // given
        Elevator elevator = new Elevator(0);
        elevator.setCurrentFloor(0);
        elevator.requestFloor(1, Direction.UP);
        elevator.requestFloor(2, Direction.UP);
        elevator.requestFloor(3, Direction.DOWN);
        elevator.requestFloor(4, Direction.DOWN);

        // when
        elevator.step(); // going to 1st floor
        elevator.requestFloor(5, Direction.UP);
        elevator.step(); // going to 2nd floor
        elevator.requestFloor(6, Direction.UP);
        elevator.step(); // going to 3rd floor
        elevator.step(); // going to 4th floor
        elevator.step(); // going to 5th floor
        elevator.step(); // going to 6th floor
        elevator.step(); // going to 5th floor
        elevator.step(); // going to 4th floor
        elevator.requestFloor(5, Direction.UP);
        elevator.step(); // going to 3rd floor
        elevator.requestFloor(2, Direction.DOWN);
        elevator.step(); // going to 2nd floor

        // then
        boolean is_equal = (2 == elevator.getStatus().get(1));
        Assert.assertTrue(is_equal);
    }
}
