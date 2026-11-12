package org.firstinspires.ftc.teamcode.dcs15815.opmodes.utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;

@Utility(name = "Dual Servo Test", description = "Test 2 servos alternately")
public class DualServoTestOpMode extends LinearOpMode {
	String name1, name2;
	Servo servo1, servo2;

	@Override
	public void runOpMode() {

		name1 = "x";
		name2 = "x";

		servo1 = hardwareMap.servo.get(BuzzBotConfiguration.SHOOTER_AGITATOR_SERVO_LEFT_NAME);
		servo2 = hardwareMap.servo.get(BuzzBotConfiguration.SHOOTER_AGITATOR_SERVO_RIGHT_NAME);
		double position = 0;
		double resolution = .1;
		double minResolution = .01;
		double maxResolution = 0.5;
		int which = 0;

		waitForStart();

		while (!isStopRequested()) {
			if (gamepad1.dpad_up) {
				position = Math.min(position + resolution, 1);

			} else if (gamepad1.dpad_down) {
				position = Math.max(position - resolution, 0);

			} else if (gamepad1.xWasPressed()) {
				position = 0;

			} else if (gamepad1.yWasPressed()) {
				position = 1;
			}

			if (gamepad1.dpadLeftWasPressed()) {
				resolution = Math.max(resolution / 2, minResolution);

			} else if (gamepad1.dpadRightWasPressed()) {
				resolution = Math.max(resolution * 2, maxResolution);
			}

			if (gamepad1.aWasPressed()) {
				which = 1;
				servo2.setPosition(0);

			} else if (gamepad1.bWasPressed()) {
				which = 2;
				servo1.setPosition(0);
			}

			if (which == 1) {
				servo1.setPosition(position);
				telemetry.addData("Servo (a)", name1);

			} else if (which == 2) {
				servo2.setPosition(position);
				telemetry.addData("Servo (b)", name2);
			}
			telemetry.addData("Resolution", resolution);
			telemetry.addData("Position", position);
			telemetry.update();

		}

	}

}
