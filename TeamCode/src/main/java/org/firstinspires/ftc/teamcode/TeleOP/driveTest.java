package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Utils.driveTrain;


@Disabled
@TeleOp(name="driveTest", group="Testing")
public class driveTest extends LinearOpMode {
    
    @Override
    public void runOpMode () {
        //Drive
        DcMotor leftFront = hardwareMap.get( DcMotor.class , "lf" );
        DcMotor rightFront = hardwareMap.get( DcMotor.class , "rf" );
        DcMotor leftBack = hardwareMap.get( DcMotor.class , "lb" );
        DcMotor rightBack = hardwareMap.get( DcMotor.class , "rb" );
        
        // Reverse the right side motors
        leftFront.setDirection( DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        
        //create driveTrain
        driveTrain drive = new driveTrain(leftFront, rightFront, leftBack, rightBack);
        
        waitForStart();
        
        if (isStopRequested()) return; //stop execution if stopping is requested
    
        while ( opModeIsActive() ) {
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y; // this is reversed! Y stick is reversed by default. We fix.
            double turn = gamepad1.right_stick_x;

            drive.setPower(x, y, turn);
        
        }
    }
}
