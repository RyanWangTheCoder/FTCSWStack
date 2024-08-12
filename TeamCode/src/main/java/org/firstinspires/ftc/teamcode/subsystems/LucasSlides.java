package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.Utils;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;
import org.opencv.core.Core;

public class LucasSlides {
    public enum State {
        ON,
        OFF
    }

    public PriorityMotor slides;
    public State motorState;
    public static double slidesPower = 1.0;
    public double targetPosition;
    public static final double KP = 1;
    public static final double powerConstantTerm = 0.05;

    public LucasSlides (HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        slides = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "slides"), "slides", 1, 2, -1);
        this.motorState = LucasSlides.State.OFF;
        hardwareQueue.addDevice(slides);
    }

    public void update() {
        switch (motorState) {
            case ON:
                double currentPosition = slides.motor[0].getCurrentPosition();
                slides.setTargetPower((targetPosition - currentPosition) * KP + powerConstantTerm);
                break;
            case OFF:
                slides.setTargetPower(0.0);
                break;
        }
    }

}
