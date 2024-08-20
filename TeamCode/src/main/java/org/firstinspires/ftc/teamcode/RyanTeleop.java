package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class RyanTeleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RyanRobot robot = new RyanRobot(hardwareMap);
        // Declare our motors
        // Make sure your ID's match your configuration
        while (opModeInInit()) {
            robot.reset();

            robot.update();
        }
        waitForStart();
        while(opModeIsActive()) {
            robot.drivetrain.drive(gamepad1);

            
        }

    }
}