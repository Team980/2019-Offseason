/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;
import frc.robot.commands.lift.HoldLift;

/**
 * Add your docs here.
 */
public class PIDLift extends PIDSubsystem {
  private static final double EXCLUSION_MIN = 0.03;//TODO: get actual distances in ft
	private static final double EXCLUSION_MAX = 0.97;
	private double minSpeedUp = .35;//used directly, do not convert
	private double minSpeedDown = -.15;
	private double maxLiftSpeed = 5; //TODO: get lift max speed ft/s
	private double fullLiftDistance = 4;//TODO: get the full range of lift motion in ft
  private static final double DEADBAND = 0.04;//TODO: use distance values for deadband

	// private static final double ENCODER_MIN_TICK_COUNT = -20_000;

  private SpeedController liftMotor;
  private Encoder liftEncoder;
	
	public PIDLift() {//Velocity controlled lift
    // Intert a subsystem name and PID values here
    super("SubsystemName", 1, 0, 0);

    liftMotor = Robot.robotMap.liftMotor;
    liftEncoder = Robot.robotMap.liftEncoder;
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system to
    enable();// - Enables the PID controller.
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new HoldLift());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return liftEncoder.getRate();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    liftMotor.set(output);
  }


	//no velocity control option, but velocity control uses a lot of these methods as well
  public void rawSet(double input) {
		liftMotor.set(input); // manual override
	}

	public void set(double input) { 
		if ((input < 0 && currentPosition() > EXCLUSION_MIN) || (input > 0 && currentPosition() < EXCLUSION_MAX)) {
			liftMotor.set(input);
		} 
		else {
			liftMotor.set(0);
		}
  }
  
	public void PIDSet(double input) { 
		if ((input < 0 && currentPosition() > EXCLUSION_MIN) || (input > 0 && currentPosition() < EXCLUSION_MAX)) {
			setSetpoint(input * maxLiftSpeed);
		} 
		else {
			setSetpoint(0);
		}
	}

	public boolean isAtTargetPosition(double targetPosition) {
		double distance = Math.abs(targetPosition - currentPosition());
		return distance < DEADBAND;
	}

	public void moveTowards(double targetPosition) {
		double distance = targetPosition - currentPosition();

		double input;
		if (isAtTargetPosition(targetPosition)) {
			input = 0;
		} 
		else if (distance <= 0 && (distance > minSpeedDown)){
			input = minSpeedDown;
		}
		else if (distance > 0 && (distance < minSpeedUp)){
			input = minSpeedUp;
		}
		else {
			input = distance / fullLiftDistance;
		}
    if (Robot.oi.getEnablePIDLift()){
      PIDSet(input);
    }
    else{
      set(input);
    }
		
	}
	public double getSpeed() {
		return liftEncoder.getRate();
	}

	public double currentPosition() {
		return liftEncoder.getDistance(); 
	} 

	public void stopMotors() {
    if (Robot.oi.getEnablePIDLift()){
     disable();
    }
     liftMotor.set(0);
	}

}