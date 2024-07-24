package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.util.ElapsedTime;

public class LucasPID {
    public static void main() {
        double p = 0;
        double i = 0;
        double d = 0;
        double reference = 0;
        double error = 0;
        double motorPosition = 0;
        double lastError = 0;
        double integralSum = 0;

        ElapsedTime time = new ElapsedTime();
        error = reference - motorPosition;
        integralSum = integralSum + (error * time.seconds() );
        double derivative = (error - lastError) / time.seconds();
        double out = error * p + integralSum * i + derivative * d;
        lastError = error;
        time.reset();
    }
}
