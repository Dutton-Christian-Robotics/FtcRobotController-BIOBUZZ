package org.firstinspires.ftc.teamcode.dcs15815.BuzzBot;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

public class BuzzBotAgitatorCycle implements Runnable {

	private BuzzBotShooter shooter;
	private boolean isRunning = false;

	public void setShooter(BuzzBotShooter i) {
		shooter = i;
	}

	public synchronized void stop() {
		shooter.bot.telemetry.addData("stop","");
		shooter.bot.telemetry.update();
		this.isRunning = false;
	}

	public void start() {
		this.isRunning = true;
		shooter.bot.telemetry.addData("start","");
		shooter.bot.telemetry.update();
	}


	private synchronized boolean keepRunning() {
		return this.isRunning == true;
	}

	@Override
	public void run() {
		BuzzBot bot = (BuzzBot)this.shooter.bot;

		while (true) {
			bot.telemetry.addData("run", "");
			bot.telemetry.update();

			while (keepRunning()) {
				bot.telemetry.addData("inside while", "");
				bot.telemetry.update();
//				try {
					this.shooter.moveAgitatorToRestPosition();
					bot.sleep(BuzzBotConfiguration.SHOOTER_AGITATOR_CYCLE_LENGTH);
					this.shooter.moveAgitatorToRestPosition();
					bot.sleep(BuzzBotConfiguration.SHOOTER_AGITATOR_CYCLE_LENGTH);
					bot.telemetry.addData("after cycle", "");
					bot.telemetry.update();
//				} catch (InterruptedException e) {
//					throw new RuntimeException(e);
//				}

			}
		}
	}
}
