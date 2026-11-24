package org.firstinspires.ftc.teamcode.dcs15815.BuzzBot;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

public class BuzzBotAgitatorCycle implements Runnable {

	private BuzzBotShooter shooter;
	private boolean isRunning = false;
	public boolean shouldTerminate = false;

	public void setShooter(BuzzBotShooter i) {
		shooter = i;
	}

	public synchronized void stop() {
		this.isRunning = false;
	}

	public void start() {
		this.isRunning = true;
	}

	public void terminate() {
		shouldTerminate = true;
	}


	private synchronized boolean keepRunning() {
		return this.isRunning == true;
	}

	@Override
	public void run() {
		BuzzBot bot = (BuzzBot)this.shooter.bot;

		while (!Thread.currentThread().isInterrupted() && !shouldTerminate) {

			while (keepRunning()) {
//				try {
					this.shooter.moveAgitatorToPushPosition();
					bot.sleep(BuzzBotConfiguration.SHOOTER_AGITATOR_CYCLE_LENGTH);
					this.shooter.moveAgitatorToRestPosition();
					bot.sleep(BuzzBotConfiguration.SHOOTER_AGITATOR_CYCLE_LENGTH);
//				} catch (InterruptedException e) {
//					throw new RuntimeException(e);
//				}

			}
		}
	}
}
