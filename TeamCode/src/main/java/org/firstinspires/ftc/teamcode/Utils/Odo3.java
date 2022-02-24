package org.firstinspires.ftc.teamcode.Utils;

import android.util.Pair;

import androidx.annotation.NonNull;

public class Odo3 {
    // F (configurable)
    private double w;               // distance between wheels / wheel span
    private double iTheta = 0.0;      // initial angle

    // F
    private double cX = 0.0;        // current X, from the beginning of runtime
    private double cY = 0.0;
    private double cTheta = 0.0;    // radians

    private double dL, dR, dM;      // arc length, dM derives from dL and dR
    private double thRot;           // angle of the overall change in x and y |

    private double rL, rR, rM;      // radius from center of rotation, rM derives from rL or rR
    private double hyp;             // assumed distance traveled from frame n to n+1

    private double magDS;
    private double thN;

    private double wX;
    private double wY;

    public Odo3(double wheelSpan) {
        w = wheelSpan;
    }

    public void ConfigureRobot(Pair<String, Double>... args) { // must use pair
        for (Pair<String, Double> p : args) {
            //this.p[0]
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
        cTheta = cTheta + thRot;
    }

}