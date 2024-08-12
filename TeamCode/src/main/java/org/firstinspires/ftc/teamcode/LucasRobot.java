package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.LucasIntake;
import org.firstinspires.ftc.teamcode.subsystems.LucasSlides;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class LucasRobot {

    public HardwareQueue hardwareQueue;
    public final LucasIntake intake;
    public final LucasSlides slides;

    public LucasRobot (HardwareMap hardwareMap) {
        hardwareQueue = new HardwareQueue();
        intake = new LucasIntake(hardwareMap, hardwareQueue);
        slides = new LucasSlides(hardwareMap, hardwareQueue);
    }
    public void update() {
        updateLucasSubsystems();
    }

    public void updateLucasSubsystems() {
        intake.update();
        slides.update();
    }
}
