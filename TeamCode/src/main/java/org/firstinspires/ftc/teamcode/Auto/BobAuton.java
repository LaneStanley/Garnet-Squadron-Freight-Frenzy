package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utils.AutoMovement;
import org.firstinspires.ftc.teamcode.Utils.BobBase;


@Autonomous(name = "BobAuton", group = "Auto")
public class BobAuton extends LinearOpMode {

    BobBase robot = new BobBase();
    AutoMovement auto = new AutoMovement(robot, this);

    //ElapsedTime elapsedTime;
    @Override
    public void runOpMode() {
        // init
        robot.init(hardwareMap);

        // consts
        double duckPower = 0.5;
        double armPos = -0.627; // 0.45
        double clampOpen = .7;
        double clampClosed = 0.9;
        double sensitivity = 0.001;
        double driveSensitivity = 1;


        waitForStart();
        if (isStopRequested()) return; // stop exec if requested

        auto.move(0, 0.5, 0.1, 1.6);
        //spin(duckPower, 2);
        auto.pause(20);
        auto.move(0, -0.5, -0.1, 0.8);
    }

    /*
    public void pause(double dur) {
        double timeStart = elapsedTime.seconds();

        while (opModeIsActive() && elapsedTime.seconds() - timeStart < dur) {
            // waiting
            robot.drive.setPower(0, 0, 0);
        }
    }
    public void move(double x, double y, double turn, double dur) {
        double timeStart = elapsedTime.seconds();

        while (opModeIsActive() && elapsedTime.seconds() - timeStart < dur) {
            robot.drive.setPower(x, y, turn);
        }
        robot.drive.setPower(0, 0, 0);
    }

    public void spin(double power, double dur) {
        double timeStart = elapsedTime.seconds();

        while (opModeIsActive() && elapsedTime.seconds() - timeStart < dur) {
            robot.duckSpinner.setPower(power);
        }
        robot.duckSpinner.setPower(0);
    }
     */
}
