package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

@Config
public class RyanWrist {
    public final Servo wrist = hardwareMap.get(Servo.class, "wrist");;
    public final PriorityServo wristServo;
    public RyanWrist(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        wristServo = new PriorityServo(wrist, "wrist", PriorityServo.ServoType.SPEED, 1.0, 0.0, 1.0, 0.0, false, 3.0, 5.0);
        normalWrist();
        hardwareQueue.addDevice(wristServo);
    }

    public void normalWrist(){
        wristServo.setTargetAngle(0.0, 1.0);
    }
    public void rotatedWrist(){
        wristServo.setTargetAngle(Math.PI, 1.0);
    }
    public boolean wristInPosition(){
        return wristServo.inPosition();
    }
}