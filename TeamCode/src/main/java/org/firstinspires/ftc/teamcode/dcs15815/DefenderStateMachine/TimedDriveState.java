package org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotMotion;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotPosition;

public class TimedDriveState extends DefenderState {
	private boolean stopAfterDriving = true;
	private DefenderBotMotion motion;
	private double powerFactor = 1;
	private double powerOverride = 0;
	private long driveTime;
	private ElapsedTime timer;

	public TimedDriveState(DefenderBotMotion m, long t) {
		super();
		motion = m;
		driveTime = t;

		timer = new ElapsedTime();
	}

	public TimedDriveState(double f, double s, double r, long t) {
		super();
		motion = new DefenderBotMotion(f, s, r);
		driveTime = t;

		timer = new ElapsedTime();
	}

	public TimedDriveState(String configName, long t) {
		super();
		try {
			motion = (DefenderBotMotion) BuzzBotConfiguration.class.getField(configName).get(BuzzBotConfiguration.class);
			driveTime = t;
			timer = new ElapsedTime();
		} catch (Exception e) {
			System.out.println("Could not find that DefenderBotMotion.");
		}

	}

	public TimedDriveState(String motionConfigName, String driveTimeConfigName) {
		super();
		try {
			motion = (DefenderBotMotion) BuzzBotConfiguration.class.getField(motionConfigName).get(BuzzBotConfiguration.class);
			driveTime = (long) BuzzBotConfiguration.class.getField(driveTimeConfigName).get(BuzzBotConfiguration.class);
			timer = new ElapsedTime();
		} catch (Exception e) {
			System.out.println("Could not find that DefenderBotMotion or DriveTime.");
		}

	}



	public static TimedDriveState make(DefenderBotMotion m, long t) {
		return new TimedDriveState(m, t);
	}

	public static TimedDriveState make(double f, double s, double r, long t) {
		return new TimedDriveState(f, s, r, t);
	}

	public static TimedDriveState make(String motionConfigName, String driveTimeConfigName) {
		return new TimedDriveState(motionConfigName, driveTimeConfigName);
	}


	public static TimedDriveState make(String configName, long t) {
		return new TimedDriveState(configName, t);
	}

	public static TimedDriveState moving(String configName, long t) {
		return new TimedDriveState(configName, t);
	}

	public static TimedDriveState moving(DefenderBotMotion m, long t) {
		return new TimedDriveState(m, t);
	}

	@Override
	public void beforeStart() {
		timer.reset();
	}



	@Override
	public void run() {
		BuzzBot bot = (BuzzBot) stateMachine.bot;

		if (stateMachine.isDebugging) {
			if (stateLabel != null) {
				stateMachine.opMode.telemetry.addData("Driving: ", powerFactor);
			}
//			stateMachine.opMode.telemetry.addData("PF: ", powerFactor);
//			stateMachine.opMode.telemetry.addData("PO: ", powerOverride);
		}

		if (timer.milliseconds() > driveTime) {
			bot.drivetrain.stopDriving();
			isFinished = true;
		} else {
			bot.drivetrain.driveNoProportional(-1 * motion.getForward(), motion.getStrafe(), motion.getRotate());
		}
	}
}