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

  private static final double DEADBAND = 7;

  private double minWristSpeed = 0.35;// minSpeed for P only control

  // the minimum angles so we don't crash into ourselves
  private static final double MINIMUM_ANGLE = 30; 
  private static final double MAXIMUM_ANGLE = 288;

  /**
   * Add your docs here.
   */
  public PIDWrist() {
    // Intert a subsystem name and PID values here
    super("SubsystemName", .005 , 0, 0);
    wristMotor = Robot.robotMap.wristMotor;
    wristPot = Robot.robotMap.wristPotentiometer;
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
    enable();
    setInputRange(MINIMUM_ANGLE , MAXIMUM_ANGLE);
    setAbsoluteTolerance(DEADBAND);
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

//non PID control option

  public void rawSet(double input) {
		wristMotor.set(input);//manual override
  }
  
	public void set(double input) {
    if ((input < 0 && currentAngle() > MINIMUM_ANGLE) || (input > 0 && currentAngle() < MAXIMUM_ANGLE)) {
      wristMotor.set(input);
    } // the softstop check needs to be here where the motor is running, this way both manual and automation use the soft stop protection

   else {
      wristMotor.set(0);
    }
 }

 public void moveTowards(double targetAngle) {
       double difference = targetAngle - currentAngle();

       double input = 1.5 * difference / 260 ; // figure out which velocity we want to be

       if (difference > 0 && input < minWristSpeed){
       input = minWristSpeed;
      }
      else if (difference < 0 && input > -minWristSpeed){
       input = -minWristSpeed;
      }

    if (isAtTargetAngle(targetAngle)) {
      input = 0;
   } 
    
    set(input);
 }

 public boolean isAtTargetAngle(double targetAngle) {
   double distance = Math.abs(targetAngle - currentAngle());
   Robot.debugTable.getEntry("distance").setNumber(distance);

   return distance < DEADBAND;
 }

 public double currentAngle() {
   return wristPot.getAngle();
 }

 public void stopMotors() {
   if (Robot.oi.getEnablePIDWrist()){
    disable();
   }
   wristMotor.set(0);
 }

}
