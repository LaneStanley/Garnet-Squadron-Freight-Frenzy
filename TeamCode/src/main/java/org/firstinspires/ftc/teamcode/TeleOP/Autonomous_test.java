package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.Utils.PID;
import org.firstinspires.ftc.teamcode.Utils.driveTrain;

@Disabled
@TeleOp(name="Autonomous_test", group="Testing")
public class Autonomous_test extends LinearOpMode{

    BNO055IMU imu1;
    BNO055IMU imu2;


    static int[] move(int x, int y, int theta) {

        return new int[]{};
    }

    @Override
    public void runOpMode () {
        //Drive
        DcMotor leftFront = hardwareMap.get( DcMotor.class , "lf" );
        DcMotor rightFront = hardwareMap.get( DcMotor.class , "rf" );
        DcMotor leftBack = hardwareMap.get( DcMotor.class , "lb" );
        DcMotor rightBack = hardwareMap.get( DcMotor.class , "rb" );

        // Reverse the right side motors
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        //time
        ElapsedTime runtime = new ElapsedTime();

        //config
        double kp = 0.0096;
        double ki = 0.0;
        double kd = 0.0;


        //create driveTrain
        driveTrain drive = new driveTrain(leftFront, rightFront, leftBack, rightBack);

        //initialize imus
        //--set up parameters for IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        //--initialize IMU
        imu1 = hardwareMap.get(BNO055IMU.class, "imu1");
        imu2 = hardwareMap.get(BNO055IMU.class, "imu2");

        imu1.initialize(parameters);
        imu2.initialize(parameters);

        // Start the logging of measured acceleration
        imu1.startAccelerationIntegration(new Position(), new Velocity(), 10);
        imu2.startAccelerationIntegration(new Position(), new Velocity(), 10);


        waitForStart();

        if (isStopRequested()) return; //stop execution if stopping is requested

        double target = 0;

        while ( opModeIsActive() ) {
            //controls (temp)
            boolean x = gamepad1.x;
            boolean y = gamepad1.y;// this is reversed! Y stick is reversed by default. We fix.
            boolean a = gamepad1.a;
            boolean b = gamepad1.b;

            boolean RB = gamepad1.right_bumper;
            boolean LB = gamepad1.left_bumper;

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

            double theta1 = imu1.getAngularOrientation().firstAngle;
            double theta2 = imu2.getAngularOrientation().firstAngle;

            double thetaAvg = (theta1 + theta2)/2;

            //error
            double distance = target - thetaAvg;
            double error = distance < 0 ? (distance + 360) : distance;

            double xDir = 0;
            double yDir = -0; // this is reversed! Y stick is reversed by default. We fix.

            if (RB) { //reversed
                yDir = (Math.min(-1, yDir - 1));
            } else if (LB) {
                yDir = (Math.max(1, yDir + 1));
            }

            double turn = thetaAvg;

            PID pid = new PID(kp, ki, kd);

            drive.setPower(xDir, yDir, turn * pid.output(error));
        }
    }
}
