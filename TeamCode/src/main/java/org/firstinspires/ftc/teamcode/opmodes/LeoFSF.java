package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.util.*;

public class LeoFSF {
    public double K1;
    public double K2;
    public LeoFSF(double k1, double k2){
        K1 = k1;
        K2 = k2;
    }
    public void updatePIDController(double K1, double K2){
        this.K1 = K1;
        this.K2 = K2;
    }
    double dP = 0;
    double dV = 0;

    ElapsedTime timer = new ElapsedTime();

    public double calculatedStateFeedback(double tP, double tV, double rP, double rV){
        dP = tP - rP;
        dV = tV - rV;
        return dP * K1 + dV * K2;
    }
}
