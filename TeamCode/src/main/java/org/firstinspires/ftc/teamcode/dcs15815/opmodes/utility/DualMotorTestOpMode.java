package org.firstinspires.ftc.teamcode.dcs15815.opmodes.utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;

@Utility(name = "Dual Motor Test", description = "Test 2 motors alternately")
public class DualMotorTestOpMode extends LinearOpMode {
	String name1, name2;
	DcMotor motor1, motor2;

	@Override
	public void runOpMode() {

		name1 = BuzzBotConfiguration.SHOOTER_MOTOR_LEFT_NAME;
		name2 = BuzzBotConfiguration.SHOOTER_MOTOR_RIGHT_NAME;

		motor1 = hardwareMap.dcMotor.get(name1);
		motor2 = hardwareMap.dcMotor.get(name2);
		double power = 0;
		double resolution = .1;
		double minResolution = .01;
		double maxResolution = 0.5;
		int which = 0;

		waitForStart();

		while (!isStopRequested()) {
			if (gamepad1.dpad_up) {
				power = Math.min(power + resolution, 1);

			} else if (gamepad1.dpad_down) {
				power = Math.max(power - resolution, -1);

			} else if (gamepad1.xWasPressed()) {
				power = 0;
			}

			if (gamepad1.dpadLeftWasPressed()) {
				resolution = Math.max(resolution / 2, minResolution);

			} else if (gamepad1.dpadRightWasPressed()) {
				resolution = Math.max(resolution * 2, maxResolution);
			}

			if (gamepad1.aWasPressed()) {
				which = 1;
				motor2.setPower(0);

			} else if (gamepad1.bWasPressed()) {
				which = 2;
				motor1.setPower(0);
			}

			if (which == 1) {
				motor1.setPower(power);
				telemetry.addData("Motor (a)", name1);

			} else if (which == 2) {
				motor2.setPower(power);
				telemetry.addData("Motor (b)", name2);
			}
			telemetry.addData("Resolution", resolution);
			telemetry.addData("Power", power);
			telemetry.update();

		}

	}

}
