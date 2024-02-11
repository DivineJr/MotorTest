// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

	private MyMotor motor1;
	private MyMotor motor2;
	private MyMotor motor3;
	private MyMotor motor4;
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any
	 * initialization code.
	 */
	@Override
	public void robotInit() {
		motor1 = new MyMotor(40);
		motor2 = new MyMotor(41);
		motor3 = new MyMotor(42);
	}

	@Override
	public void robotPeriodic() {
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	// Just the bunch of variables used by the motor test program

	// private MyMotor[] motorArray = new MyMotor[]{motor1, motor2, motor3, motor4};

	@Override
	public void teleopInit() {

		// motor4 = new MyMotor(0);

		// motor1.getMotor().restoreFactoryDefaults();
		// motor1.getMotor().clearFaults();
		// motor2.getMotor().restoreFactoryDefaults();
		// motor2.getMotor().clearFaults();
		// motor3.getMotor().restoreFactoryDefaults();
		// motor3.getMotor().clearFaults();
		// motor4.getMotor().restoreFactoryDefaults();
		// motor4.getMotor().clearFaults();

		// motor2.setInverted(true);
		// motor3.setInverted(false);
		// motor3.setFollower(41);

		// SmartDashboard.putNumber("Motor 1 CAN", 0.0);
		// SmartDashboard.putNumber("Motor 2 CAN", 0.0);
		// SmartDashboard.putNumber("Motor 3 CAN", 0.0);
		// SmartDashboard.putNumber("Motor 4 CAN", 0.0);

		SmartDashboard.putNumber("Motor 1 Power", 0.0);
		SmartDashboard.putNumber("Motor 2 Power", 0.0);
		SmartDashboard.putNumber("Motor 3 Power", 0.0);
		// SmartDashboard.putNumber("Motor 4 Power", 0.0);

		SmartDashboard.putBoolean("Motor 1 Toggle", false);
		SmartDashboard.putBoolean("Motor 2 Toggle", false);
		SmartDashboard.putBoolean("Motor 3 Toggle", false);
		// SmartDashboard.putBoolean("Motor 4 Toggle", false);
	}

	public int smartDashboardGetInteger(String key) {
		return Integer.parseInt(Long.toString(Math.round(SmartDashboard.getNumber(key, 0))));
	}

	boolean toggleFlag = false;

	@Override
	public void teleopPeriodic() {
		// motor1.setCAN(smartDashboardGetInteger("Motor 1 CAN"));
		// motor2.setCAN(smartDashboardGetInteger("Motor 2 CAN"));
		// motor3.setCAN(smartDashboardGetInteger("Motor 3 CAN"));
		// motor4.setCAN(smartDashboardGetInteger("Motor 4 CAN"));

		if(SmartDashboard.getBoolean("Motor 2 Toggle", toggleFlag) != toggleFlag) toggleFlag = !toggleFlag;
		SmartDashboard.putBoolean("Toggler", toggleFlag);

		if (SmartDashboard.getBoolean("Motor 1 Toggle", false)) {
			motor1.setPower(SmartDashboard.getNumber("Motor 1 Power", 0.0));
		} else { 
			motor1.stopMotor();
		}
		if (SmartDashboard.getBoolean("Motor 2 Toggle", toggleFlag)) {
			motor2.setPower(SmartDashboard.getNumber("Motor 2 Power", 0.0));
		} else {
			motor2.stopMotor();
		}
		if (SmartDashboard.getBoolean("Motor 3 Toggle", true)) {
			motor3.setPower(SmartDashboard.getNumber("Motor 3 Power", 0.0));
		} else {
			motor3.stopMotor();
		}
		// }
		// if (SmartDashboard.getBoolean("Motor 4 Toggle", false)) {
		// 	motor4.setPower(SmartDashboard.getNumber("Motor 4 Power", 0.0));
		// } else {
		// 	motor4.stopMotor();
		// }
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
	}

	@Override
	public void testInit() {
	}

	@Override
	public void testPeriodic() {
	}

	@Override
	public void simulationInit() {
	}

	@Override
	public void simulationPeriodic() {
	}
}
