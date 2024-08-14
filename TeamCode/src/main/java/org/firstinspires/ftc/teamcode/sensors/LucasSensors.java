package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class LucasSensors {
    private LynxModule controlHub, expansionHub;
    private final HardwareQueue hardwareQueue;
    private final HardwareMap hardwareMap;

    private double slidesEncoder;
    private double slidesVelocity;
    boolean isSlidesDown;

    public LucasSensors (HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        this.hardwareMap = hardwareMap;
        this.hardwareQueue = hardwareQueue;
    }

    public double getSlidesEncoder() {
        return slidesEncoder;
    }
    public double getSlidesVelocity() {
        return slidesVelocity;
    }
    public boolean isSlidesDown() {
        return isSlidesDown;
    }

}
