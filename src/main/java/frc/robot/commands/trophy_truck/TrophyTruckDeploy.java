/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.trophy_truck;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;


public class TrophyTruckDeploy extends InstantCommand {
    public static final boolean ON_STATE = true;

    Solenoid trophyTruckSolenoid;
    
    public TrophyTruckDeploy() {
        super();

        //trophyTruckSolenoid = Robot.robotMap.trophyTruckSolenoid; UNDO
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        trophyTruckSolenoid.set(ON_STATE);
    }
}
