/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;


public class TrophyTruckRetract extends InstantCommand {
    public static final boolean OFF_STATE = false;

    Solenoid trophyTruckSolenoid;
    
    public TrophyTruckRetract() {
        super();

        trophyTruckSolenoid = Robot.robotMap.trophyTruckSolenoid;
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        trophyTruckSolenoid.set(OFF_STATE);
    }
}
