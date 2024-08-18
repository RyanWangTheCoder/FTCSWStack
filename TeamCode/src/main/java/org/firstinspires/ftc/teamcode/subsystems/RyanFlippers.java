package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

@Config
public class RyanFlippers {

    public final Servo leftServo = hardwareMap.get(Servo.class, "leftServo");
    ;
    public final Servo rightServo = hardwareMap.get(Servo.class, "rightServo");
    ;
    public final PriorityServo flipperServo_left;
    public final PriorityServo flipperServo_right;

    public RyanFlippers(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        flipperServo_left = new PriorityServo(leftServo, "leftFlipper", PriorityServo.ServoType.SPEED, 1.0, 0.0, 1.0, 0.0, false, 3.0, 5.0);
        flipperServo_right = new PriorityServo(rightServo, "rightFlipper", PriorityServo.ServoType.SPEED, 1.0, 0.0, 1.0, 0.0, false, 3.0, 5.0);
        closeBoth();
        hardwareQueue.addDevice(flipperServo_left);
        hardwareQueue.addDevice(flipperServo_right);
    }

    public boolean leftInPosition(){
        return flipperServo_left.inPosition();
    }
    public boolean rightInPosition(){
        return flipperServo_right.inPosition();
    }
    public void openLeft() {
        flipperServo_left.setTargetAngle(1.0, 1.0);
    }

    public void closeLeft() {
        flipperServo_left.setTargetAngle(0.0, 1.0);
    }

    public void openRight() {
        flipperServo_right.setTargetAngle(1.0, 1.0);
    }

    public void closeRight() {
        flipperServo_right.setTargetAngle(0.0, 1.0);
    }

    public void openBoth() {
        flipperServo_left.setTargetAngle(1.0, 1.0);
        flipperServo_right.setTargetAngle(1.0, 1.0);
    }

    public void closeBoth() {
        flipperServo_left.setTargetAngle(0.0, 1.0);
        flipperServo_right.setTargetAngle(0.0, 1.0);
    }
}