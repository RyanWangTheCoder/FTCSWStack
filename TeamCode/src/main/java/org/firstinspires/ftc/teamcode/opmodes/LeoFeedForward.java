package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import org.firstinspires.ftc.teamcode.utils.Utils;

@Config
public class LeoFeedForward {
    public double p;
    public double ks;
    public LeoFeedForward(double P, double KS){
        p = P;
        ks = KS;
    }
    public double update(double error, double min, double max){
        return Utils.minMaxClip(p * error + ks, min, max);
    }

    public void updateFeedForward(double p, double ks){
        this.p = p;
        this.ks = ks;
    }
}
