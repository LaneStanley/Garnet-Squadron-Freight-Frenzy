package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utils.TimBase;


@Autonomous(name = "AutoRedDuck", group = "Auto")
public class AutoRedDuck extends LinearOpMode {

    TimBase robot = new TimBase();

    @Override
    public void runOpMode() {
        //----------INITIALIZE Robot---------------------------

        robot.init(hardwareMap);

        //constants
        double duckPower = 1.0;
        double armPower = 1.0;
        double clampOpen = 1.0;
        double clampClosed = 0.0;
        double timeStart = 0.0;




        //time thing a thing
        ElapsedTime getTime = new ElapsedTime();

        waitForStart();
        if (isStopRequested()) return; //stop execution if stopping is requested


        //yo drive forward you bum
        timeStart = getTime.seconds();
        while(opModeIsActive() && getTime.seconds() - timeStart < 3) {
            robot.drive.setPower(0, 1.0, 0);
        }
        robot.drive.setPower(0, 0, 0);
        //yo spin that thing a thing with the duck thing for the duck you bum
        timeStart = getTime.seconds();
        while(opModeIsActive() && getTime.seconds() - timeStart < 3) {
            robot.duckSpinner.setPower(duckPower);
        }
        robot.duckSpinner.setPower(0);
        //yo crab that bitch over!!!!!!!!!!!
        timeStart = getTime.seconds();
        while(opModeIsActive() && getTime.seconds() - timeStart < 3) {
            robot.drive.setPower(1.0,0,0);
        }
        robot.drive.setPower(0,0,0);


    }


}
