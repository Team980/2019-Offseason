/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManualLiftControl extends Command {

    public ManualLiftControl() {
         requires(Robot.lift);
    }

    @Override
    public void initialize() {
        Robot.pidLift.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.debugTable.getEntry("lift control speed").setNumber(Robot.lift.getSpeed());
        Robot.lift.rawSet(Robot.oi.getLiftJoystickValue());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

  	// Called when another command which requires one or more of the same
  	// subsystems is scheduled to run
  	@Override
 	protected void interrupted() {
        Robot.lift.stopMotors();
        Robot.pidLift.enable();
    }
    
    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.lift.stopMotors();
        Robot.pidLift.enable();
    }
}
