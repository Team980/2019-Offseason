/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The limelight camera. This extends subsystem because there is only one limelight on the robot and it keeps state; ie, one
 * pipeline can be active at once.
 */
public class Limelight {
    public static final double BALL_TARGET_PIPELINE_INDEX = 0;
    public static final double VISION_TARGET_PIPELINE_INDEX = 1;

    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;
    private NetworkTableEntry tv;
    private NetworkTableEntry pipelineIndex;

    public Limelight(){
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        tv = table.getEntry("tv");
        pipelineIndex = table.getEntry("pipeline");
    }

    public void setPipelineIndex(double index) {
        pipelineIndex.setDouble(index);
    }

    public boolean hasTarget() {
        return tv.getDouble(0.0) == 1; // is 1 when there is a valid target
    }

    public double getTargetX(){
        return tx.getDouble(0.0);
    }

    public double getTargetY(){
        return ty.getDouble(0.0);
    }

    public double getTargetArea(){
        return ta.getDouble(0.0);
    }
}