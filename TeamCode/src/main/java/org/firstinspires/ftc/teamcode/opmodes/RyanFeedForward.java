package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.utils.Utils;

// https://www.ctrlaltftc.com/the-pid-controller
@Config
public class RyanFeedForward {
    public double p;
    public double s;
    public RyanFeedForward(double P, double S){
        p=P;
        s=S;
    }
    double lastError = 0;

    public double update(double error, double min, double max){
        double proportion = p * error;
        double K_static = s;

        lastError = error;

        return Utils.minMaxClip(proportion + K_static, min, max);
    }

    public void updatePID(double p, double s) {
        this.p = p;
        this.s = s;
    }
}