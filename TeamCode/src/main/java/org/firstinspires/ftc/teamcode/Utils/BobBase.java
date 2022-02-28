package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BobBase {
    /* Public OpMode members. */
    public driveTrain drive     = null;

    public DcMotor leftFront = null;
    public DcMotor rightFront = null;
    public DcMotor leftBack = null;
    public DcMotor rightBack = null;

    public Servo clamp = null;
    public CRServo arm = null;

    public DcMotor duckSpinner = null;

    /* local OpMode members. */
    HardwareMap hwMap           = null;


    /* Constructor */
    public BobBase() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        //Drive train:
        leftFront  = hwMap.get( DcMotor.class , "lf" );
        rightFront = hwMap.get( DcMotor.class , "rf" );
        leftBack   = hwMap.get( DcMotor.class , "lb" );
        rightBack  = hwMap.get( DcMotor.class , "rb" );


        // Reverse the right side motors
        leftFront.setDirection( DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        // Arm/Intake
        clamp = hwMap.get(Servo.class, "clmp");

        arm = hwMap.get(CRServo.class,"arm");

        //Duck Spinner
        duckSpinner = hwMap.get( DcMotor.class, "ds");

        //Init drivetrain
        drive = new driveTrain(leftFront, rightFront, leftBack, rightBack);

    }


}
