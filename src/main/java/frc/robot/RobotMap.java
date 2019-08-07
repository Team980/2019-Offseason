/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase;

public class RobotMap {

	public WPI_VictorSPX liftMotor;
	public Encoder liftEncoder;

    public RobotMap() {
		liftMotor = new WPI_VictorSPX(11);
		liftEncoder = new Encoder(10, 12, false, CounterBase.EncodingType.k4X);
	}

}
