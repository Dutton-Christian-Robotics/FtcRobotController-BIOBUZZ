package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DefenderStateMachine;

public abstract class BuzzBotStateMachineAutonomousOpMode extends BuzzBotAutonomousOpMode {

	DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

	DefenderStateMachine stateMachine = new DefenderStateMachine();

	@Override
	public abstract void setup();

	@Override
	public final void performAutonomous() {
		while (opModeIsActive() && !stateMachine.isFinished()) {
			stateMachine.run();
		}
		telemetry.addLine("All done!");
		telemetry.update();

	}


}
