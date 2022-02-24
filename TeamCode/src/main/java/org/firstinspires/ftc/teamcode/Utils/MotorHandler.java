package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class MotorHandler {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private DcMotor midMotor;

    public MotorHandler(DcMotor lMotor, DcMotor rMotor, DcMotor mMotor, DcMotor.Direction[] directions) {
        leftMotor = lMotor;
        rightMotor = rMotor;
        midMotor = mMotor;

        leftMotor.setDirection(directions[0]);
        rightMotor.setDirection(directions[1]);
        midMotor.setDirection(directions[2]);
    }

    public void SetUp(int leftMotorCounts, int rightMotorCounts) {
        // reset encoder counts
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        midMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motor targets
        leftMotor.setTargetPosition(leftMotorCounts);
        rightMotor.setTargetPosition(rightMotorCounts);

        // set to move
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    // <
    public DcMotor[] getMotors() {
        return new DcMotor[]{leftMotor, rightMotor, midMotor};
    }


    // >
    public void setPower(double pL, double pR) {
        leftMotor.setPower(pL);
        rightMotor.setPower(pR);
    }

    public void setPower(double p) {
        this.setPower(p, p);
    }

}
