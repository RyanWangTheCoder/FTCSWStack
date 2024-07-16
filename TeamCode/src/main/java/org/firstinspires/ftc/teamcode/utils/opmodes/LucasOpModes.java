package org.firstinspires.ftc.teamcode.utils.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name="My Cool Opmode")
public class LucasOpModes extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor myMotor = hardwareMap.get(DcMotor.class, "MyMotor");
        waitForStart();
        double x = 0;
        while (opModeIsActive()) {
            myMotor.setPower(Math.sin(x));
            x += 0.00001;
        }
    }
}
