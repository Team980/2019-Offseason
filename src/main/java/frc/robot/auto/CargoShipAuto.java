/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.DriveUntilLevelSurface;
import frc.robot.commands.auto.IMUTurn;
import frc.robot.commands.auto.TimedDrive;

public class CargoShipAuto extends CommandGroup {
  
  public CargoShipAuto() {
    addSequential(new TimedDrive(0.75, 1));
    addSequential(new IMUTurn(-30));
  }
}
