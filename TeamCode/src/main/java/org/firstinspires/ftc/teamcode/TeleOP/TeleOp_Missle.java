package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Utils.RobotBase;

@TeleOp(name="TeleOp_Missle", group="Comp")
public class TeleOp_Missle extends LinearOpMode {

    RobotBase robot = new RobotBase();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        if (isStopRequested()) return; //stop execution if stopping is requested

        while (opModeIsActive()) {

            //Get Drive Inputs
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y; // this is reversed! Y stick is reversed by default. We fix.
            double turn = gamepad1.right_stick_x;

            robot.drive.setPower(x, y, turn);

            robot.arm.setPower(-gamepad2.left_stick_y);
            robot.extenderArm.setPower(gamepad2.right_stick_y);
        }
    }
}
