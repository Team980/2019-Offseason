/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class DisableRobot extends InstantCommand {
  /**
   * Add your docs here.
   */
  public DisableRobot() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveSystem);
    requires(Robot.pidPositionalLift);
    requires(Robot.pidWrist);
    requires(Robot.pidLift);

  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.driveSystem.stopMotors();
    Robot.pidPositionalLift.stopMotors();
    Robot.pidWrist.stopMotors();
    Robot.pidLift.stopMotors();
  }

}
