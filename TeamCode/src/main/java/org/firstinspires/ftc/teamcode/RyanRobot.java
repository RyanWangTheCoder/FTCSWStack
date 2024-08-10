package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.utils.Globals.GET_LOOP_TIME;
import static org.firstinspires.ftc.teamcode.utils.Globals.START_LOOP;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.sensors.RyanSensors;
import org.firstinspires.ftc.teamcode.subsystems.RyanIntake;
import org.firstinspires.ftc.teamcode.subsystems.RyanSlides;
import org.firstinspires.ftc.teamcode.utils.Globals;
import org.firstinspires.ftc.teamcode.utils.Pose2d;
import org.firstinspires.ftc.teamcode.utils.Vector3;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class RyanRobot {
    public HardwareQueue hardwareQueue;
    public final RyanIntake intake;
    public final RyanSlides slides;
    public final RyanSensors sensor;
    public RyanRobot(HardwareMap hardwareMap) {
        hardwareQueue = new HardwareQueue();
        intake = new RyanIntake(hardwareMap, hardwareQueue);
        sensor = new RyanSensors(hardwareMap, hardwareQueue);
        slides = new RyanSlides(hardwareMap, hardwareQueue, sensor);

    }

    public void update() {
        updateSubsystems();
    }

    private void updateSubsystems() {
        hardwareQueue.update();

        intake.update();
        slides.update();
    }

}
