package org.firstinspires.ftc.teamcode.opmodes;


public class LyliaPID {
    public double Kp;
    public double Ki;
    public double Kd;

    //    PID() takes the values of Kp, Ki, and Kd as inputs
    public LyliaPID(double p, double i, double d){
        Kp=p;
        Ki=i;
        Kd=d;
    }

    double lastError = 0;
    double integral = 0; //some sort of sum of errors
    long lastLoopTime=System.nanoTime(); //gets the current time elapsed in nanoseconds
    double loopTime=0.0;
    boolean counter=true; // this is the first time the loop is being run

    public void resetIntegral() {integral=0; }

    public double update(double error /*, double min, double max*/){
        if (counter==true){
            lastLoopTime = System.nanoTime() / 1000000; //converts to something smaller than nanoseconds?
        }

        //get the current time
        long currentTime = System.nanoTime();
        loopTime = (currentTime-lastLoopTime)/1000000000.0; //what is the difference between currentTime and loopTime?
        lastLoopTime=currentTime;

        //why does ctrlaltftc use a while loop but that is not used here?
        double proportion = Kp * error; //scale the error by the Kp value
        integral += Ki * error * loopTime; //add the error multiplied by time to integral
        double derivative = Kd * (error - lastError) / lastLoopTime; //rate of change in error

        lastError=error;
        counter=false;

        return proportion+integral+derivative; //did not min-max clip
    }

    //in case you want to change p, i, and d values
    public void updatePID(double p, double i, double d) {
        this.Kp=p;
        this.Ki=i;
        this.Kd=d;
    }
}