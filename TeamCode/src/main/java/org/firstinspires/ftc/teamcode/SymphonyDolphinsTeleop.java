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
    // defining motors (servo ports)
    private CRServo backleftMotor; // port 0
    private CRServo frontleftMotor; // port 1
    private CRServo backrightMotor; // port 2
    private CRServo frontrightMotor; // port 3
    // defining manipulator motors (motor ports)
    private DcMotor linActMotor; // port 1
    private DcMotor ShoulderMotor; // port 0
    // defining servos
    private Servo wristServo; // port 4
    private Servo clawServo; // port 5

 // start of OpMode
    public void runOpMode () {
        backleftMotor = hardwareMap.get(CRServo.class, "leftBackDrive");
        frontleftMotor = hardwareMap.get(CRServo.class, "leftFrontDrive");
        backrightMotor = hardwareMap.get(CRServo.class, "rightBackDrive");
        frontrightMotor = hardwareMap.get(CRServo.class, "rightFrontDrive");
        
        linActMotor = hardwareMap.get(DcMotor.class, "linAct");
        ShoulderMotor = hardwareMap.get(DcMotor.class, "shoulder");
        
        wristServo = hardwareMap.get(Servo.class, "wrist");
        clawServo = hardwareMap.get(Servo.class, "claw");

  // Defining controller methods
        // left and right sticks (x and y axies)
        double LX;
        double LY;
        double RX;
        double RY;

        // T triggers
        float LT;
        float RT;

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
    frontleftMotor.setDirection(CRServo.Direction.REVERSE);
    backleftMotor.setDirection(CRServo.Direction.REVERSE);

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

            // Defining Y,A - shoulder up/down
            if (A == true){
                // shoulder up
                ShoulderMotor.setPower(0.8);
            }
            if(Y == true){
                // shoulder down
                ShoulderMotor.setPower(-0.8);     
            }
            else {
                // if button is not held down shoulder stops moving
                ShoulderMotor.setPower(0);
            }
            // Defining LT and RT - lin act up/down
            if (RT > 0){
                // lin act up
                linActMotor.setPower(0.8);
            }
            if (LT > 0){
                // lin act down
                linActMotor.setPower(-0.8);
            }
            else
            {
                // if button is not held down lin act stops moving
                linActMotor.setPower(0);
            }
            
            // Defining DU/DD on the D pad- wrist up/down
            // set position
            if(DU == true){ 
                // wrist up
                wristServo.setPosition(0.3);
                sleep(500);
            }
            else if(DD == true){ 
                // wrist down
                wristServo.setDirection(Servo.Direction.REVERSE);
                wristServo.setPosition(0.0);
                sleep(200);
            }
               
            if(DU == true){ 
                // wrist up
                wristServo.setDirection(Servo.Direction.REVERSE);
                wristServo.setPosition(0.3);
                sleep(200);
            }
            else if(DD == true){ 
                // wrist down
                wristServo.setPosition(0.0);
                sleep(200);
            }
             
            // Defining X,B - open/close claw
            // set position
            if(X == true){ 
                // claw open
                clawServo.setPosition(0.3);
                sleep(500);
            }
            else if(B == true){ 
                // claw close
                clawServo.setDirection(Servo.Direction.REVERSE);
                clawServo.setPosition(0.0);
                sleep(200);
            }
               
            if(B == true){ 
                // claw close
                clawServo.setDirection(Servo.Direction.REVERSE);
                clawServo.setPosition(0.3);
                sleep(200);
            }
            else if(X == true){ 
                // claw open
                clawServo.setPosition(0.0);
                sleep(200);
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
