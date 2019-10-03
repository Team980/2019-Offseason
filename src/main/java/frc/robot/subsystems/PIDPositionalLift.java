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
public class PIDPositionalLift extends PIDSubsystem {
  private SpeedController liftMotor;
  private Encoder liftEncoder;

  /**
   * Add your docs here.
   */
  public PIDPositionalLift() {
    // Intert a subsystem name and PID values here
    super("SubsystemName", 1, 0, 0);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
    liftMotor = Robot.robotMap.liftMotor;
    liftEncoder = Robot.robotMap.liftEncoder;

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new HoldLift());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return Robot.robotMap.liftEncoder.getDistance();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    Robot.robotMap.liftMotor.set(output);
  }

  public void stopMotors() {
    disable();
    liftMotor.set(0);
	}

}
