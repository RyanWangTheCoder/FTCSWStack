package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

@TeleOp (name = "Drivetrain sample code")
public class RyanOpmode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        /*
        DcMotorEx frontLeftMotor = (DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotorEx backLeftMotor = (DcMotorEx) hardwareMap.dcMotor.get("backLeftMotor");
        DcMotorEx frontRightMotor = (DcMotorEx) hardwareMap.dcMotor.get("frontRightMotor");
        DcMotorEx backRightMotor = (DcMotorEx) hardwareMap.dcMotor.get("backRightMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        HardwareQueue hardwareQueue = new HardwareQueue();
        PriorityMotor RealfrontLeftMotor = new PriorityMotor(frontLeftMotor, "frontLeftMotor", 1, 2, 1);
        PriorityMotor RealbackLeftMotor = new PriorityMotor(backLeftMotor, "backLeftMotor", 1, 2, 1);
        PriorityMotor RealfrontRightMotor = new PriorityMotor(frontRightMotor, "frontRightMotor", 1, 2, 1);
        PriorityMotor RealbackRightMotor = new PriorityMotor(backRightMotor, "backRightMotor", 1, 2, 1);

        Above was the original code, and it's a little bit easier to understand. One thing I had to add
        to the original gm0 code was the (DcMotorEx). that way you can pass it in to the constructor.
        */

        HardwareQueue hardwareQueue = new HardwareQueue();
        PriorityMotor frontLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor"), "frontLeftMotor", 1, 2, 1);
        PriorityMotor backLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("backLeftMotor"), "backLeftMotor", 1, 2, 1);
        PriorityMotor frontRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontRightMotor"), "frontRightMotor", 1, 2, -1);
        PriorityMotor backRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("backRightMotor"), "backRightMotor", 1, 2, -1);
        // Declare our motors
        // Make sure your ID's match your configuration
        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.

        /*
        For frontRightMotor and backRightMotor, notice how the multiplier is -1. This is because I don't have

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        so I make multiplier -1 to account for that.
         */

        hardwareQueue.addDevice(frontLeftMotor);
        hardwareQueue.addDevice(backLeftMotor);
        hardwareQueue.addDevice(frontRightMotor);
        hardwareQueue.addDevice(backRightMotor);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x; // multiply this by 1.1 if needed to help with strafing
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]

            frontLeftMotor.setTargetPower(frontLeftPower);
            backLeftMotor.setTargetPower(backLeftPower);
            frontRightMotor.setTargetPower(frontRightPower);
            backRightMotor.setTargetPower(backRightPower);

            hardwareQueue.update();
        }
    }
}