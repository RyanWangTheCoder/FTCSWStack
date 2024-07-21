package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

@TeleOp (name="My Cool Opmode")
public class LucasOpModes extends LinearOpMode {
    @Override
    public void runOpMode() {
        HardwareQueue hardwareQueue = new HardwareQueue();
        PriorityMotor myMotor = new PriorityMotor(
                hardwareMap.get(DcMotorEx.class, "myMotor"),
                "myMotor",
                5,
                2,
                1
        );
        hardwareQueue.addDevice(myMotor);
        waitForStart();
        double x = 0;
        while (opModeIsActive()) {
            myMotor.setTargetPower(Math.sin(x));
            x += 0.000001;
            hardwareQueue.update();
        }
    }
}
