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
    public double powerScaleArm = 1.0;
    public double powerScaleClamp = 1.0;


    public Arm(DcMotor armAngleMotor, DcMotor armExtendingMotor, double ps) {
        angler = armAngleMotor;
        extender = armExtendingMotor;

        powerScaleArm = ps;
    }

    // +
    public void adjustArmScale(double x) {
        powerScaleArm = Math.max(0.0, Math.min(powerScaleArm + x, 1.0));
    }

    private void calculateArmPower(double aA, double aE) {
        anglerPower = aA*powerScaleArm;
        extendPower = aE*powerScaleArm;
    }

    public void setArmPower(double aA, double aE) {
        calculateArmPower(aA, aE);

        angler.setPower(anglerPower);
        extender.setPower(extendPower);
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
