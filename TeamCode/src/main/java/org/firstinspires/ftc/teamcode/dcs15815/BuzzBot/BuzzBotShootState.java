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
//		bot.shooter.shootAutonomously(boost);
		isFinished = true;
	}
}
