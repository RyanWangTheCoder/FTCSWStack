package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.sensors.RyanSensors;
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
    public static double ticksToInches = 0.04132142857142857;

    public static final double KP = 1;
    public static final double powerConstantTerm = 0.05;
    public static double slidesPower = 1.0;
    RyanSensors sensor;


    private double targetPosition;

    public RyanSlides(HardwareMap hardwareMap, HardwareQueue hardwareQueue, RyanSensors sensor) {
        slides = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "slides"), "slides", 1, 2, -1);
        this.motorState = MotorState.OFF;
        hardwareQueue.addDevice(slides);
        this.sensor = sensor;
    }

    public void update() {
        switch (motorState) {
            case ON:
                double currentPosition = sensor.getSlidesPos()*ticksToInches;
                slides.setTargetPower(Utils.minMaxClip((targetPosition - currentPosition) * KP+powerConstantTerm, -1.0, 1.0));
                break;
            case OFF:
                slides.setTargetPower(0.0);
                break;

        }
    }
    public void setTargetPosition(double targetPosition) { this.targetPosition = targetPosition; }

}