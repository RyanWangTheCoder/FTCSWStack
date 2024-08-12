package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.LeoRobot;
import org.firstinspires.ftc.teamcode.sensors.LeoSensors;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class LeoDrivetrain {
    public PriorityMotor leftFront;
    public PriorityMotor leftRear;
    public PriorityMotor rightFront;
    public PriorityMotor rightRear;

    private HardwareQueue hardwareQueue;
    private HardwareMap hardwareMap;
    private LeoSensors sensor;
    public LeoRobot robot;

    public LeoDrivetrain(HardwareQueue hardwareQueue, LeoSensors sensor, LeoRobot robot){
        this.hardwareQueue = hardwareQueue;
        this.sensor = sensor;
        this.robot = robot;

        leftFront = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "leftFront"), "leftFront",3, 5);
        leftRear = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "leftFront"), "leftFront",3, 5);
        rightFront = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "leftFront"), "leftFront",3, 5);
        rightRear = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "leftFront"), "leftFront",3, 5);

        hardwareQueue.addDevice(leftFront);
        hardwareQueue.addDevice(leftRear);
        hardwareQueue.addDevice(rightFront);
        hardwareQueue.addDevice(rightRear);
    }
    public void setPower(double lf, double lr, double rf, double rr){
        leftFront.setTargetPowerSmooth(lf);
        leftRear.setTargetPowerSmooth(lr);
        rightFront.setTargetPowerSmooth(rf);
        rightRear.setTargetPowerSmooth(rr);
    }
    public void drive(Gamepad gamepad) {
        double x = gamepad.left_stick_x * 1.1;
        double y = -gamepad.left_stick_y;
        double rx = gamepad.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        setPower(frontLeftPower, backLeftPower, frontRightPower, backRightPower);
    }
}
