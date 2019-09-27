/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;
import frc.robot.commands.wrist.HoldWrist;
import frc.robot.sensors.Potentiometer;

/**
 * Add your docs here.
 */
public class PIDWrist extends PIDSubsystem {
  private SpeedController wristMotor;
  private Potentiometer wristPot;
  /**
   * Add your docs here.
   */
  public PIDWrist() {
    // Intert a subsystem name and PID values here
    super("SubsystemName", 1, 0, 0);
    wristMotor = Robot.robotMap.wristMotor;
    wristPot = Robot.robotMap.wristPotentiometer;
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
    enable();
    setInputRange(30 , 288);
    setAbsoluteTolerance(2);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new HoldWrist());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return wristPot.getAngle();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    wristMotor.set(output);
  }
}
