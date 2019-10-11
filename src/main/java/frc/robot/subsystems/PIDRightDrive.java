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

/**
 * Add your docs here.
 */
public class PIDRightDrive extends PIDSubsystem {
  private SpeedController rightDrive;
  private Encoder rightEncoder;
  /**
   * Add your docs here.
   */
  public PIDRightDrive() {
    // Intert a subsystem name and PID values here
    super("PIDRightDrive", .08 , 0 , 0 , 0);
    rightDrive = Robot.robotMap.rightDrive;
    rightEncoder = Robot.robotMap.rightDriveEncoder;
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
    setInputRange(-17.0, 17.0);
    setAbsoluteTolerance(0.01);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return rightEncoder.getRate();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    rightDrive.set(output);
  }
}
