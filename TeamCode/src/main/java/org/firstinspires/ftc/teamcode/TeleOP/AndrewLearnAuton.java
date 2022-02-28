package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Utils.driveTrain;

@Disabled
@TeleOp(name="AndrewLearnAuton", group="Testing")
public class AndrewLearnAuton extends LinearOpMode
{

    @Override
    public void runOpMode ()
    {
        waitForStart();
        DcMotor leftFront = hardwareMap.get(DcMotor.class, "lf");
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "rf");
        DcMotor leftBack = hardwareMap.get(DcMotor.class, "lb");
        DcMotor rightBack = hardwareMap.get(DcMotor.class, "rb");
        long circleTime = 3000000;
        double startTime = System.nanoTime();

        while ( System.nanoTime() - startTime < 500000 )
        {
            leftFront.setPower(1);
            leftBack.setPower(1);
            rightFront.setPower(1);
            rightBack.setPower(1);

        }
        startTime = System.nanoTime();

        while ( System.nanoTime() - startTime < 500000 )
        {
            leftFront.setPower(-1);
            leftBack.setPower(-1);
            rightFront.setPower(-1);
            rightBack.setPower(-1);

        }
        startTime = System.nanoTime();

        while ( System.nanoTime() - startTime < circleTime )
        {
            leftFront.setPower(1);
            leftBack.setPower(1);
            rightFront.setPower(0.5);
            rightBack.setPower(0.5);

        }
        startTime = System.nanoTime();

        while ( System.nanoTime() - startTime < circleTime )
        {
            leftFront.setPower(0.5);
            leftBack.setPower(0.5);
            rightFront.setPower(1);
            rightBack.setPower(1);

        }

    }
}
