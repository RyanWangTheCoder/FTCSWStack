package org.firstinspires.ftc.teamcode.v4Bar;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

public class LucasV4Bar {
    Servo servo1;
    Servo servo2;
    PriorityServo flipper1;
    PriorityServo flipper2;
    PriorityServo flipTurner;
    PriorityServo servos;
    double pi = 3.1415926535897932384;
    public double theFlippersAreBROKEN;

    HardwareQueue hardwareQueue = new HardwareQueue();

    public LucasV4Bar(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        servo1 = hardwareMap.get(Servo.class, "leftArmServo");
        servo2 = hardwareMap.get(Servo.class, "rightArmServo");
        flipper1 = hardwareMap.get(Servo.class, "leftFlipper");
        flipper2 = hardwareMap.get(Servo.class, "rightFlipper");
        flipTurner = hardwareMap.get(Servo.class, "flipTurner");
        servos =
    }

    public void setAngle(double angle) {
        servos.setTargetAngle(angle,1.0);
    }

    public void releaseRightBall() {
        checkFlippersFlipped();
        if (AreFlippersFlipped) {
            flipper2.
        }
    }

    public void releaseRightBall() {
        if (boolean AreFlippersFlipped = false;) {
            flipper2.setPosition(0);
        }
        if (AreFlippersFlipped = true;) {
            flipper2.setPosition(pi);
        }
    }
    boolean AreFlippersFlipped = true;

    private void checkFlippersFlipped() {
        if (flipTurner.getPosition() == pi) {
            AreFlippersFlipped = true;
        }
        if (flipTurner.getPosition() == 0) {
            AreFlippersFlipped = false;
        }
        if (flipTurner.getPosition() != 0 && flipTurner.getPosition() != pi) {
            theFlippersAreBROKEN = 1;
        }
    }
}