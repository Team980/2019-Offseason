/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.Robot;
import frc.robot.commands.DriveCommand;

/**
 * Add your docs here.
 */
public class DriveSystem extends Subsystem {
  private SpeedControllerGroup leftDrive;
  private Encoder leftEncoder;
  private PIDController leftController;

  private SpeedControllerGroup rightDrive;
  private Encoder rightEncoder;
  private PIDController rightController;

  private Solenoid shifterSolenoid;

  private boolean isAutoShiftEnabled = false;

  public DriveSystem() {
      leftDrive = Robot.robotMap.leftDrive;
      //leftDrive.setName("Drive System", "Left Speed Controllers");

      leftEncoder = Robot.robotMap.leftEncoder;
      //leftEncoder.setName("Drive System", "Left Encoder");
      LiveWindow.add(leftEncoder);

      leftController = Robot.robotMap.leftController;
      //leftController.setName("Drive System", "Left PID Controller");
      LiveWindow.add(leftController);

      rightDrive = Robot.robotMap.rightDrive;
      //rightDrive.setName("Drive System", "Right Speed Controllers");

      rightEncoder = Robot.robotMap.rightEncoder;
      //rightEncoder.setName("Drive System", "Right Encoder");

      rightController = Robot.robotMap.rightController;
      //rightController.setName("Drive System", "Right PID Controller");
      LiveWindow.add(rightController);

      shifterSolenoid = Robot.robotMap.shifterSolenoid;
      //shifterSolenoid.setName("Drive System", "Shifter Solenoid");
  }

  public Encoder getLeftEncoder() {
      return leftEncoder;
  }

  public Encoder getRightEncoder() {
      return rightEncoder;
  }

  public void resetEncoders() {
      leftEncoder.reset();
      rightEncoder.reset();
  }

  public Gear getGear() {
      return (shifterSolenoid.get() == Gear.LOW.solenoidValue) ? Gear.LOW : Gear.HIGH;
  }

  public void setGear(boolean gear) {
      shifterSolenoid.set(gear);
  }

  public boolean isPIDEnabled() {
      return leftController.isEnabled() && rightController.isEnabled();
  }

  public void setPIDEnabled(boolean isPIDEnabled) {
      leftController.setEnabled(isPIDEnabled);
      rightController.setEnabled(isPIDEnabled);
  }

  public boolean isAutoShiftEnabled() {
      return isAutoShiftEnabled;
  }

  public void setAutoShiftEnabled(boolean isAutoShiftEnabled) {
      this.isAutoShiftEnabled = isAutoShiftEnabled;
  }

  /**
   * Separated so it can run in all control modes
   */
  private void runAutoShift() {
      if (isAutoShiftEnabled) {
          if (Math.abs(leftEncoder.getRate()) > 4.5 && Math.abs(rightEncoder.getRate()) > 4.5) {
              setGear(false);

              leftController.setPID(.03, 0, 0, 0);
              rightController.setPID(.03, 0, 0, 0);
          } else if (Math.abs(leftEncoder.getRate()) < 4.0 && Math.abs(rightEncoder.getRate()) < 4.0) {
              setGear(true);

              leftController.setPID(.1, 0, 0, 0);
              rightController.setPID(.1, 0, 0, 0);
          }
      }
  }

  /**
   * @param left  Requested left speed, scaled
   * @param right Requested right speed, scaled
   */
  public void setSetpoints(double left, double right) {
      leftController.setSetpoint(left);
      rightController.setSetpoint(right);

      runAutoShift();
  }

  /**
   * @param left         Requested left drive command, from -1 to 1
   * @param right        Requested right drive command, from -1 to 1
   * @param squareInputs If set, decreases the input sensitivity at low speeds.
   */
  public void tankDrive(double left, double right, boolean squareInputs) {
      //left = limit(left);
      left = applyDeadband(left, .1);

      //right = limit(right);
      right = applyDeadband(right, .1);

      if (squareInputs) {
          left = Math.copySign(left * left, left);
          right = Math.copySign(right * right, right);
      }

      if (isPIDEnabled()) {
          leftController.setSetpoint(left * 17);
          rightController.setSetpoint(right * 17);
      } else {
          leftDrive.set(left);
          rightDrive.set(right);
      }

      runAutoShift();
  }

  /**
   * @param move         Requested move command, from -1 to 1
   * @param turn         Requested turn command, from -1 to 1
   * @param squareInputs If set, decreases the input sensitivity at low speeds.
   */
  public void arcadeDrive(double move, double turn, boolean squareInputs) {
      //move = limit(move);
      move = applyDeadband(move, .1);

      //turn = limit(turn);
      turn = applyDeadband(turn, .05);

      if (squareInputs) {
          move = Math.copySign(move * move, move);
          turn = Math.copySign(turn * turn, turn);
      }

      double left;
      double right;

      var maxInput = Math.copySign(Math.max(Math.abs(move), Math.abs(turn)), move);

      if (move >= 0.0) {
          // First quadrant, else second quadrant
          if (turn >= 0.0) {
              left = maxInput;
              right = move - turn;
          } else {
              left = move + turn;
              right = maxInput;
          }
      } else {
          // Third quadrant, else fourth quadrant
          if (turn >= 0.0) {
              left = move + turn;
              right = maxInput;
          } else {
              left = maxInput;
              right = move - turn;
          }
      }

      if (isPIDEnabled()) {
          leftController.setSetpoint(left * 17);
          rightController.setSetpoint(right * 17);
      } else {
          leftDrive.set(left);
          rightDrive.set(right);
      }

      runAutoShift();
  }

  /**
   * Limit motor values to the -1.0 to +1.0 range.
   */
  private double limit(double value) {
      if (value > 1.0) {
          return 1.0;
      }
      if (value < -1.0) {
          return -1.0;
      }
      return value;
  }

  /**
   * Returns 0.0 if the given value is within the specified range around zero. The remaining range
   * between the deadband and 1.0 is scaled from 0.0 to 1.0.
   *
   * @param value    value to clip
   * @param deadband range around zero
   */
  private double applyDeadband(double value, double deadband) {
      if (Math.abs(value) > deadband) {
          if (value > 0.0) {
              return (value - deadband) / (1.0 - deadband);
          } else {
              return (value + deadband) / (1.0 - deadband);
          }
      } else {
          return 0.0;
      }
  }

  public void disable() {
      setSetpoints(0, 0);
      setPIDEnabled(false);

      leftController.reset();
      rightController.reset();

      leftDrive.stopMotor();
      rightDrive.stopMotor();
  }

  public enum Gear {
      LOW(true),
      HIGH(false);

      private boolean solenoidValue;

      Gear(boolean solenoidValue) {
          this.solenoidValue = solenoidValue;
      }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveCommand());
  }
}
