package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.Util;

public class Potentiometer {

    private AnalogInput analogInput;

    private static final double MIN_VOLTAGE = 0;
    private static final double MAX_VOLTAGE = 5;

    public Potentiometer(int channel) {
        analogInput = new AnalogInput(channel);
    }

    public double getAngle() { 
        double rawOut = analogInput.getVoltage(); 

        return Util.map(rawOut, MIN_VOLTAGE, MAX_VOLTAGE, 0, 360);
    }
}