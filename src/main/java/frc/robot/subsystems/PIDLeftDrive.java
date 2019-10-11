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
public class PIDLeftDrive extends PIDSubsystem {
  private SpeedController leftDrive;
  private Encoder leftEncoder;

  private double maxVelocity = 17;
  /**
   * Add your docs here.
   */
  public PIDLeftDrive() {
    // Intert a subsystem name and PID values here
    super("PIDLeftDrive", .08, 0, 0 ,0);

    leftDrive = Robot.robotMap.leftDrive;
    leftEncoder = Robot.robotMap.leftDriveEncoder;
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
    setInputRange(-maxVelocity, maxVelocity);
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
    return leftEncoder.getRate();
    
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    leftDrive.set(output);
  }

  public double getMaxVelocity(){
    return maxVelocity;
  }
}
