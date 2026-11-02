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

		waitForStart();

		while (opModeIsActive()) {

//			bot.drivetrain.driveNoProportional(
//				   gamepad1.left_stick_y,
//				   (gamepad1.right_trigger - gamepad1.left_trigger),
//				   gamepad1.right_stick_x);

		}


	}

}