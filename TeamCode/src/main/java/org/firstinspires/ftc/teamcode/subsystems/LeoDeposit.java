package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

@Config
public class LeoDeposit {
    public static double nonRotateAngle = Math.toRadians(180);
    public static double RotateAngle = Math.toRadians(0);
    public static double endReleaseAngle = Math.toRadians(90);
    public Servo left;
    public Servo right;
    public Servo rotate;
    public Servo leftBox;
    public Servo rightBox;
    public boolean rotated = false;
    public PriorityServo armServo, rotateServo, leftReleaseServo, rightReleaseServo;
    public HardwareMap hardwareMap;
    public LeoDeposit(HardwareMap hardwareMap, HardwareQueue hardwareQueue){
        this.hardwareMap = hardwareMap;
        left = hardwareMap.get(Servo.class, "Left");
        right = hardwareMap.get(Servo.class, "Right");
        rotate = hardwareMap.get(Servo.class, "Rotate");
        leftBox = hardwareMap.get(Servo.class, "Left Box");
        rightBox = hardwareMap.get(Servo.class, "Right Box");
        armServo = new PriorityServo(new Servo[] {left, right}, "Arm Servo", PriorityServo.ServoType.SPEED, 1.0, -1.0, 1.0, 0.0, false, 1.0, 5, new double[]{1.0, -1.0});
        rotateServo = new PriorityServo(rotate, "Rotate Servo", PriorityServo.ServoType.SPEED, 1.0, -1.0, 1.0, 0.0, false, 1.0, 1);
        leftReleaseServo = new PriorityServo(leftBox, "Left Release Servo", PriorityServo.ServoType.SPEED, 1.0, -1.0, 1.0, 0.0, false, 1.0, 1);
        rightReleaseServo = new PriorityServo(rightBox, "Left Release Servo", PriorityServo.ServoType.SPEED, 1.0, -1.0, 1.0, 0.0, false, 1.0, 1);
        hardwareQueue.addDevice(armServo);
        hardwareQueue.addDevice(rotateServo);
        hardwareQueue.addDevice(leftReleaseServo);
        hardwareQueue.addDevice(rightReleaseServo);
    }

    public void setAngle(double angle){
        armServo.setTargetAngle(angle, 1.0);
    }
    public void rotateBox(){
        if(rotated){
            rotateServo.setTargetAngle(nonRotateAngle, 1.0);
            rotated = true;
        }
        else{
            rotateServo.setTargetAngle(RotateAngle, 1.0);
            rotated = false;
        }
    }
    public void leftRelease(){
        leftReleaseServo.setTargetAngle(endReleaseAngle, 1.0);
    }
    public void rightRelease(){
        rightReleaseServo.setTargetAngle(endReleaseAngle, 1.0);
    }
}