package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomousSquare;
//import frc.robot.commands.AutoLiftCommand;
//import frc.robot.commands.LiftManualCommand;
import frc.robot.subsystems.DriveSystem;
//import frc.robot.subsystems.LiftSystem;


public class Robot extends TimedRobot {
  public static OI oi;

  public static RobotMap robotMap;
  //public static LiftSystem liftSystem;
  public static DriveSystem driveSystem;

  Command AutoDriveCommand;
  CommandGroup autonomousSquare;
  //Command AutoLiftCommand;
  //Command LiftHoldCommand;

  @Override
  public void robotInit() {
    robotMap = new RobotMap();
    driveSystem = new DriveSystem();
    oi = new OI();

 }

  @Override
  public void robotPeriodic() {
    robotMap.imu.getYawPitchRoll(robotMap.ypr);
    SmartDashboard.putNumber("left Encoder distance", driveSystem.getLeftEncoder().getDistance());
    //dont put anything in here
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }


  @Override
  public void autonomousInit() {
    autonomousSquare = new AutonomousSquare();
    autonomousSquare.start();

    //AutoLiftCommand = new AutoLiftCommand(50);
    //AutoLiftCommand.start();


  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if(autonomousSquare != null){
      autonomousSquare.cancel();
    }
    /*if(AutoLiftCommand != null)
    {
      AutoLiftCommand.cancel();
    }
    //AutoDriveCommand = new AutoDriveCommand();

    LiftManualCommand teleopLift = new LiftManualCommand();
    teleopLift.start();*/

    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

}
