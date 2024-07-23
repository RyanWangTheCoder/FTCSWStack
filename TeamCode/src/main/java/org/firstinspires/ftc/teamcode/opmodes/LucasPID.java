package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.util.ElapsedTime;

public class LucasPID {
    public static void main() {
        double Kp = 0.3;
        double Ki = 0.5;
        double Kd = 0.7; //random values
        double reference = 0.9;
        double error = 0;
        double lastError = 123;

        ElapsedTime time = new ElapsedTime();
    }
}
