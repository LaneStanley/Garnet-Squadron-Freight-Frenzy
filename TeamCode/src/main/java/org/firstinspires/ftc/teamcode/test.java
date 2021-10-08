package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp( name = "TeleOp", group = "Linear Opmode" )
public class test extends LinearOpMode {
    
    // Declare OpMode members.
    //private ElapsedTime runtime = new ElapsedTime();
    
    // Motors
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftRear = null;
    private DcMotor rightRear = null;
    
    // Servos
    private Servo intake;
    private CRServo grabber;
    private Servo FoundationMover;
    private DcMotor string;
    private DcMotor string2;
    
    @Override
    public void runOpMode () {
        //mapping
        //Drive
        leftFront = hardwareMap.get( DcMotor.class , "lf" );
        rightFront = hardwareMap.get( DcMotor.class , "rf" );
        leftRear = hardwareMap.get( DcMotor.class , "lr" );
        rightRear = hardwareMap.get( DcMotor.class , "rr" );
        
        //intake
        intake = hardwareMap.servo.get( "intake" );
        grabber = hardwareMap.crservo.get( "grabber" );
        FoundationMover = hardwareMap.servo.get( "Foundation mover" );
        string = hardwareMap.dcMotor.get( "string" );
        string2 = hardwareMap.dcMotor.get( "string2" );
        
        double posUp = 0.7;
        double posDown = 1.5;
        // crab stick
        double sensitivity = 0.5;
        
        
        waitForStart();
        //set the intake to up
        intake.setPosition( posUp );
        
        //Powers
        double liftUp = -1;
        double liftDown = 1;
        double extendOut = 0.6;
        double extendIn = -0.6;
        
        //runtime.reset();
        while ( opModeIsActive() ) {
            
            //Lift
            if ( gamepad2.right_stick_y > 0 ) {
                string.setPower( liftUp );
            }
            else if ( gamepad2.right_stick_y < 0 ) {
                string.setPower( liftDown );
            }
            else {
                string.setPower( 0 );
            }
            
            if ( gamepad2.right_stick_x > 0 ) {
                string2.setPower( extendOut );
            }
            else if ( gamepad2.right_stick_x < 0 ) {
                string2.setPower( extendIn );
            }
            else {
                string2.setPower( 0 );
            }
            
            //Drive System
            
            // make variables and get stick input values for
            // calculations
            double xSpeed = gamepad1.left_stick_x;
            double ySpeed = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            
            // make variables for stick calculations to to stored
            double v1, v2, v3, v4;
            
            // only accept x input == crab better
            if ( gamepad1.right_trigger > sensitivity ) {
                //Left Front
                v1 = xSpeed + turn;
                //Right Front
                v2 = xSpeed + turn;
                //Left Rear
                v3 = -xSpeed + turn;
                //Right Rear
                v4 = -xSpeed + turn;
            }
            // default powers == not so good crab == accepts y input
            else {
                //Left Front
                v1 = xSpeed - ySpeed + turn;
                //Right Front
                v2 = xSpeed + ySpeed + turn;
                //Left Rear
                v3 = -xSpeed - ySpeed + turn;
                //Right Rear
                v4 = -xSpeed + ySpeed + turn;
            }
            
            //Servos to move foundation
            
            if ( gamepad1.b ) {
                FoundationMover.setPosition( 1.5 );
            }
            
            if ( gamepad1.a ) {
                FoundationMover.setPosition( 0.5 );
            }
            
            //grabber.setPower(gamepad2.left_stick_x);
            
            
            //grabber (CR servo)
            //  if (gamepad2.left_bumper){
            //   grabber.setPower( -0.4 );
            
            //}
            
            if ( gamepad2.left_trigger > 0.5 ) {
                grabber.setPower( -1 );
            } else if ( gamepad2.left_bumper ) {
                grabber.setPower( 1 );
            } else {
                grabber.setPower( 0 );
            }
            
            
            
            //block elevator
            // if (gamepad2.left_bumper) {
            //  string.setPower(0.6);
            // }
            
            
            
            //TODO: NeveRest 40 Gearmotor clicks per revolution: 1120
            //TODO: NeveRest 3.7 v1 Gearmotor clicks per revolution: 28
            
            //Speed multiplier
            final double driveFast = 1.2;
            final double driveRegular = 0.6;
            final double driveSlow = 0.3;
            
            
            string.setTargetPosition(56);
            
            
            //Block stacker (theoretically) moves up with each button click
/*
            if (gamepad1.a){
                string.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } else{
            string.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
*/
            
            //Slow
            if ( gamepad1.left_bumper ) {
                leftFront.setPower( driveSlow * v1 );
                rightFront.setPower( driveSlow * v2 );
                leftRear.setPower( driveSlow * v3 );
                rightRear.setPower( driveSlow * v4 );
                //Fast
            }
            else if ( gamepad1.right_bumper ) {
                leftFront.setPower( driveFast * v1 );
                rightFront.setPower( driveFast * v2 );
                leftRear.setPower( driveFast * v3 );
                rightRear.setPower( driveFast * v4 );
                //Regular
            }
            else {
                leftFront.setPower( driveRegular * v1 );
                rightFront.setPower( driveRegular * v2 );
                leftRear.setPower( driveRegular * v3 );
                rightRear.setPower( driveRegular * v4 );
            }
            
            //intake
            if ( gamepad2.dpad_up) {
                //up
                intake.setPosition( 1.8);
            }
            else {
                //down
                intake.setPosition( 0.3 );
            }
        }
    }
}
