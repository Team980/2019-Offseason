/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;

public class ManualLiftControl extends Command {

    private Lift lift;
    private OI oi;
    Timer t1;
   
    private double lastUpdated;


    public ManualLiftControl() {
        lift = Robot.lift;
        oi = Robot.oi;
       
        requires(lift);
    }

    @Override
    public void initialize() {
        t1 = new Timer();
        t1.start();
        lastUpdated = t1.get();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() 
    {
       if(t1.get() - lastUpdated > 0.01)//if it was greater than 10 milliseconds since last call
       {
            if(lift.targetPosition < 1 && Math.signum(oi.getLiftJoystickValue()) > 0)
            {
                lift.targetPosition +=0.01;
            }
            else if (lift.targetPosition > 0 && Math.signum(oi.getLiftJoystickValue()) < 0)
            {
                lift.targetPosition -= 0.01;
            }
            lastUpdated = t1.get();
        }
        


       // lift.rawSet(oi.getLiftJoystickValue());
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
