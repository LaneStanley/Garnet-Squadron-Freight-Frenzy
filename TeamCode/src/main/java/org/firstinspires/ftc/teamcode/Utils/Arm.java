package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    // F
    public DcMotor angler;
    public DcMotor extender;

    public Servo rClamp;
    public Servo lClamp;

    //
    public double anglerPower = 0.0;
    public double extendPower = 0.0;

    //public double clampPower = 0.0;

    //
    public double powerScaleAngle = 1.0; // deprecated
    public double powerScaleExtend = 1.0; // deprecated
    public double powerScaleClamp = 1.0;

    PID armAnglePID = new PID(.96, 0, 0);
    PID armExtendPID = new PID(.96, 0, 0);


    public Arm(DcMotor armAngleMotor, DcMotor armExtendingMotor, double ps) {
        angler = armAngleMotor;
        extender = armExtendingMotor;

        powerScaleAngle = ps;
    }

    // +
    public void adjustArmScale(double x) {
        powerScaleAngle = Math.max(0.0, Math.min(powerScaleAngle + x, 1.0));
    }

    public void adjustExtendScale(double x) {
        powerScaleExtend = Math.max(0.0, Math.min(powerScaleExtend + x, 1.0));
    }

    private void calculateArmPower(double aA, double aE) {
        anglerPower = aA*powerScaleAngle; //aA*1; //
        extendPower = aE*powerScaleExtend; //aE*1; //
    }

    public void setArmPower(double aA, double aE) {
        calculateArmPower(aA, aE);

        angler.setPower(anglerPower);
        extender.setPower(extendPower);
    }

    public void adjustAnglePID(double x) {
        armAnglePID.kp = Math.max(0.0, Math.min(armAnglePID.kp + x, 1.0));
    }

    public void adjustExtendPID(double x) {
        armExtendPID.kp = Math.max(0.0, Math.min(armExtendPID.kp + x, 1.0));
    }

    //
    /*
    public void adjustClampScale(double x) {
        powerScaleArm = Math.max(0.0, Math.min(powerScaleArm + x, 1.0));
    }

    private void calculateClampPower(double aA, double aE) {
        anglerPower = aA*powerScaleClamp;
        extendPower = aE*powerScaleClamp;
    }
     */

    public void setClamp(double x) {
        rClamp.setPosition(x);
        lClamp.setPosition(x);
    }
}
