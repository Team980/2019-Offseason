package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Robot;
//import frc.robot.commands.LiftHoldCommand;


public class LiftSystem /*extends Subsystem*/ {
 /*   private static final double ENCODER_MAX_TICK_COUNT = 22_000; // TODO: change this value later to trashpanda's map

    private static final double DEADZONE = 0.05;

    public static final double Tau = 2*Math.PI;
    static Encoder liftEncoder;
    WPI_TalonSRX liftController;    

    private double distance;

    public void move(double TargetValue){
        distance = getPosition() - TargetValue;

         if(Math.abs(distance) > DEADZONE){
             setVelocity(distance);
        } else {
            setVelocity(0);        
        }
    }

    public LiftSystem()  {
        liftEncoder = Robot.robotMap.liftEncoder;
        liftController = Robot.robotMap.liftController;
    }

    public void setVelocity(double newVelocity) {
        liftController.set(newVelocity);
    }

    public void resetEncoder() {
        liftEncoder.reset();
    }
    
    public boolean isAtTargetHeight(double targetValue) {
        return Math.abs(getPosition() - targetValue) < DEADZONE;
    }
    
    public double getPosition() {
        double tickCount = (double)liftEncoder.getRaw();
        return map(tickCount, 0, ENCODER_MAX_TICK_COUNT, 0, 1);
    }

    private static double map(double x, double inMin, double inMax, double outMin, double outMax) {
        // this function is the spicy sauce
        // https://www.arduino.cc/reference/en/language/functions/math/map/
        return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new LiftHoldCommand());
    }*/
}