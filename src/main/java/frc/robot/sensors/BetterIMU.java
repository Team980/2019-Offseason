package frc.robot.sensors;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.GyroBase;

/**
 * Talking to PigeonIMU with arrays is stupid and dumb. This lets us not do stupid and dumb. - Ethan W
 * It also lets us display a gyro widget on the dashboard. - Not Ethan
 */
public class BetterIMU extends GyroBase {

    private PigeonIMU inner;

    public BetterIMU(int channel) {
        inner = new PigeonIMU(channel);
    }

    public double getYaw() {
        var ypr = new double[3];
        inner.getYawPitchRoll(ypr);
        return ypr[0];
    }

    public double getPitch() {
        var ypr = new double[3];
        inner.getYawPitchRoll(ypr);
        return ypr[1];
    }

    public double getRoll() {
        var ypr = new double[3];
        inner.getYawPitchRoll(ypr);
        return ypr[2];
    }

    @Override
    public double getAngle() {
        return getYaw();
    }

    @Override
    public double getRate() {
        return 0; // take that!
    }

    @Override
    public void calibrate() {} //stub

    @Override
    public void reset() {
        inner.setYaw(0);
    }
}