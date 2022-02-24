package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utils.RobotBase;
import org.firstinspires.ftc.teamcode.Utils.driveTrain;

@Autonomous(name="Forward", group="Auto")
public class forward extends LinearOpMode {

    RobotBase robot = new RobotBase();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        robot.drive.setPower(0,1,0);
        double startTime = runtime.seconds();
        while (opModeIsActive() && runtime.seconds() - startTime < 3);
    }
}
