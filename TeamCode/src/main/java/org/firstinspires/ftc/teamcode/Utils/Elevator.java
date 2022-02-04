package org.firstinspires.ftc.teamcode.Utils;

public class Elevator {
    // --F
    private static short eLvl = 0; //height on which the elevator is set
    private static double scale = 1; //scaling for elevator movement

    private static double[] pos; //three predefined positions for the elevator

    // --+
    public boolean setLevel(short h) {

        if (move(pos[h])) {
            eLvl = h;
            return true;
        }
        else
        {
            return false;
        }
    }

    //Moves the elevator slide
    public boolean move(double y) {

        return true;
    }

    public void predefineLevels(double... lvl) {
        pos = lvl;
    }
}
