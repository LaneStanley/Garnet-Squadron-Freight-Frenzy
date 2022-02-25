package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotBase {
    /* Public OpMode members. */
    public DcMotor leftFront    = null;
    public DcMotor rightFront   = null;
    public DcMotor leftBack     = null;
    public DcMotor rightBack    = null;

    public Servo clamp          = null;
    public DcMotor armAngle     = null;
    public DcMotor armExtender  = null;

    public DcMotor duckSpinner  = null;

    public driveTrain drive     = null;
    public Arm arm              = null;

    /* local OpMode members. */
    HardwareMap hwMap           = null;


    /* Constructor */
    public RobotBase() {

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
        //clamp       = hwMap.get(Servo.class, "clmp");
        armAngle        = hwMap.get(DcMotor.class,"armAngle");
        armExtender = hwMap.get(DcMotor.class, "armExtender");

        //Duck Spinner
        //duckSpinner = hwMap.get( DcMotor.class, "ds");

        //Init drivetrain
        drive = new driveTrain(leftFront, rightFront, leftBack, rightBack);
        arm = new Arm(armAngle, armExtender, 0.5);
    }


}
