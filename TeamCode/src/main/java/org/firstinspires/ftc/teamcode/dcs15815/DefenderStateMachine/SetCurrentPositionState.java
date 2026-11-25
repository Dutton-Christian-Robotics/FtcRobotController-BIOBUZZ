package org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotPosition;

public class SetCurrentPositionState extends DefenderState {
	private DefenderBotPosition target;


	public SetCurrentPositionState(DefenderBotPosition p) {
		super();
		target = p;

	}

	public SetCurrentPositionState(double x, double y, double h) {
		super();
		target = new DefenderBotPosition(x, y, h);
	}

	public SetCurrentPositionState(String configName) {
		super();
		try {
			target = (DefenderBotPosition) BuzzBotConfiguration.class.getField(configName).get(BuzzBotConfiguration.class);
		} catch (Exception e) {
			System.out.println("Could not find that position.");
		}
	}

	public static SetCurrentPositionState make(DefenderBotPosition p) {
		return new SetCurrentPositionState(p);
	}

	public static SetCurrentPositionState make(double x, double y, double h) {
		return new SetCurrentPositionState(x, y, h);
	}

	public static SetCurrentPositionState make(String configName) {
		return new SetCurrentPositionState(configName);
	}

	public static SetCurrentPositionState where(DefenderBotPosition pos) {
		return new SetCurrentPositionState(pos);
	}


	@Override
	public void run() {
		BuzzBot bot = (BuzzBot) stateMachine.bot;
		ElapsedTime waitTimer = new ElapsedTime();
		long waitTimeout = 4000;

		while (!bot.navigation.isPinpointReady() && waitTimer.milliseconds() < waitTimeout) {
			// waiting! waiting!
			stateMachine.opMode.sleep(100);
		}

		if (bot.navigation.isPinpointReady()) {
			bot.navigation.setPosition(target);
		} else {
			throw new RuntimeException("Pinpoint was not ready before timeout to set position.");
		}


	}
}