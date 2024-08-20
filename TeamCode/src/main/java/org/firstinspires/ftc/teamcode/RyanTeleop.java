package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.utils.ButtonToggle;

@TeleOp
public class RyanTeleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RyanRobot robot = new RyanRobot(hardwareMap);
        // Declare our motors
        // Make sure your ID's match your configuration

        // DRIVER 1
        ButtonToggle rightBumper_1 = new ButtonToggle();
        ButtonToggle leftBumper_1 = new ButtonToggle();
        ButtonToggle x_1 = new ButtonToggle();
        ButtonToggle b_1 = new ButtonToggle();
        ButtonToggle y_1 = new ButtonToggle();
        ButtonToggle a_1 = new ButtonToggle();
        ButtonToggle leftTrigger_1 = new ButtonToggle();
        ButtonToggle rightTrigger_1 = new ButtonToggle();
        ButtonToggle leftTrigger_1_double = new ButtonToggle();
        ButtonToggle up_dpad_1 = new ButtonToggle();
        ButtonToggle down_dpad_1 = new ButtonToggle();

        // DRIVER 2
        ButtonToggle dpadUp_2 = new ButtonToggle();
        ButtonToggle dpadDown_2 = new ButtonToggle();
        ButtonToggle dpadLeft_2 = new ButtonToggle();
        ButtonToggle dpadRight_2 = new ButtonToggle();
        ButtonToggle leftTrigger_2 = new ButtonToggle();
        ButtonToggle rightTrigger_2 = new ButtonToggle();
        ButtonToggle leftBumper_2 = new ButtonToggle();
        ButtonToggle rightBumper_2 = new ButtonToggle();
        ButtonToggle a_2 = new ButtonToggle();
        ButtonToggle x_2 = new ButtonToggle();
        ButtonToggle y_2 = new ButtonToggle();

        while (opModeInInit()) {
            robot.reset();

            robot.update();
        }
        waitForStart();
        while(opModeIsActive()) {
            robot.drivetrain.drive(gamepad1);

            if(x_1.isClicked(gamepad1.x)){
                robot.startDepositSetup();
            }

            if(leftBumper_1.isClicked(gamepad1.left_bumper)){
                robot.startDepositBalls();
            }
            if(y_1.isClicked(gamepad1.y)){
                robot.reset();
            }
            if(rightBumper_1.isClicked(gamepad1.right_bumper)){
                robot.rotateWrist();
            }
            if(up_dpad_1.isHeld(gamepad1.dpad_up, 0.1)){
                robot.slides.setTargetPosition(robot.slides.getCurrPos() + 0.5);
            }
            if(down_dpad_1.isHeld(gamepad1.dpad_down, 0.1)){
                robot.slides.setTargetPosition(robot.slides.getCurrPos() - 0.5);
            }

            robot.update();
        }

    }
}