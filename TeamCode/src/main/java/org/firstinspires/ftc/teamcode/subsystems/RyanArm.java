package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityServo;

@Config
public class RyanArm {

    public final Servo leftServo = hardwareMap.get(Servo.class, "leftServo");;
    public final Servo rightServo = hardwareMap.get(Servo.class, "rightServo");;
    public final Servo[] servos = {leftServo, rightServo};
    public final PriorityServo armServo;
    double[] multipliers = {1.0, -1.0};
    double homePosition = 0.0;
    double scoringPosition = Math.PI;


    public RyanArm(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        armServo = new PriorityServo(servos, "arm", PriorityServo.ServoType.SPEED, 1.0, 0.0, 1.0, 0.0, false, 3.0, 5.0, multipliers);
        setHomePosition();
        hardwareQueue.addDevice(armServo);

    }
    public boolean armInPosition(){
        return armServo.inPosition();
    }
    public void setHomePosition(){
        armServo.setTargetAngle(homePosition, 1.0);
    }

    public void setScoringPosition() {
        armServo.setTargetAngle(scoringPosition, 1.0);
    }
}