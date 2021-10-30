package org.firstinspires.ftc.teamcode.TeleOP;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.Utils.driveTrain;
import java.math.*;

@TeleOp(name="driveTestAngle", group="Testing")
public class AngleThing extends LinearOpMode {
    
    // The IMU sensor object
    BNO055IMU imu;
    
    // State used for updating telemetry
    Orientation angles;
    Acceleration gravity;
    
    @Override
    public void runOpMode () {
        
    
        //set up parameters for IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
    
        //initialize IMU
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    
        //Drive
        DcMotor leftFront = hardwareMap.get( DcMotor.class , "lf" );
        DcMotor rightFront = hardwareMap.get( DcMotor.class , "rf" );
        DcMotor leftBack = hardwareMap.get( DcMotor.class , "lb" );
        DcMotor rightBack = hardwareMap.get( DcMotor.class , "rb" );
    
        // Reverse the right side motors
        rightFront.setDirection( DcMotorSimple.Direction.REVERSE );
        rightBack.setDirection( DcMotorSimple.Direction.REVERSE );
    
        //create driveTrain
        driveTrain drive = new driveTrain( leftFront , rightFront , leftBack , rightBack );
    
        waitForStart();
        
        // Start the logging of measured acceleration
        imu.startAccelerationIntegration(new Position(), new Velocity(), 10);
        
        boolean triggerPressed = false;
        
        // 0.0096
        double pk = 0.001;
        
        if (isStopRequested()) return; //stop execution if stopping is requested
        double target = 0;
        while ( opModeIsActive() ) {
            boolean x = gamepad1.x;
            boolean y = gamepad1.y;// this is reversed! Y stick is reversed by default. We fix.
            boolean a = gamepad1.a;
            boolean b = gamepad1.b;
            
            
            double theta = imu.getAngularOrientation().firstAngle;
            
            if (y) {
                target = 0;
            }
            else if (b) {
                target = 90;
            }
            else if (a) {
                target = 180;
            }
            else if (x) {
                target = -90;
            }
            
            //0 to 180 then -180 to 0
            double distance = target - theta;
            double error = Math.abs(distance);
            
            boolean LTrigger = gamepad1.left_bumper;
            boolean RTrigger = gamepad1.right_bumper;
            
            
            if (LTrigger) {
                if (!triggerPressed) {
                    pk -= 0.0001;
                }
                
                triggerPressed = true;
            }
            else {
                triggerPressed = false;
            }
    
            if (RTrigger) {
                if (!triggerPressed) {
                    pk += 0.0001;
                }
    
                triggerPressed = true;
            }
            else {
                triggerPressed = false;
            }
            
            //double distanceLeft = 360 - distanceRight;
            
            if (Math.abs(distance) > 180) {
                if(distance > 0) {
                    drive.setPower( 0 , 0 , -(error*pk));
                }
                else {
                    drive.setPower( 0 , 0 , (error*pk) );
                }
            }
            else {
                if ( distance > 0 ) {
                    drive.setPower( 0 , 0 , (error*pk) );
                }
                else {
                    drive.setPower( 0 , 0 , -(error*pk) );
                }
            }
            telemetry.addData("theta", theta);
            telemetry.addData("pk", pk);
            telemetry.addData( "target", target );
            telemetry.update();
            
            
            
            //drive.setPower( 0 , 0 , 0);
        }
    }
}
