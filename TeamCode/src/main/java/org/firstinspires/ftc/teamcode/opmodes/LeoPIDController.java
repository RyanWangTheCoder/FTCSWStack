package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.util.*;

public class LeoPIDController {
    public double Kp;
    public double Ki;
    public double Kd;
    public LeoPIDController(double p, double i, double d){
        Kp = p;
        Ki = i;
        Kd = d;
    }
    public void updatePIDController(double Kp, double Ki, double Kd){
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
    }
    double integralSum = 0;
    double lastError = 0;
    double lastReference = 0;
    double deltaError = 0;
    double xc = 0;
    double xp = 0;

    public void resetIntegral(){
        integralSum = 0;
    }

    ElapsedTime timer = new ElapsedTime();

    public double update(double reference, double position){
        if(reference != lastReference){
            integralSum = 0;
        }
        double error = reference - position;
        deltaError = error - lastError;

        xc = 0.8 * xp + 0.2 * deltaError;
        xp = xc;

        double derivative = xc/timer.seconds();
        integralSum = integralSum + error * timer.seconds();
        lastError = error;
        lastReference = reference;
        timer.reset();
        return (Kp * error + Ki * integralSum + Kd * derivative);
    }
}
