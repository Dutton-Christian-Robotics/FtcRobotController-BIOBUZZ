package org.firstinspires.ftc.teamcode.dcs15815.BuzzBot;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DefenderState;

public class BuzzBotShootState extends DefenderState {

	public double boost = 0;

	public BuzzBotShootState() {
		super();
	}

	public static BuzzBotShootState make() {
		return new BuzzBotShootState();
	}

	public BuzzBotShootState setBoost(double n) {
		boost = n;
		return this;
	}

	@Override
	public void run() {
		BuzzBot bot = (BuzzBot)stateMachine.bot;

		if (stateMachine.isDebugging) {
			stateMachine.addTelemetryLine("starting to shoot");
		}
		bot.shooter.shootAutonomously();

		if (stateMachine.isDebugging) {
			stateMachine.addTelemetryLine("done shooting");
		}
//		stateMachine.opMode.sleep(3000);

		isFinished = true;
	}
}
