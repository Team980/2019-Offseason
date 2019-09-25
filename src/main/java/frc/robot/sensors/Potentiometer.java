package frc.robot.sensors;


import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.Robot;
import frc.robot.Util;

//import edu.wpi.first.wpilibj.PIDSource;
//import edu.wpi.first.wpilibj.PIDSourceType;
//import java.util.ArrayList;

public class Potentiometer /*implements PIDSource*/ {
    public AnalogInput analogInput;

    private static final double MIN_VOLTAGE = 0;
    private static final double MAX_VOLTAGE = 5;

    private static final double REFRESH_INTERVAL_SECONDS = 0.02; 

   //private PIDSourceType sourceType;
    
    double prevPosition;
    double currPosition;

    public Potentiometer(int channel) {
        //sourceType = PIDSourceType.kDisplacement;

        analogInput = new AnalogInput(channel);

        prevPosition = getAngle();
        currPosition = getAngle();
    }

    public void updateSpeed() {
        Robot.debugTable.getEntry("wrist speed 3").setNumber(getRate());

        prevPosition = currPosition;
        currPosition = getAngle();
    }

    public double getRate() {
        double rate = (currPosition - prevPosition) / REFRESH_INTERVAL_SECONDS;
        if (Math.abs(rate) < 5) {
            return 0;
        } else {
            return rate;
        }
    }

    public double getAngle() {
        return voltageToAngle(analogInput.getVoltage());
    }

    private static double voltageToAngle(double voltage) {
        return Util.map(voltage, MIN_VOLTAGE, MAX_VOLTAGE, 0, 360);
    }

    /*@Override
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
    }*/
}