package org.firstinspires.ftc.teamcode.Utils;

import  com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PID {
    private double kp = 0;
    private double ki = 0;
    private double kd = 0;
    
    private double integral = 0;
    private double previousError = 0;
    
    public PID(double p, double i, double d) {
        kp = p;
        ki = i;
        kd = d;
    }
    
    public double output(double error) {
        double derivative = error - previousError;
        integral += error;
        previousError = error;
        
        return  error * kp + integral * ki + derivative * kd;
    }
    
}
