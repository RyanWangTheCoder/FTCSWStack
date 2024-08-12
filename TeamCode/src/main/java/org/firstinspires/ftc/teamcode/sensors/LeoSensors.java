package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class LeoSensors {
    private LynxModule hub;
    private final HardwareQueue hardwareQueue;
    private final HardwareMap hardwareMap;

    private int slidesEncoder;
    private double slidesVelocity;
    private boolean slidesDown = false;

    public LeoSensors(HardwareQueue hardwareQueue, HardwareMap hardwareMap){
        this.hardwareQueue = hardwareQueue;
        this.hardwareMap = hardwareMap;
        initSensors(hardwareMap);
    }
    public void initSensors(HardwareMap hardwareMap){
        hub = hardwareMap.get(LynxModule.class, "hub");
        hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
    }
    public void update(){
        slidesEncoder = ((PriorityMotor) hardwareQueue.getDevice("slides")).motor[0].getCurrentPosition() * -1;
    }
    public int getSlidesEncoder() {
        return slidesEncoder;
    }
    public double getSlidesVelocity() {
        return slidesVelocity;
    }
    public boolean isSlidesDown() {
        return slidesDown;
    }
}
