package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

@TeleOp (name = "Drivetrain sample code")
public class MyOpmode_Ryan extends LinearOpMode {
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
        to the original gm0 code was the (DcMotorEx). that way you can pass it in to the function.
        */

        HardwareQueue hardwareQueue = new HardwareQueue();
        PriorityMotor RealfrontLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor"), "frontLeftMotor", 1, 2, 1);
        PriorityMotor RealbackLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("backLeftMotor"), "backLeftMotor", 1, 2, 1);
        PriorityMotor RealfrontRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontRightMotor"), "frontRightMotor", 1, 2, -1);
        PriorityMotor RealbackRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("backRightMotor"), "backRightMotor", 1, 2, -1);

        /*
        For frontRightMotor and backRightMotor, notice how the multiplier is -1. This is because I don't have

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        so I make multiplier -1 to account for that.
         */

        hardwareQueue.addDevice(RealfrontLeftMotor);
        hardwareQueue.addDevice(RealbackLeftMotor);
        hardwareQueue.addDevice(RealfrontRightMotor);
        hardwareQueue.addDevice(RealbackRightMotor);

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

            RealfrontLeftMotor.setTargetPower(frontLeftPower);
            RealbackLeftMotor.setTargetPower(backLeftPower);
            RealfrontRightMotor.setTargetPower(frontRightPower);
            RealbackRightMotor.setTargetPower(backRightPower);

            hardwareQueue.update();
        }
    }
}