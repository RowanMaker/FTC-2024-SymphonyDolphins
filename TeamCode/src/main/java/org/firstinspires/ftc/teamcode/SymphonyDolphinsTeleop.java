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
    // defining manipulator motors
    private DcMotor linActMotor; // port ??
    private DcMotor ShoulderMotor; // port ??
    // defining servos
    private Servo wristServo; // port ??
    private Servo clawServo; // port ??

 // start of OpMode
    public void runOpMode () {
        backleftMotor = hardwareMap.dcMotor.get("leftBackDrive");
        frontleftMotor = hardwareMap.dcMotor.get("leftFrontDrive");
        backrightMotor = hardwareMap.dcMotor.get("backRightDrive");
        frontrightMotor = hardwareMap.dcMotor.get("frontRightDrive");
        
        linActMotor = hardwareMap.dcMotor.get("linAct");
        ShoulderMotor = hardwareMap.dcMotor.get("shoulder");
        
        wristServo = hardwareMap.get(Servo.class, "wrist");
        clawServo = hardwareMap.get(Servo.class, "claw");

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

            // Defining B,X - shoulder up/down
            if (B == true){
                // shoulder up
                shoulder.setPower(0.2);
            }
            if(X == true){
                // shoulder down
                shoulder.setPower(-0.2);     
            }
            // Defining LT and RT - lin act up/down
            if (RT == true){
                // lin act up
                linAct.setPower(0.5);
            }
            if (LT == true){
                // lin act down
                linAct.setPower(-0.5);
            }
            // Defining DU/DD on the D pad- wrist up/down
            // set position
            if (DU == true){  
                // wrist up
            }
            if (DD == true){    
                // wrist down
            }
            // Defining Y,A - open/close claw
            // set position
            if(Y == true){ 
                // claw open
            }
            if(A == true){ 
                // claw close
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
