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

    // Declare OpMode members.

    // Motors
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;

    private Servo clamp = null;
    private CRServo arm = null;

    private DcMotor duckSpinner = null;

    @Override
    public void runOpMode () {
       //----------INITIALIZE Robot---------------------------

        //constants
        double duckPower = 0.0; // 0.5
        double armPos = 0.0; // 0.45
        double clampOpen = 0.0; // 0.6;
        double clampClosed = 0.0;
        double sensitivity = 0.001;
        double driveSensitivity = 1;

        //Drive train:
        leftFront = hardwareMap.get( DcMotor.class , "lf" );
        rightFront = hardwareMap.get( DcMotor.class , "rf" );
        leftBack = hardwareMap.get( DcMotor.class , "lb" );
        rightBack = hardwareMap.get( DcMotor.class , "rb" );

        // Reverse the right side motors
        leftFront.setDirection( DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        //create driveTrain
        driveTrain drive = new driveTrain(leftFront, rightFront, leftBack, rightBack);


        // Arm/Intake
        clamp = hardwareMap.get(Servo.class, "clmp");

        arm = hardwareMap.get(CRServo.class,"arm");


        //Duck Spinner
        duckSpinner = hardwareMap.get( DcMotor.class, "ds");

        waitForStart();
        if (isStopRequested()) return; //stop execution if stopping is requested

        while ( opModeIsActive() ) {

            //Get Drive Inputs
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y; // this is reversed! Y stick is reversed by default. We fix.
            double turn = gamepad1.right_stick_x;



            //Duck Spinner Inputs
            boolean duckSwitchForward = gamepad1.a;
            boolean duckSwitchBackward = gamepad1.y;




            //--------------Logic and power time!!!!!!!!!!!!!!!!-----------------------------------

            //drive power
            drive.setPower(x * driveSensitivity, y * driveSensitivity, turn * driveSensitivity);

            if (gamepad1.left_trigger > 0) {
                driveSensitivity = 1;
            }
            else {
                driveSensitivity = 0.5;
            }


            //duck spinner
            if (duckSwitchForward) {
                duckSpinner.setPower(duckPower);
                telemetry.addData("yuh", 0);
            }
            else if (duckSwitchBackward) {
                duckSpinner.setPower(-duckPower);
            }
            else {
                duckSpinner.setPower(0);
            }

            //Clamp
            if (gamepad2.right_trigger > 0.3) {
                clamp.setPosition(clampClosed);
            }

            if (gamepad2.left_trigger > 0.3) {
                clamp.setPosition(clampOpen);
            }

            armPos += gamepad2.left_stick_y * sensitivity;
             double amt = 0;
            //arm
            /*
            if (( amt + armPos) > 1) {
                armPosss = 1;
            }
            else if ((amt + armPos) < 0) {
                armPos = 0;
            }
            else {
                armPos += amt;
            }
            */

            arm.setPower(armPos);



            //arm.setPower(armPow);

            telemetry.addData("Pos", arm.getPower());
            telemetry.update();
        }
    }
}

