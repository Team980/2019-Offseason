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

public class ManualLiftControl extends Command {

    private Lift lift;
    private OI oi;

    public ManualLiftControl() {
        lift = Robot.lift;
        oi = Robot.oi;

        requires(lift);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.debugTable.getEntry("lift control speed").setNumber(lift.getSpeed());
        lift.rawSet(oi.getLiftJoystickValue());
        //lift.set(oi.getLiftJoystickValue()); TODO: add back in soft stops
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        lift.stopMotors();
    }
}
