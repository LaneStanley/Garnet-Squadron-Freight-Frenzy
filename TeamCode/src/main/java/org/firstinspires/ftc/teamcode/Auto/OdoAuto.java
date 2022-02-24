package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Utils.MotorHandler;

@Autonomous(name = "OdoAuto", group = "Testing")
public class OdoAuto extends LinearOpMode {
    DcMotor lMotor;
    DcMotor rMotor;
    DcMotor mMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        lMotor = hardwareMap.dcMotor.get("motoL");
        rMotor = hardwareMap.dcMotor.get("motoR");
        mMotor =  hardwareMap.dcMotor.get("mMotor");

        // initialize motors
        MotorHandler motors = new MotorHandler(lMotor, rMotor, mMotor,
                new DcMotorSimple.Direction[]{DcMotor.Direction.FORWARD, DcMotor.Direction.FORWARD, DcMotor.Direction.FORWARD});

        motors.SetUp(1000, 1000);
        DcMotor motoL = motors.getMotors()[0];
        DcMotor motoR = motors.getMotors()[1];

        // tele
        telemetry.addData("Mode", "waiting");
        telemetry.update();

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        //
        motors.setPower(0.25);

        //
        while (opModeIsActive() && motoL.isBusy())   //leftMotor.getCurrentPosition() < leftMotor.getTargetPosition())
        {
            telemetry.addData("encoder-fwd-left", motoL.getCurrentPosition() + "  busy=" + motoL.isBusy());
            telemetry.addData("encoder-fwd-right", motoR.getCurrentPosition() + "  busy=" + motoR.isBusy());
            telemetry.update();
            idle();
        }

        //
        motors.setPower(0.0);


    }

}
