package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Utils;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

@Config
public class RyanSlides {
    public enum MotorState {
        ON,
        OFF,
    }
    public final PriorityMotor slides;
    public MotorState motorState;
    public static final double KP = 1;
    public static final double powerConstantTerm = 0.05;
    public static double slidesPower = 1.0;
    private double targetPosition;

    public RyanSlides(HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        slides = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "slides"), "slides", 1, 2, -1);
        this.motorState = MotorState.OFF;
        hardwareQueue.addDevice(slides);
    }

    public void update() {
        switch (motorState) {
            case ON:
                double currentPosition = slides.motor[0].getCurrentPosition();
                slides.setTargetPower(Utils.minMaxClip((targetPosition - currentPosition) * KP+powerConstantTerm, -1.0, 1.0));
                break;
            case OFF:
                slides.setTargetPower(0.0);
                break;

        }
    }
}