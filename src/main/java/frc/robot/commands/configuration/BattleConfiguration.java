
package frc.robot.commands.configuration;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.EncoderDrive;
import frc.robot.commands.lift.SetLiftPosition;
import frc.robot.commands.wrist.SetWristAngle;

public class BattleConfiguration extends CommandGroup {
    public BattleConfiguration() {
        addParallel(new SetLiftPosition(0.2)); // FIXME: add in real values
        addParallel(new SetWristAngle(100)); // pointing down
    }
}