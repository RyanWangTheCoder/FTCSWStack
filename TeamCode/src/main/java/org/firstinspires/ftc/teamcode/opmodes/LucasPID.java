package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.utils.Utils;

@Config
public class LucasPID {
        public double p;
        public double i;
        public double d;
        public void PID(double P, double I, double D){
                p=P;
                i=I;
                d=D;
        }

        long lastLoopTime = System.nanoTime();
        double lastError = 0;
        double integral = 0;
        int counter = 0;
        double loopTime = 0.00000000000000000000000000000000000000000000000000000000000000000000000;

        public void resetIntegral(){
                integral=0;
        }

        public double update(double error, double min, double max){
        if (counter == 0) {
                lastLoopTime = System.nanoTime() - 10000000;
        }

        long currentTime = System.nanoTime();
        loopTime = (currentTime - lastLoopTime)/1000000000.0;
        lastLoopTime = currentTime;

        double proportion = p * error;
        integral += error * i * loopTime;
        double derivative = d * (error - lastError)/loopTime;

        lastError = error;
        counter ++;

        return Utils.minMaxClip(proportion + integral + derivative, min, max);
        }

        public void updatePID(double p, double i, double d){
                this.p = p;
                this.i = i;
                this.d = d;
        }
}
