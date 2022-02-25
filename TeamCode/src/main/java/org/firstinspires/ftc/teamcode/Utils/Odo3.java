package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

public class Odo3 {
    // F (configurable)
    private double w;               // distance between wheels / wheel span
    private double iTheta = 0.0;    // initial angle
    private double offset = 0.0;    // preferably 0

    // F
    private double cX = 0.0;        // current X, from the beginning of runtime
    private double cY = 0.0;
    private double cTheta = 0.0;    // radians

    private double dL, dR, dM;      // arc length, dM derives from dL and dR
    private double thRot;           // angle of the overall change in x and y |

    private double rL, rR, rM;      // radius from center of rotation, rM derives from rL or rR
    private double hyp;             // assumed distance traveled from frame n to n+1

    private double magDS;           // magnitude of the normal vector of middle wheel
    private double thN;

    private double wX;
    private double wY;

    public Odo3(double wheelSpan) {
        w = wheelSpan;
    }

    public void ConfigureRobot(String varName, double value) {
        switch (varName) {
            case ("iTheta"):
                iTheta = value;
                break;
            case ("w"):
                w = value;
                break;
            case ("offset"):
                offset = value;
                break;
            default:
                return;
        }

        cTheta = iTheta;
    }

    // <
    public double[] getCoordinates() {
        return new double[]{cX, cY};
    }

    public double getAngle() {
        return cTheta;
    }

    // +
    public void setCoordinates(double x, double y) {
        cX = x;
        cY = y;
    }

    public void setAngle(double theta) {
        cTheta = theta;
    }

    // ()
    public void update(double x, double y) {
        dM = (dR + dL)/2;
        thRot = (dR - dL)/w;

        rL = dL/thRot;
        rR = dR/thRot;
        rM = dM/thRot;  //(rL + w/2);

        hyp = (rM * Math.sin(thRot))/Math.cos(thRot/2);

        double newX = hyp * Math.cos(cTheta + (thRot/2));
        double newY = hyp * Math.sin(cTheta + (thRot/2));
        double newTh = cTheta + thRot;

        thN = iTheta + (thRot/2) + Math.PI/2;

        wX = magDS * Math.cos(thN);
        wY = magDS * Math.sin(thN);

        cX = x + newX;
        cY = y + newY;
        cTheta = newTh;
    }

    public void clean() {
        w = 0.0;
        iTheta = 0.0;
        offset = 0.0;
        cX = 0.0;
        cY = 0.0;
        cTheta = 0.0;
        dL = dR = dM = 0.0;
        thRot = 0.0;
        rL = rR = rM = 0.0;
        hyp = 0.0;
        magDS = 0.0;
        thN = 0.0;
        wX = 0.0;
        wY = 0.0;
    }

}