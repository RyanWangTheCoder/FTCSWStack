package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class LucasSlides {
    public enum State {
        ON,
        OFF
    }

    public PriorityMotor slides;
    public State motorState;
    public static double slidesPower = 1.0;

    public LucasSlides (HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        slides = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "slides"), "slides", 1, 2, -1);
        this.motorState = LucasSlides.State.OFF;
        hardwareQueue.addDevice(slides);
    }

    public void update() {
        switch (motorState) {
            case ON:
                slides.setTargetPower(1.0);
                break;
            case OFF:
                slides.setTargetPower(0.0);
                break;
        }
    }

}
