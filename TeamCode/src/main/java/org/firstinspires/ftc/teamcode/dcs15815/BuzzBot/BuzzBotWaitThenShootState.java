package org.firstinspires.ftc.teamcode.dcs15815.BuzzBot;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DefenderState;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous.BuzzBotAutonomousOpMode;

public class BuzzBotWaitThenShootState extends DefenderState {
	private ElapsedTime timer;
	private boolean timerHasBeenReset = false;

	public double boost = 0;

	public BuzzBotWaitThenShootState() {
		super();
		timer = new ElapsedTime();
	}

	public static BuzzBotWaitThenShootState make() {
		return new BuzzBotWaitThenShootState();
	}

	@Override
	public void beforeStart() {
		timer.reset();
	}

	public BuzzBotWaitThenShootState setBoost(double n) {
		boost = n;
		return this;
	}

	@Override
	public void run() {
		BuzzBot bot = (BuzzBot)stateMachine.bot;
		DefenderAlliance.Color allianceColor = DefenderAlliance.getInstance().getColor();

		if (!timerHasBeenReset) {
			timer.reset();
			timerHasBeenReset = true;
		}

		if (stateMachine.isDebugging) {
			stateMachine.opMode.telemetry.addData("Elapsed time", timer.milliseconds());
			stateMachine.addTelemetryLine("waiting for hive tip");
		}

		if (timer.milliseconds() > BuzzBotConfiguration.VISION_WAIT_FOR_HIVE_TIP_TIME_AUTONOMOUS) {
			isFinished = true;
			return;
		}
		if (allianceColor == DefenderAlliance.Color.RED) {
			if (stateMachine.opMode.startingSide == BuzzBotAutonomousOpMode.StartingSide.DOWN) {
				if (bot.vision.redHiveOrientation() == BuzzBotVision.HiveOrientation.SCORING) {
					stateMachine.opMode.sleep(BuzzBotConfiguration.SHOOTER_WAIT_FOR_TIP_TO_FINISH_TIME_AUTONOMOUS);
					bot.shooter.shootAutonomously();
					isFinished = true;
					return;
				}
			} else if (stateMachine.opMode.startingSide == BuzzBotAutonomousOpMode.StartingSide.UP) {
				if (bot.vision.redHiveOrientation() == BuzzBotVision.HiveOrientation.AUDIENCE) {
					stateMachine.opMode.sleep(BuzzBotConfiguration.SHOOTER_WAIT_FOR_TIP_TO_FINISH_TIME_AUTONOMOUS);

					bot.shooter.shootAutonomously();
					isFinished = true;
					return;
				}
			}

		} else if (allianceColor == DefenderAlliance.Color.BLUE) {
			if (stateMachine.opMode.startingSide == BuzzBotAutonomousOpMode.StartingSide.DOWN) {
				if (bot.vision.blueHiveOrientation() == BuzzBotVision.HiveOrientation.AUDIENCE) {
					stateMachine.opMode.sleep(BuzzBotConfiguration.SHOOTER_WAIT_FOR_TIP_TO_FINISH_TIME_AUTONOMOUS);
					bot.shooter.shootAutonomously();
					isFinished = true;
					return;
				}
			} else if (stateMachine.opMode.startingSide == BuzzBotAutonomousOpMode.StartingSide.UP) {
				if (bot.vision.blueHiveOrientation() == BuzzBotVision.HiveOrientation.SCORING) {
					stateMachine.opMode.sleep(BuzzBotConfiguration.SHOOTER_WAIT_FOR_TIP_TO_FINISH_TIME_AUTONOMOUS);
					bot.shooter.shootAutonomously();
					isFinished = true;
					return;
				}
			}
		}

		isFinished = false;
	}
}
