package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotPosition;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DefenderStateMachine;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DriveToPositionState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.RunOnceState;

@Utility(name = "Pinpoint Driving Test", description = "Testing")
public class StateMachinePinpointTestOpMode extends BuzzBotAutonomousOpMode {

	DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

	DefenderStateMachine sm = new DefenderStateMachine(bot);

	@Override
	public void setup() {
		sm
			   .setOpMode(this)
			   .setDebugging(true)
			   .startWithState(
					 RunOnceState
						    .make(() -> {
							    bot.navigation.setPosition(new DefenderBotPosition(69, -12, 0));
						    })
						    .setBeforeStop(() -> {
							    bot.telemetry.addData("x", bot.navigation.getPosition().getX(DistanceUnit.INCH));
							    bot.telemetry.addData("y", bot.navigation.getPosition().getY(DistanceUnit.INCH));
							    bot.telemetry.addData("h", bot.navigation.getPosition().getHeading(AngleUnit.DEGREES));
							    bot.telemetry.addData("err", bot.navigation.totalError);
							    bot.telemetry.update();
							    bot.sleep(5000);
						    })
						    .setLabel("START")
			   ).andThen(
					 DriveToPositionState
						    .where(new DefenderBotPosition(45, -12, 0), 3000)
						    .setLabel("DONE")
			   );


	}

	@Override
	public void performAutonomous() {
		while (opModeIsActive() && !sm.isFinished()) {
			sm.run();
		}
		telemetry.addLine("All done!");
		telemetry.update();

	}

	public void setAlliance() {
		DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);
	}

}