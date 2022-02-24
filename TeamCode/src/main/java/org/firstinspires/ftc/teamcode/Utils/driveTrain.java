package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class driveTrain{
    // define motors
    public DcMotor lf = null;
    public DcMotor rf = null;
    public DcMotor lb = null;
    public DcMotor rb = null;
    public double lfPow = 0;
    public double rfPow = 0;
    public double lbPow = 0;
    public double rbPow = 0;
    
    public driveTrain(DcMotor lf, DcMotor rf, DcMotor lb, DcMotor rb){
        this.lf = lf;
        this.rf = rf;
        this.lb = lb;
        this.rb = rb;
    }

    public driveTrain(RobotBase robot){
        robot.leftFront  = lf;
        robot.rightFront = rf;
        robot.leftBack   = lb;
        robot.rightBack  = rb;
    }
    
    public void calculatePower (double x, double y, double turn) {
        //calculate the largest power to preserve ratios after being clipped
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);
        //calculate individual wheel power
        lfPow = (y - x + turn) / denominator; //Left Front
        rfPow = (y + x - turn) / denominator; //Right Front
        lbPow = (y + x + turn) / denominator; //Left Rear
        rbPow = (y - x - turn) / denominator; //Right Rear
    }
    public void setPower (double x, double y, double turn) {
        calculatePower(x, y, turn); //update wheel powers
        //set wheel powers
        lf.setPower(lfPow); // Left Front
        rf.setPower(rfPow); // Right Front
        lb.setPower(lbPow); // Left Back
        rb.setPower(rbPow); // Right Back
    }
}
