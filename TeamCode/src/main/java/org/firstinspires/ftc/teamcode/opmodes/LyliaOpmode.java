package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class LyliaOpmode extends LinearOpMode {

    DcMotorEx frontLeftMotor = (DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor");
    DcMotorEx backRightMotor = (DcMotorEx) hardwareMap.dcMotor.get("backRightMotor");
    DcMotorEx frontRightMotor = (DcMotorEx) hardwareMap.dcMotor.get("frontRightMotor");
    DcMotorEx backLeftMotor = (DcMotorEx) hardwareMap.dcMotor.get("backLeftMotor");

    @TeleOp
    public void runOpMode() throws InterruptedException {
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()){
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            double frontLeftPower = y + x + rx;
            double backRightPower = y + x - rx;
            double frontRightPower = y - x - rx;
            double backLeftPower = y - x + rx;

            frontLeftMotor.setPower(frontLeftPower);
            backRightMotor.setPower(backRightPower);
            frontRightMotor.setPower(frontRightPower);
            backLeftMotor.setPower(backLeftPower);
        }

    }

}