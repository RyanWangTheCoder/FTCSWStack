package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

@TeleOp (name="My Cool Opmode")
public class LucasOpMode extends LinearOpMode {

    public void runOpMode() {
        @TeleOp
        class MecanumTeleOp extends LinearOpMode {
            @Override
            public void runOpMode() throws InterruptedException {
                DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
                DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
                DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
                DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

                frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

                waitForStart();

                if (isStopRequested()) return;

                while (opModeIsActive()) {
                    double y = -gamepad1.left_stick_y;
                    double x = gamepad1.left_stick_x * 1.1;
                    double rx = gamepad1.right_stick_x;
                    double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
                    double frontLeftPower = (y + x + rx) / denominator;
                    double backLeftPower = (y - x + rx) / denominator;
                    double frontRightPower = (y - x - rx) / denominator;
                    double backRightPower = (y + x - rx) / denominator;

                    frontLeftMotor.setPower(frontLeftPower);
                    backLeftMotor.setPower(backLeftPower);
                    frontRightMotor.setPower(frontRightPower);
                    backRightMotor.setPower(backRightPower);

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
                    double x1 = 0;
                    while (opModeIsActive()) {
                        myMotor.setTargetPower(Math.sin(x));
                        x1 += 0.000001;
                        hardwareQueue.update();
                    }
                }
            }
        }
    }
}