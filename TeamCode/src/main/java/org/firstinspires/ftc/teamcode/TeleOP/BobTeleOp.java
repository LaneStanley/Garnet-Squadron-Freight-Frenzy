package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Utils.BobBase;

@TeleOp(name="BobTeleOp", group="Comp")
public class BobTeleOp extends LinearOpMode {

    BobBase robot = new BobBase();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        if (isStopRequested()) return; //stop execution if stopping is requested

        boolean scaleAdjusted = false;

        //constants
        double duckPower = 0.5;
        double armPos = -0.627; // 0.45
        double clampOpen = 1.0;
        double clampClosed = .2;
        double sensitivity = 0.0015;

        double defaultDriveSensitivity = 0.5;
        double defaultTurnSensitivity = 0.75;

        double driveSensitivity;
        double turnSensitivity;

        double sprintSensitivity = 1;

        while (opModeIsActive()) {
            //Get Drive Inputs
            double x1 = gamepad1.left_stick_x;
            double y1 = -gamepad1.left_stick_y; // this is reversed! Y stick is reversed by default. We fix.
            double turn = gamepad1.right_stick_x;

            // sprint
            if (gamepad1.right_bumper) {
                driveSensitivity = sprintSensitivity;
                turnSensitivity = sprintSensitivity;
            } else {
                driveSensitivity = defaultDriveSensitivity;
                turnSensitivity = defaultTurnSensitivity;
            }

            robot.drive.setPower(x1 * driveSensitivity, y1 * driveSensitivity, turn * turnSensitivity);

            boolean duckspinnerForward = gamepad1.a;
            boolean duckspinnerBackward = gamepad1.y;

            // -- robot control logic

            // duck spinner
            if (duckspinnerForward) {
                robot.duckSpinner.setPower(duckPower);
            }
            else if (duckspinnerBackward) {
                robot.duckSpinner.setPower(-duckPower);
            } else {
                robot.duckSpinner.setPower(0);
            }

            // servo for duck grabber
            if (gamepad2.right_trigger > 0.3) {
                robot.clamp.setPosition(clampClosed);
            }
            else if (gamepad2.left_trigger > 0.3) {
                robot.clamp.setPosition(clampOpen);
            }

            // set arm position
            armPos += -gamepad2.left_stick_y * sensitivity;
            armPos = Math.max(-0.77, armPos);

            robot.arm.setPower(armPos);
        }
    }
}

