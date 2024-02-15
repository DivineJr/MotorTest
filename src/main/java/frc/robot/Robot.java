// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.List;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
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

	// MyMotor objects
	private MyMotor motor1;
	private MyMotor motor2;
	private MyMotor motor3;
	private MyMotor motor4;

	// Keeps track of the previous state, since if somehow contact with Shuffleboard
	// or Smartdashboard is lost it doesn't just skip around, and updates on next
	// check, and if you really do need the motors to stop, you can always disable
	// or turn off the power on the robot. Essentially trades safety with
	// operability.
	private boolean toggleFlag1;
	private boolean toggleFlag2;
	private boolean toggleFlag3;
	private boolean toggleFlag4;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any
	 * initialization code.
	 */
	@Override
	public void robotInit() {
		motor1 = new MyMotor(0);
		motor2 = new MyMotor(0);
		motor3 = new MyMotor(0);
	}

	@Override
	public void robotPeriodic() {
	}

	// Using Autonomous Init to add things to shuffleboard, it will most definitely crash if the things  are already there but that's an issue for future me to deal with, and what I'm testing to see if I can get around anyways 
	@Override
	public void autonomousInit() {
		ShuffleboardTab theTab = Shuffleboard.getTab("Test Tab");
		GenericEntry entry1 = theTab.addPersistent("Persistent Test", 0.0).getEntry();
		GenericEntry entry2 = theTab.add("String Test", "a string").getEntry();
		GenericEntry entry3 = theTab.add("Number Test", 1004).getEntry();
	}

	@Override
	public void autonomousPeriodic() {
	}

	// Using teleop init to read things off the shuffleboard without actually having the variables to see if I can test to see if a thing is on the shuffleboard or not
	@Override
	public void teleopInit() {
		ShuffleboardTab theTab = Shuffleboard.getTab("Test Tab");

		List<ShuffleboardComponent<?>> test = theTab.getComponents();

		SimpleWidget test2 = (SimpleWidget)test.get(1);

		System.out.println(test2.getEntry().getString("Nothing!"));
		
		// Note: HOLY CRAP IT WORKED NO WAY
		//		 so in the future, likelihood is that this would get whatever was added second, which does not always line up to what's in the code, so I'll have to make some sort of automated checker for this
		//       anyways, this is very cool :)
	}

	/**
	 * This this is so, so ugly, but it works.
	 * There is definitely a better way to do this, no doubt in my mind, but this is
	 * what works right now sooooo
	 * 
	 * @param key Key of the number to be interpreting
	 * @return Integer version of the read number
	 */
	public int smartDashboardGetInteger(String key) {
		return Integer.parseInt(Long.toString(Math.round(SmartDashboard.getNumber(key, 0))));
	}

	@Override
	public void teleopPeriodic() {
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
	}

	@Override
	public void testInit() {
		motor1.setCAN(0);
		motor2.setCAN(0);
		motor3.setCAN(0);
		motor4.setCAN(0);

		// motor1.getMotor().restoreFactoryDefaults();
		// motor1.getMotor().clearFaults();
		// motor2.getMotor().restoreFactoryDefaults();
		// motor2.getMotor().clearFaults();
		// motor3.getMotor().restoreFactoryDefaults();
		// motor3.getMotor().clearFaults();
		// motor4.getMotor().restoreFactoryDefaults();
		// motor4.getMotor().clearFaults();

		SmartDashboard.putNumber("Motor 1 CAN", 0.0);
		SmartDashboard.putNumber("Motor 2 CAN", 0.0);
		SmartDashboard.putNumber("Motor 3 CAN", 0.0);
		SmartDashboard.putNumber("Motor 4 CAN", 0.0);

		SmartDashboard.putNumber("Motor 1 Power", 0.0);
		SmartDashboard.putNumber("Motor 2 Power", 0.0);
		SmartDashboard.putNumber("Motor 3 Power", 0.0);
		SmartDashboard.putNumber("Motor 4 Power", 0.0);

		SmartDashboard.putBoolean("Motor 1 Toggle", false);
		SmartDashboard.putBoolean("Motor 2 Toggle", false);
		SmartDashboard.putBoolean("Motor 3 Toggle", false);
		SmartDashboard.putBoolean("Motor 4 Toggle", false);
	}

	@Override
	public void testPeriodic() {

		// Normally setting the CAN over and over is a terrible idea and you should
		// never do it, but the MyMotor class takes care of it for me and makes it so
		// it's okay
		motor1.setCAN(smartDashboardGetInteger("Motor 1 CAN"));
		motor2.setCAN(smartDashboardGetInteger("Motor 2 CAN"));
		motor3.setCAN(smartDashboardGetInteger("Motor 3 CAN"));
		motor4.setCAN(smartDashboardGetInteger("Motor 4 CAN"));

		// sets state of toggleFlag
		if (SmartDashboard.getBoolean("Motor 1 Toggle", toggleFlag1) != toggleFlag1)
			toggleFlag1 = !toggleFlag1;
		if (SmartDashboard.getBoolean("Motor 2 Toggle", toggleFlag2) != toggleFlag2)
			toggleFlag2 = !toggleFlag2;
		if (SmartDashboard.getBoolean("Motor 3 Toggle", toggleFlag3) != toggleFlag3)
			toggleFlag3 = !toggleFlag3;
		if (SmartDashboard.getBoolean("Motor 4 Toggle", toggleFlag4) != toggleFlag4)
			toggleFlag4 = !toggleFlag4;

		// sends toggleFlag to SmartDashboard
		SmartDashboard.putBoolean("Toggler1", toggleFlag1);
		SmartDashboard.putBoolean("Toggler2", toggleFlag2);
		SmartDashboard.putBoolean("Toggler3", toggleFlag3);
		SmartDashboard.putBoolean("Toggler4", toggleFlag4);

		// Actual motor logic

		// Turn off if the toggle is FALSE
		// Turn on and use the set power if the toggle is true
		if (SmartDashboard.getBoolean("Motor 1 Toggle", toggleFlag1)) {
			motor1.setPower(SmartDashboard.getNumber("Motor 1 Power", 0.0));
		} else {
			motor1.stopMotor(); // Pretty much the same as .set(0.0)
		}
		if (SmartDashboard.getBoolean("Motor 2 Toggle", toggleFlag2)) {
			motor2.setPower(SmartDashboard.getNumber("Motor 2 Power", 0.0));
		} else {
			motor2.stopMotor();
		}
		if (SmartDashboard.getBoolean("Motor 3 Toggle", toggleFlag3)) {
			motor3.setPower(SmartDashboard.getNumber("Motor 3 Power", 0.0));
		} else {
			motor3.stopMotor();
		}
		if (SmartDashboard.getBoolean("Motor 4 Toggle", toggleFlag4)) {
			motor4.setPower(SmartDashboard.getNumber("Motor 4 Power", 0.0));
		} else {
			motor4.stopMotor();
		}

		/* 	FYI, .set(0.0) and .stopMotor() both idle the motor and it uses its set idle
			behavior.
			You have to use .setIdleBehavior in order to set it to the idle mode
			appropriate for the situation.
			For the longevity of the robot and the motors, if you can justify using
			coast, use coast whenever you can.
		*/
	}

	@Override
	public void simulationInit() {
	}

	@Override
	public void simulationPeriodic() {
	}
}
