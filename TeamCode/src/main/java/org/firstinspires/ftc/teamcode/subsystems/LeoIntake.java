package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

@Config
public class LeoIntake {
    public enum State{
        ON,
        OFF,
        ANTI_STALL,
        REVERSE
    }
    public final PriorityMotor intake;
    public State state;
    public static double intakePower = 1.0;
    public LeoIntake(HardwareMap hardwareMap, HardwareQueue hardwareQueue){
        intake = new PriorityMotor(hardwareMap.get(DcMotorEx.class, "intake"), "intake", 1, 2, -1);
        this.state = State.OFF;
        hardwareQueue.addDevice(intake);
    }
    public void update(){
        switch(state){
            case ON:
                intake.setTargetPower(intakePower);
                break;
            case OFF:
                intake.setTargetPower(0);
            case ANTI_STALL:
                intake.setTargetPower(-0.35);
            case REVERSE:
                intake.setTargetPower(-1);
        }
    }
    public void setOn(){
        state = State.ON;
    }
    public void setOff(){
        state = State.OFF;
    }
    public void setAntiStall(){
        state = State.ANTI_STALL;
    }
    public void setReverse(){
        state = State.REVERSE;
    }
}
