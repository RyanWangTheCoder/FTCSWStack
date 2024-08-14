package org.firstinspires.ftc.teamcode.v4Bar;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

public class LucasV4Bar {
    Servo servo1;
    Servo servo2;
    Servo flipper1;
    Servo flipper2;
    PriorityServo servos;

    HardwareQueue hardwareQueue = new HardwareQueue();

    public LucasV4Bar(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        servo1 = hardwareMap.get(Servo.class, "leftArmServo");
        servo2 = hardwareMap.get(Servo.class, "rightArmServo");
        flipper1 = hardwareMap.get(Servo.class, "leftFlipper");
        flipper2 = hardwareMap.get(Servo.class, "rightFlipper");
        servos = new PriorityServo(new Servo[] {servo1,servo2}, "servos", PriorityServo.ServoType.SPEED,  1.0, 0,  1,  0,  false,  3,  1, new double[]{1, -1});
        }

    public void setAngle(double angle) {
        servos.setTargetAngle(angle,1.0);
    }

    public void releaseLeftBall() {
        flipper1.setPosition(0);
    }

    public void releaseRightBall() {
        flipper2.setPosition(0);
    }
}