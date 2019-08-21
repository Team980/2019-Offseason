package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Util;

public class Potentiometer implements PIDSource {

    private AnalogInput analogInput;

    private static final double MIN_VOLTAGE = 0;
    private static final double MAX_VOLTAGE = 5;

    public Potentiometer(int channel) {
        analogInput = new AnalogInput(channel);
    }

    public double getAngle() {
        return voltageToAngle(analogInput.getVoltage());
    }

    private static double voltageToAngle(double voltage) {
        return Util.map(voltage, MIN_VOLTAGE, MAX_VOLTAGE, 0, 360);
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidType) {
        analogInput.setPIDSourceType(pidType);
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return analogInput.getPIDSourceType();
    }

    @Override
    public double pidGet() {
        return voltageToAngle(analogInput.pidGet());
    }
}