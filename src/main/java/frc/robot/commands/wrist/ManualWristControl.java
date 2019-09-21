/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Wrist;

public class ManualWristControl extends Command {

    private Wrist wrist;
    private OI oi;

    public ManualWristControl() {
        wrist = Robot.wrist;
        oi = Robot.oi;

        requires(wrist);
    }

    @Override
    protected void initialize() {
        //wrist.setPidEnabled(false);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double input = oi.getWristJoystickValue();
        wrist.rawSet(input/3);
        //Robot.debugTable.getEntry("wrist speed").setNumber(wrist.getVelocity());
        //wrist.set(-oi.getWristJoystickValue()); TODO: add back soft stops
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
		wrist.stopMotors();
	}
	  
    // Called once after isFinished returns true
    @Override
    protected void end() {
        //wrist.setPidEnabled(true);
        wrist.stopMotors();
    }
}
