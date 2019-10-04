package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Robot;
import frc.robot.Util;


public class Potentiometer implements PIDSource {
    public AnalogInput analogInput;

    private static final double MIN_VOLTAGE = 0;
    private static final double MAX_VOLTAGE = 5;

    private PIDSourceType sourceType;
    
    private double rate;
 
    public Potentiometer(int channel) {
        sourceType = PIDSourceType.kDisplacement;

        analogInput = new AnalogInput(channel);

        rate = 0;
    }

    public void updateSpeed() {
        Robot.debugTable.getEntry("wrist speed 3").setNumber(getRate());

    }

    public double getRate() {
       return rate;
    }

    public void setRate(double rate){
        this.rate = rate;
    }

    public double getAngle() {
        return voltageToAngle(analogInput.getVoltage());
    }

    private static double voltageToAngle(double voltage) {
        return Util.map(voltage, MIN_VOLTAGE, MAX_VOLTAGE, 0, 360);
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidType) {
        sourceType = pidType;
        analogInput.setPIDSourceType(pidType);
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return sourceType;
    }

    @Override
    public double pidGet() {
        if (sourceType == PIDSourceType.kDisplacement) {
            return voltageToAngle(analogInput.pidGet()); 
        } else {
            return getRate();
        }
    }
}