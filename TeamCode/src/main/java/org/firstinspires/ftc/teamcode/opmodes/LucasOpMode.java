package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

@TeleOp
public class LucasOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareQueue hardwareQueue = new HardwareQueue();
        PriorityMotor frontLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor"), "frontLeftMotor", 1, 2, 1);
        PriorityMotor backLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("backLeftMotor"), "backLeftMotor", 1, 2, 1);
        PriorityMotor frontRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor"), "frontLeftMotor", 1, 2, 1);
        PriorityMotor backRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor"), "frontLeftMotor", 1, 2, 1);

        hardwareQueue.addDevice(frontLeftMotor);
        hardwareQueue.addDevice(backLeftMotor);
        hardwareQueue.addDevice(frontRightMotor);
        hardwareQueue.addDevice(backRightMotor);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            backRightMotor.setTargetPower(frontLeftPower);
            backLeftMotor.setTargetPower(backLeftPower);
            frontRightMotor.setTargetPower(frontRightPower);
            backRightMotor.setTargetPower(backRightPower);

            hardwareQueue.update();
        }
    }
}
