package org.firstinspires.ftc.teamcode.dcs15815.TurkeyBot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Turkey Drive", group = "TeleOp")
public class TurkeyDriveOpMode extends LinearOpMode {

	TurkeyBot bot;

	@Override
	public void runOpMode() {
		bot = new TurkeyBot(hardwareMap);

		waitForStart();

		// What should be put here?

	}
}