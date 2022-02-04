package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Utils.driveTrain;


@TeleOp(name="TeleOp", group="Comp")
public class TeleOP extends LinearOpMode {
    @Override
    public void runOpMode () {
       //----------INITIALIZE Robot---------------------------

        //constants
        double duckPower = 1.0;
        double armPower = 1.0;
        double clampOpen = 1.0;
        double clampClosed = 0.0;

        //-----------Drive train ---------------------------
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




        waitForStart();



        if (isStopRequested()) return; //stop execution if stopping is requested

        while ( opModeIsActive() ) {

            //Get Drive Inputs
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y; // this is reversed! Y stick is reversed by default. We fix.
            double turn = gamepad1.right_stick_x;
            drive.setPower(x, y, turn);

            //Duck Spinner Inputs
            boolean duckSwitch = gamepad1.dpad_left;




            //--------------Logic and power time!!!!!!!!!!!!!!!!-----------------------------------

            //duck spinner
            if (duckSwitch) {
                duckSpinner.setPower(duckPower);
            }

            //Clamp





        }
    }
}

