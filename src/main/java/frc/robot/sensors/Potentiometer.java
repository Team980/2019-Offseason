package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.Util;

public class Potentiometer { //TODO implement pot

    private AnalogInput analogInput;

    private static final double MIN_VOLTAGE = 0;
    private static final double MAX_VOLTAGE = 0xbeef;

    public Potentiometer(int channel) {
        analogInput = new AnalogInput(channel);
        // FIXME: how many times do we want to oversample or average? Should we set a sampling rate?
        // analogInput.setAverageBits();
    }

    public double getAngle() { // should be degrees
        double rawOut = analogInput.getVoltage(); // maybe call getAverageVoltage here?

        return Util.map(rawOut, MIN_VOLTAGE, MAX_VOLTAGE, 0, 360);
    }
}