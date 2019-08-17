/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;

public class IncrementLiftPosition extends Command {

	private Lift lift;
  private double targetPosition;
  private double increment;
  private OI oi;

  public IncrementLiftPosition(double increment) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    lift = Robot.lift;
    oi = Robot.oi;

    this.increment = increment;

    requires(lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    targetPosition = lift.currentPosition() + increment;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    lift.moveTowards(targetPosition);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
		return lift.isAtTargetPosition(targetPosition);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    lift.stopMotors();
  }
}
