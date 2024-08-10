package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.RyanRobot;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class RyanSensors {
    private LynxModule controlHub, expansionHub;
    private final HardwareQueue hardwareQueue;
    private final HardwareMap hardwareMap;
    private RyanRobot  robot;

    //private IMU imu;

    private int slidesEncoder;
    private double slidesVelocity;
    private boolean slidesDown = false;


    private double voltage;


    private IMU imu;
    private long imuLastUpdateTime = System.currentTimeMillis();
    private double imuHeading = 0.0;
    public boolean useIMU; // don't change the value here. Change in drivetrain.

    public RyanSensors(HardwareMap hardwareMap, HardwareQueue hardwareQueue, RyanRobot robot) {
        this.hardwareMap = hardwareMap;
        this.hardwareQueue = hardwareQueue;
        this.robot = robot;

        initSensors(hardwareMap);
    }

    private void initSensors(HardwareMap hardwareMap) {
        controlHub = hardwareMap.get(LynxModule.class, "Control Hub");
        controlHub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);

        expansionHub = hardwareMap.get(LynxModule.class, "Expansion Hub");
        expansionHub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);

        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(
                new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                )
                )
        );
        imu.resetYaw();

        voltage = hardwareMap.voltageSensor.iterator().next().getVoltage();
    }

    public void update() {
        updateControlHub();
    }

    private double imuUpdateTime = 200;
    public double timeTillNextIMUUpdate = imuUpdateTime;
    public boolean imuJustUpdated = false;

    private double voltageUpdateTime = 5000;
    long lastVoltageUpdatedTime = System.currentTimeMillis();

    private void updateControlHub() {
        imuJustUpdated = false;
        long currTime = System.currentTimeMillis();
        if (useIMU && currTime - imuLastUpdateTime >= imuUpdateTime) {
            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            imuHeading = orientation.getYaw(AngleUnit.RADIANS);
            imuLastUpdateTime = currTime;
            imuJustUpdated = true;
        }

        timeTillNextIMUUpdate = imuUpdateTime - (currTime - imuLastUpdateTime);

        if (currTime - lastVoltageUpdatedTime > voltageUpdateTime) {
            voltage = hardwareMap.voltageSensor.iterator().next().getVoltage();
            lastVoltageUpdatedTime = currTime;
        }

        slidesEncoder = ((PriorityMotor) hardwareQueue.getDevice("rightFront")).motor[0].getCurrentPosition() * -1;


    }




    public int getSlidesPos() {
        return slidesEncoder;
    }
    public double getSlidesVelocity() {
        return slidesVelocity;
    }

    public boolean isSlidesDown() {
        return slidesDown;
    }



    public double getImuHeading() {
        return imuHeading;
    }

    public double getVoltage() { return voltage; }


    private double previousAngle = 0.0;
    private int numRotations = 0;

}

