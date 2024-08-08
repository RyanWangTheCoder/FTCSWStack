package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

@Config
public class LucasIntake {
    public enum MotorState {
        ON,
        OFF,
        REVERSE,
        SOFT_REVERSE
    }

    public PriorityMotor intake;
    public MotorState motorState;
    public static double intakePower = 1.0;

    public LucasIntake (HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        intake = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "intake"), "intake", 1, 2, -1);
        this.motorState = MotorState.OFF;
        hardwareQueue.addDevice(intake);
    }

    public void update() {
        switch (motorState) {
            case ON:
                intake.setTargetPower(1.0);
                break;
            case OFF:
                intake.setTargetPower(0.0);
                break;
            case REVERSE:
                intake.setTargetPower(-1.0);
                break;
            case SOFT_REVERSE:
                intake.setTargetPower(-0.35);
                break;
        }
    }
}