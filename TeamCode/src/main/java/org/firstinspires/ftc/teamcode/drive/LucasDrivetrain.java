package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class LucasDrivetrain {
    public PriorityMotor frontLeftMotor;
    public PriorityMotor frontRightMotor;
    public PriorityMotor backLeftMotor;
    public PriorityMotor backRightMotor;

    public HardwareQueue hardwareQueue;
    public HardwareMap hardwareMap;

    public LucasDrivetrain (HardwareQueue hardwareQueue, HardwareMap hardwareMap) {
        this.hardwareQueue = hardwareQueue;
        this.hardwareMap = hardwareMap;
        //why are these gray 👇
        PriorityMotor frontLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor"), "frontLeftMotor", 1, 2, 1);
        PriorityMotor backLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("backLeftMotor"), "backLeftMotor", 1, 2, 1);
        PriorityMotor frontRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor"), "frontLeftMotor", 1, 2, 1);
        PriorityMotor backRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor"), "frontLeftMotor", 1, 2, 1);
    }
    public volatile com.qualcomm.robotcore.hardware.Gamepad gamepad1;
    public void setVariables() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 1.1;
        double rx = gamepad1.right_stick_x;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeftMotor.setTargetPower(frontLeftPower);
        frontRightMotor.setTargetPower(backLeftPower);
        backLeftMotor.setTargetPower(frontRightPower);
        backRightMotor.setTargetPower(backRightPower);
    }
}
