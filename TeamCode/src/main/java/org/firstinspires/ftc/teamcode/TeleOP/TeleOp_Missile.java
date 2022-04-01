package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Utils.PID;
import org.firstinspires.ftc.teamcode.Utils.RobotBase;

@TeleOp(name="TeleOp_Missile", group="Comp")
public class TeleOp_Missile extends LinearOpMode {

    RobotBase robot = new RobotBase();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        if (isStopRequested()) return; //stop execution if stopping is requested

        boolean scaleAdjusted = false;
        while (opModeIsActive()) {
            //Get Drive Inputs
            double x1 = gamepad1.left_stick_x;
            double y1 = -gamepad1.left_stick_y; // this is reversed! Y stick is reversed by default. We fix.
            double turn = gamepad1.right_stick_x;

            robot.drive.setPower(x1, y1, turn);

            double armAngle = -gamepad2.left_stick_y;
            double armExtend = gamepad2.right_stick_y;

            boolean uDpad = gamepad2.dpad_up; // arm scaling
            boolean dDpad = gamepad2.dpad_down;

            boolean lDpad = gamepad2.dpad_left; // extension scaling
            boolean rDpad = gamepad2.dpad_right;

            boolean xBtn = gamepad2.x; // clamp
            boolean aBtn = gamepad2.a; // release

            if (!(uDpad || dDpad || lDpad || rDpad)) {
                scaleAdjusted = false;
            }

            // arm adjust
            if (uDpad) {
                if (!scaleAdjusted) {
                    robot.arm.adjustArmScale(0.1);
                }
                scaleAdjusted = true;
            } else if (dDpad) {
                if (!scaleAdjusted) {
                    robot.arm.adjustArmScale(-0.1);
                }
                scaleAdjusted = true;
            }

            // extend adjust -- edited
            if (rDpad) {
                if (!scaleAdjusted) {
                    robot.arm.adjustExtendScale(0.1);
                }
                scaleAdjusted = true;
            } else if (lDpad) {
                if (!scaleAdjusted) {
                    robot.arm.adjustExtendScale(-0.1);
                }
                scaleAdjusted = true;
            }
            robot.arm.setArmPower(armAngle, armExtend);

            if (xBtn) {
                robot.arm.setClamp(1.0);
            } else if (aBtn) {
                robot.arm.setClamp(0.0);
            }

            /*
            robot.armAngle.setPower(-gamepad2.left_stick_y);
            robot.armExtender.setPower(gamepad2.right_stick_y);
            */

        }
    }
}

