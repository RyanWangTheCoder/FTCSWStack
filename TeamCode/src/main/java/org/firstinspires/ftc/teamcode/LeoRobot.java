package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.LeoIntake;
import org.firstinspires.ftc.teamcode.sensors.LeoSensors;
import org.firstinspires.ftc.teamcode.subsystems.LeoSlides;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class LeoRobot {
    public HardwareQueue hardwareQueue;
    public final LeoIntake intake;
    public final LeoSlides slides;
    public final LeoSensors sensor;
    public LeoRobot(HardwareMap hardwareMap) {
        hardwareQueue = new HardwareQueue();
        intake = new LeoIntake(hardwareMap, hardwareQueue);
        sensor = new LeoSensors(hardwareQueue, hardwareMap);
        slides = new LeoSlides(hardwareMap, hardwareQueue, sensor);
    }

    private void update() {
        hardwareQueue.update();
        intake.update();
        slides.update();
        sensor.update();
    }
}
