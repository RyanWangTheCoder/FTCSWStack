// still
// working
// on
// it,
// dont
// really
// know
// what
// to
// do
// because
// i
// wasnt
// here
// when
// eric
// taught
// this

package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class LucasSensors {
    private LynxModule hub;
    private final HardwareQueue hardwareQueue;
    private final HardwareMap hardwareMap;
    double slidesEncoder = 0;
    double slidesVelocity = 0;
    boolean isSlidesDown = false;

    public LucasSensors (HardwareMap hardwareMap, HardwareQueue hardwareQueue) {
        this.hardwareMap = hardwareMap;
        this.hardwareQueue = hardwareQueue;
    }

    public double getSlidesEncoder() {
        return slidesEncoder;
    }
    public double getSlidesVelocity() {
        return slidesVelocity;
    }
    public boolean isSlidesDown() {
        return isSlidesDown;
    }

}
