/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.subsystems.Lift;
//import frc.robot.commands.drive.TrophyTruckRetract;
//import frc.robot.commands.drive.TrophyTruckDeploy;


public class SetLiftPosition extends Command {

	//private Lift lift;

	private double targetPosition;

	public SetLiftPosition(double targetPosition) {
		this.targetPosition = targetPosition;


		//lift = Robot.lift;		
		requires(Robot.lift);
	}
	


	@Override
	protected void initialize() {
		// if (targetPosition < 0.15) {
		// 	Robot.robotMap.trophyTruckSolenoid.set(true);
		// } else {
		// 	Robot.robotMap.trophyTruckSolenoid.set(false);
		// }
	}

	@Override
	protected void execute() {
		Robot.lift.moveTowards(targetPosition);
	}

	@Override
	protected boolean isFinished() {
		return Robot.lift.isAtTargetPosition(targetPosition);
	}

	@Override
	protected void interrupted() {
		Robot.lift.stopMotors();//something bad happened, kill the motor
   }
		 @Override
	protected void end() {
		Robot.lift.set(0);//set motor to 0 before handing off to HoldLift
	}
}
