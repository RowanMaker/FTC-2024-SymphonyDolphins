package org.firstinspires.ftc.teamcode;
//Defining the code as linear, importing motors, servos, etc
//defining the code as TeleOp

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// defining motors
@TeleOp
public class SymphonyDolphinsT extends LinearOpMode {
    private DcMotor backleftMotor; // port 0
    private DcMotor frontleftMotor; // port 1
    private DcMotor backrightMotor; // port 2
    private DcMotor frontrightMotor; // port 3


    //private Servo insertServoNameHere;
    //private CRServo insertLinActNameHere; //servo port location

 // start of OpMode
    @Override
    public void runOpMode () {
        backleftMotor = hardwareMap.dcMotor.get("leftBackDrive");
        frontleftMotor = hardwareMap.dcMotor.get("leftFrontDrive");
        backrightMotor = hardwareMap.dcMotor.get("backRightDrive");
        frontrightMotor = hardwareMap.dcMotor.get("frontRightDrive");

        //insertServoNameHere = hardwareMap.get(Servo.class, "servoName");
        //insertLinActNameHere = hardwareMap.get(CRServo.class, "LinAct");

  // Defining controller methods
        // left and right sticks (x and y axies)
        double LX;
        double LY;
        double RX;
        double RY;

        // T triggers
        double LT;
        double RT;

        // B bumpers
        boolean LB;
        boolean RB;

        // X, Y, A, B buttons
        boolean X;
        boolean Y;
        boolean A;
        boolean B;

        // D pad
        boolean DU;
        boolean DD;
        boolean DL;
        boolean DR;

        // bumpers true/false
        boolean RBhasbeenpressed = false;
        boolean LBhasbeenpressed = false;

        double speed = 1;

 // configure motors- reverse motors to simplify coding
    backleftMotor.setDirection(DcMotor.Direction.REVERSE);
    backrightMotor.setDirection(Servo.Direction.REVERSE);

 // Official start of code
        waitForStart();
        while(opModeIsActive()) {
        // Defining controller buttons and values
            // left and right sticks
            LX = gamepad1.left_stick_x;
            LY = gamepad1.left_stick_y * -1;
            RX = gamepad1.right_stick_x;
            RY = gamepad1.right_stick_y * -1;
            // triggers
            LT = gamepad1.left_trigger;
            RT = gamepad1.right_trigger;
            // bumpers
            LB = gamepad1.left_bumper;
            RB = gamepad1.right_bumper;
            // letter buttons
            X = gamepad1.x;
            Y = gamepad1.y;
            A = gamepad1.a;
            B = gamepad1.b;
            // D pad
            DU = gamepad1.dpad_up;
            DD = gamepad1.dpad_down;
            DL = gamepad1.dpad_left;
            DR = gamepad1.dpad_right;

        }
    }
}
