package org.firstinspires.ftc.teamcode.dcs15815.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAnalogModifier;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@TeleOp(name = "Driver Controlled", group = "Driver")
public class DriverControlledOpMode extends LinearOpMode {
	BuzzBot bot;

	@Override
	public void runOpMode() {

		bot = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);

		bot.shooter.moveGateToBlockPosition();
		bot.effects.strobeGold();
		waitForStart();
		boolean shootingWheelsOn = false;

		while (opModeIsActive()) {

			/* CURRENT CONTROL CONFIGURATION =================

				Forward / Reverse: GP1 left stick
				Rotate: GP1 right stick
				Strafe: GP1 left trigger + GP1 right trigger

				Intake on: GP2 left rigger HELD (off on release)
				Intake reverse, Gate to outtake: GP2 x HELD (off on release)

				Shooter wheels on/off: GP2 right bumper (as toggle)
				Lift on + Gate to shoot: GP2 right trigger HELD (off on release)

				Extend Proboscis: GP2 a HELD (off on release)
				Retract Proboscis: GP2 b HELD (off on release
				Stop Proboscis: GP2 y pressed (shouldn't be needed)

			 */


			bot.drivetrain.driveNoProportional(
				   gamepad1.left_stick_y,
				   (gamepad1.right_trigger - gamepad1.left_trigger),
				   -1 * gamepad1.right_stick_x);

			if (gamepad2.leftTriggerWasPressed()) {
				bot.intake.turnOn();
			} else if (gamepad2.leftTriggerWasReleased()) {
				bot.intake.turnOff();
			} else if (gamepad2.xWasPressed()) {
				bot.intake.reverse();
				bot.shooter.moveGateToOuttakePosition();
			} else if (gamepad2.xWasReleased()) {
				bot.intake.turnOff();
				bot.shooter.moveGateToBlockPosition();
			}

			if (gamepad2.rightBumperWasPressed()) {
				if (!shootingWheelsOn) {
					bot.shooter.turnOn();
					shootingWheelsOn = true;
				} else {
					bot.shooter.turnOff();
					shootingWheelsOn = false;
				}
			}

			if (gamepad2.rightTriggerWasPressed()) {
				bot.shooter.turnOnLift();
				// sleep?
				bot.shooter.moveGateToShootPosition();

			} else if (gamepad2.rightTriggerWasReleased()) {
				bot.shooter.turnOffLift();
				bot.shooter.moveGateToBlockPosition();
			}

			if (gamepad2.aWasPressed()) {
				bot.proboscis.goOut();
			} else if (gamepad2.aWasReleased()) {
				bot.proboscis.stop();
			} else if (gamepad2.bWasPressed()) {
				bot.proboscis.goIn();
			} else if (gamepad2.bWasReleased()) {
				bot.proboscis.stop();
			} else if (gamepad2.yWasPressed()) {
				bot.proboscis.stop();
//			} else if (gamepad2.dpadLeftWasPressed() || gamepad2.dpadRightWasPressed()) {
//				bot.proboscis.changePower(0.5);
			}


			// need way to use gate to push things out

		}


	}

}