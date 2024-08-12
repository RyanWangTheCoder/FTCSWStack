package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.sensors.LeoSensors;
import org.firstinspires.ftc.teamcode.utils.Utils;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

@Config
public class LeoSlides {
    public static final double ticksToInches = 0.04132142857142857;
    public static final double p = 1;
    public static final double pK = 0.05;
    public enum State{
        ON,
        OFF
    }
    public final PriorityMotor slides;
    public State state;
    LeoSensors sensor;
    private double targetPosition;

    public LeoSlides(HardwareMap hardwareMap, HardwareQueue hardwareQueue, LeoSensors sensor){
        slides = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "intake"), "intake", 1, 2, -1);
        this.state = State.OFF;
        hardwareQueue.addDevice(slides);
        this.sensor = sensor;
    }
    public void update(){
        switch(state) {
            case ON:
                double currentPosition = sensor.getSlidesEncoder() * ticksToInches;
                slides.setTargetPower(Utils.minMaxClip((targetPosition - currentPosition) * p + pK, -1.0, 1.0));
                break;
            case OFF:
                slides.setTargetPower(0);
                break;
        }
    }
    public void setTargetPosition(double targetPosition) { this.targetPosition = targetPosition; }
}
