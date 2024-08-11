package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RyanRobot;
import org.firstinspires.ftc.teamcode.sensors.RyanSensors;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

import java.util.Arrays;
import java.util.List;

public class RyanDrivetrain {
    public PriorityMotor leftFront, leftRear, rightRear, rightFront;
    private HardwareQueue hardwareQueue;
    private RyanSensors sensors;
    public RyanRobot robot;

    public RyanDrivetrain(HardwareMap hardwareMap, HardwareQueue hardwareQueue, RyanSensors sensors, RyanRobot robot) {
        this.hardwareQueue = hardwareQueue;
        this.sensors = sensors;
        this.robot = robot;

        leftFront = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "leftFront"),
                "leftFront",
                3, 5
        );

        leftRear = new PriorityMotor(
                hardwareMap.get(DcMotorEx.class, "leftRear"),
                "leftRear",
                3, 5
        );
        rightRear = new PriorityMotor(
                hardwareMap.get(DcMotorEx.class, "rightRear"),
                "rightRear",
                3, 5
        );
        rightFront = new PriorityMotor(
                hardwareMap.get(DcMotorEx.class, "rightFront"),
                "rightFront",
                3, 5
        );

        hardwareQueue.addDevice(leftFront);
        hardwareQueue.addDevice(leftRear);
        hardwareQueue.addDevice(rightFront);
        hardwareQueue.addDevice(rightRear);
    }

    public void setMotorPowers(double lf, double lr, double rr, double rf) {
        leftFront.setTargetPowerSmooth(lf);
        leftRear.setTargetPowerSmooth(lr);
        rightRear.setTargetPowerSmooth(rr);
        rightFront.setTargetPowerSmooth(rf);
    }
    public void drive(Gamepad gamepad1) {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x; // multiply this by 1.1 if needed to help with strafing
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        setMotorPowers(frontLeftPower, backLeftPower, frontRightPower, backRightPower);
    }
}
