package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Utils.driveTrain;

@Disabled
@TeleOp(name="AndrewLearn", group="Testing")
public class AndrewLearn extends LinearOpMode
{

    @Override
    public void runOpMode ()
    {
        DcMotor leftFront = hardwareMap.get(DcMotor.class, "lf");
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "rf");
        DcMotor leftBack = hardwareMap.get(DcMotor.class, "lb");
        DcMotor rightBack = hardwareMap.get(DcMotor.class, "rb");

        while (true)
        {
            double x = gamepad1.right_stick_x;
            double y = gamepad1.left_stick_y;

            leftFront.setPower(y + x);
            leftBack.setPower(y + x);
            rightFront.setPower(y - x);
            rightBack.setPower(y - x);

        }
    }
}
