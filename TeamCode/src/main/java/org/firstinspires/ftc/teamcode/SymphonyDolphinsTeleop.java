package org.firstinspires.ftc.teamcode;
//Defining the code as linear, importing motors, servos, etc
//defining the code as TeleOp

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class SymphonyDolphinsTeleOp extends LinearOpMode {
    // defining motors
    private DcMotor backleftMotor; // port 0
    private DcMotor frontleftMotor; // port 1
    private DcMotor backrightMotor; // port 2
    private DcMotor frontrightMotor; // port 3


    //private Servo insertServoNameHere; // servo port location
    //private CRServo insertLinActNameHere; //servo port location

 // start of OpMode
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
        
    // reverse directions of motors to simplify coding
    frontleftMotor.setDirection(DcMotor.Direction.REVERSE);
    backleftMotor.setDirection(DcMotor.Direction.REVERSE);
    //backservo.setDirection(Servo.Direction.REVERSE);

 // Official start of code
        waitForStart();
        while(opModeIsActive()) {
        // Defining controller buttons and values
            // left and right sticks
            LX = gamepad1.left_stick_x;
            LY = gamepad1.left_stick_y * -1;
            RX = gamepad1.right_stick_x;
            RY = gamepad1.right_stick_y * -1;
            // bumpers
            LB = gamepad1.left_bumper;
            RB = gamepad1.right_bumper;
            // triggers
            LT = gamepad1.left_trigger;
            RT = gamepad1.right_trigger;
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

        // speed changes
            // right bumper - increase speed by 0.2
            if(RB == true && RBhasbeenpressed == false){
                RBhasbeenpressed = true;
                speed = speed + 0.2;
            }
            if(RB == false){
                RBhasbeenpressed = false;

            }
            // left bumper - decrease speed by 0.2
            if(LB == true && LBhasbeenpressed == false){
                LBhasbeenpressed = true;
                speed = speed - 0.2;
            }
            if(LB == false){
                LBhasbeenpressed = false;
            }

            //Defining the min and max of the variable speed
            speed = Math.min(speed, 1);
            speed = Math.max(speed, 0);

            //rounds to legible numbers
            speed = (int)(speed * 10) / 10.0;

            //Modular speed control for the movement joysticks on the controller
            frontleftMotor.setPower((LY + RX + LX) * speed);
            frontrightMotor.setPower((LY - RX - LX) * speed);
            backleftMotor.setPower((LY + RX - LX) * speed);
            backrightMotor.setPower((LY - RX + LX) * speed);

            // displays speed data on the driver hub
            telemetry.addData("Speed: ", speed);
            telemetry.update();

            
        }
    }

}
