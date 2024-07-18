package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

@TeleOp (name = "Drivetrain sample code")
public class MyOpmode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareQueue hardwareQueue = new HardwareQueue();
        PriorityMotor RealfrontLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor"), "frontLeftMotor", 1, 2, 1);
        PriorityMotor RealbackLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("backLeftMotor"), "backLeftMotor", 1, 2, 1);
        PriorityMotor RealfrontRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontRightMotor"), "frontRightMotor", 1, 2, -1);
        PriorityMotor RealbackRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("backRightMotor"), "backRightMotor", 1, 2, -1);

        hardwareQueue.addDevice(RealfrontLeftMotor);
        hardwareQueue.addDevice(RealbackLeftMotor);
        hardwareQueue.addDevice(RealfrontRightMotor);
        hardwareQueue.addDevice(RealbackRightMotor);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
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