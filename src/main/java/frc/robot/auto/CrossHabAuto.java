/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.DriveUntilLevelSurface;
import frc.robot.commands.auto.TimedDrive;
import frc.robot.commands.drive.TelopDrive;

public class CrossHabAuto extends CommandGroup {
    public CrossHabAuto() {
        addSequential(new TimedDrive(0.75, 1));
        addSequential(new TelopDrive());
        //addSequential((new DriveUntilLevelSurface(0.4))); TODO: this never stopped
    }
}
