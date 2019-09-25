
package frc.robot.commands.configuration;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.lift.SetLiftPosition;
import frc.robot.commands.wrist.SetWristAngle;
//import frc.robot.commands.auto.EncoderDrive;

public class CargoScoreConfiguration extends CommandGroup {
    public CargoScoreConfiguration() {
        addParallel(new SetLiftPosition(0.5)); // FIXME: add in real values
        addParallel(new SetWristAngle(100));
    }
}