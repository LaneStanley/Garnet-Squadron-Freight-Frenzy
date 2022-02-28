package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Utils.driveTrain;
import java.util.Locale;

@Disabled
@Autonomous(name = "IMU Test", group = "Testing")
//@Disabled                            // Comment this out to add to the opmode list
public class IMUTest extends LinearOpMode{
    
    // The IMU sensor object
    BNO055IMU imu;
    
    // State used for updating telemetry
    Orientation angles;
    Acceleration gravity;
    
    @Override public void runOpMode() {
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
        rightFront.setDirection( DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
    
        //create driveTrain
        driveTrain drive = new driveTrain(leftFront, rightFront, leftBack, rightBack);
    
        // Wait until we're told to go
        waitForStart();
    
        // Start the logging of measured acceleration
        imu.startAccelerationIntegration(new Position(), new Velocity(), 10);
    
        while ( opModeIsActive() ) {
            double crab, forward, turn;
            // forward
            double distancex = imu.getPosition().z; // change axis depending on tests
            if (distancex < 5) {
                forward = 1;
                // crab
                double distancey = imu.getPosition().x; // change axis depending on tests
                crab = distancey; //change to - depending on tests might need weight for power
    
                // turn
                double angle = imu.getAngularOrientation().firstAngle; // change angle depending on tests
                turn = 0; //angle; //change to - depending on tests might need weight for power
            }
            else {
                forward = 0;
                crab = 0;
                turn = 0;
            }
            if (distancex > 1) {
                double angle = imu.getAngularOrientation().firstAngle; // change angle depending on tests
                if (angle < 90) {
                    turn = 0; //1;
                }
                else{
                    turn = 0;
                    forward = 0;
                    crab = 0;
                    drive.setPower(crab, forward, turn);
                    break;
                }
                
            }
            telemetry.addData( "z", imu.getPosition());
            telemetry.addData("y", imu.getPosition());
            telemetry.addData("angle", imu.getAngularOrientation());
            telemetry.update();
            crab = 0;
            forward = 0;
            turn = 0;
            drive.setPower(crab, forward, turn);
        
        }
    
    }
    
}
