package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutoMovement {
    private ElapsedTime elapsedTime;
    public BobBase robot; // fix for mod
    public LinearOpMode opMode;

    public AutoMovement(BobBase robo, LinearOpMode op) {

        elapsedTime = new ElapsedTime();
        this.robot = robo;
        this.opMode = op;
    }

    //

    public void pause(double dur) {
        double timeStart = elapsedTime.seconds();

        while (opMode.opModeIsActive() && elapsedTime.seconds() - timeStart < dur) {
            // waiting
            robot.drive.setPower(0, 0, 0);
        }
    }
    public void move(double x, double y, double turn, double dur) {
        double timeStart = elapsedTime.seconds();

        while (opMode.opModeIsActive() && elapsedTime.seconds() - timeStart < dur) {
            robot.drive.setPower(x, y, turn);
        }
        robot.drive.setPower(0, 0, 0);
    }

    public void spin(double power, double dur) {
        double timeStart = elapsedTime.seconds();

        while (opMode.opModeIsActive() && elapsedTime.seconds() - timeStart < dur) {
            robot.duckSpinner.setPower(power);
        }
        robot.duckSpinner.setPower(0);
    }

}
