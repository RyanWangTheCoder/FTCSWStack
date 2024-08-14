package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.utils.Globals.GET_LOOP_TIME;
import static org.firstinspires.ftc.teamcode.utils.Globals.START_LOOP;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.RyanDrivetrain;
import org.firstinspires.ftc.teamcode.sensors.RyanSensors;
import org.firstinspires.ftc.teamcode.subsystems.RyanArm;
import org.firstinspires.ftc.teamcode.subsystems.RyanFlippers;
import org.firstinspires.ftc.teamcode.subsystems.RyanIntake;
import org.firstinspires.ftc.teamcode.subsystems.RyanSlides;
import org.firstinspires.ftc.teamcode.subsystems.RyanWrist;
import org.firstinspires.ftc.teamcode.utils.Globals;
import org.firstinspires.ftc.teamcode.utils.Pose2d;
import org.firstinspires.ftc.teamcode.utils.Vector3;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class RyanRobot {
    public HardwareQueue hardwareQueue;
    public final RyanIntake intake;
    public final RyanSlides slides;
    public final RyanSensors sensor;
    public final RyanDrivetrain drivetrain;
    public final RyanArm arm;
    public final RyanFlippers flippers;
    public final RyanWrist wrist;

    public RyanRobot(HardwareMap hardwareMap) {
        hardwareQueue = new HardwareQueue();
        intake = new RyanIntake(hardwareMap, hardwareQueue);
        sensor = new RyanSensors(hardwareMap, hardwareQueue);
        slides = new RyanSlides(hardwareMap, hardwareQueue, sensor);
        drivetrain = new RyanDrivetrain(hardwareMap, hardwareQueue, sensor);
        arm = new RyanArm(hardwareMap, hardwareQueue);
        flippers = new RyanFlippers(hardwareMap, hardwareQueue);
        wrist = new RyanWrist(hardwareMap, hardwareQueue);
    }

    public void update() {
        updateSubsystems();
    }

    private void updateSubsystems() {
        hardwareQueue.update();

        intake.update();
        slides.update();
        sensor.update();
    }
    public void raiseSlidesToScoringPosition(){
        double slidesDepositPosition = 1.0;
        slides.setTargetPosition(slidesDepositPosition);
    }
    public void lowerSlidesToHomePosition(){
        slides.setTargetPosition(0.0);
    }
    public void moveArmToScoringPosition(){
        arm.setScoringPosition();
    }
    public void moveArmToHomePosition(){
        arm.setHomePosition();
    }
    public void openBothFlippers(){
        flippers.openBoth();
    }
    public void openLeftFlipper(){
        flippers.openLeft();
    }
    public void openRightFlipper(){
        flippers.openRight();
    }
    public void closeBothFlippers(){
        flippers.closeBoth();
    }
    public void closeLeftFlipper(){
        flippers.closeLeft();
    }
    public void closeRightFlipper(){
        flippers.closeRight();
    }
    public void setNormalWristPosition(){
        wrist.normalWrist();
    }
    public void setRotatedWristPosition(){
        wrist.rotatedWrist();
    }
    public void turnOnIntake(){
        intake.setOn();
    }
    public void turnOffIntake(){
        intake.setOff();
    }
    public enum Deposit_States {
        INTAKE_BALLS,
        RAISE_SLIDES,
        RAISE_ARM,
        WAIT_FOR_DEPOSIT,
        ROTATE_WRIST,
        OPEN_FLIPPER,




    }

    public void depositSequence(){

    }

}
