package org.firstinspires.ftc.teamcode.dcs15815.opmodes.utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.CRServo;

@Utility(name = "Dual CR Servo Test", description = "Test 2 CR servos alternately")
public class DualCrServoTestOpMode extends LinearOpMode {
	String name1, name2;
	CRServo servo1, servo2;

	@Override
	public void runOpMode() {

		name1 = "proboscis_servo_left";
		name2 = "proboscis_servo_right";

		servo1 = hardwareMap.crservo.get(name1);
		servo2 = hardwareMap.crservo.get(name2);
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
				servo2.setPower(0);

			} else if (gamepad1.bWasPressed()) {
				which = 2;
				servo1.setPower(0);
			}

			if (which == 1) {
				servo1.setPower(power);
				telemetry.addData("Servo (a)", name1);

			} else if (which == 2) {
				servo2.setPower(power);
				telemetry.addData("Servo (b)", name2);
			}
			telemetry.addData("Resolution", resolution);
			telemetry.addData("Power", power);
			telemetry.update();

		}

	}

}
