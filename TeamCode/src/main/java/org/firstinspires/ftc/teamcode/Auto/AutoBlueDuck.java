package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utils.driveTrain;



@Autonomous(name = "AutoBlueDuck", group = "Auto")
public class AutoBlueDuck extends LinearOpMode {


    @Override
    public void runOpMode() {
        //----------INITIALIZE Robot---------------------------

        //constants
        double duckPower = 1.0;
        double armPower = 1.0;
        double clampOpen = 1.0;
        double clampClosed = 0.0;
        double timeStart = 0.0;

        //Drive train:
        DcMotor leftFront = hardwareMap.get( DcMotor.class , "lf" );
        DcMotor rightFront = hardwareMap.get( DcMotor.class , "rf" );
        DcMotor leftBack = hardwareMap.get( DcMotor.class , "lb" );
        DcMotor rightBack = hardwareMap.get( DcMotor.class , "rb" );

        // Reverse the right side motors
        leftFront.setDirection( DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        //create driveTrain
        driveTrain drive = new driveTrain(leftFront, rightFront, leftBack, rightBack);

        // Arm/Intake
        Servo clamp = hardwareMap.get(Servo.class, "clmp");
        CRServo arm = hardwareMap.get(CRServo.class, "arm");

        //Duck Spinner
        DcMotor duckSpinner = hardwareMap.get( DcMotor.class, "ds");

        //time thing a thing
        ElapsedTime getTime = new ElapsedTime();

        waitForStart();
        if (isStopRequested()) return; //stop execution if stopping is requested


        //yo drive forward you bum
        timeStart = getTime.seconds();
        while(opModeIsActive() && getTime.seconds() - timeStart > 3) {
            drive.setPower(0, 1.0, 0);
        }
        drive.setPower(0, 0, 0);
        //yo spin that thing a thing with the duck thing for the duck you bum
        timeStart = getTime.seconds();
        while(opModeIsActive() && getTime.seconds() - timeStart > 3) {
            duckSpinner.setPower(duckPower);
        }
        duckSpinner.setPower(0);
        //yo crab that bitch over!!!!!!!!!!!
        timeStart = getTime.seconds();
        while(opModeIsActive() && getTime.seconds() - timeStart > 3) {
            drive.setPower(-1.0,0,0);
        }
        drive.setPower(0,0,0);


    }


}

