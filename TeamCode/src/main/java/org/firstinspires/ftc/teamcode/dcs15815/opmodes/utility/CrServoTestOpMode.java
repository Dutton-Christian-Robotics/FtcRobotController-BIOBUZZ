package org.firstinspires.ftc.teamcode.dcs15815.opmodes.utility;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@Utility(name = "CR Servo Test", description = "Testing")
public class CrServoTestOpMode extends LinearOpMode {
	CRServo servo;

	@Override
	public void runOpMode() {

		servo = hardwareMap.crservo.get("proboscis_servo_left");
		double power = 0;

		waitForStart();

		while (!isStopRequested()) {
			if (gamepad1.dpad_up) {
				power += 0.1;
				if (power > 1) {
					power = 1;
				}
				servo.setPower(power);
			} else if (gamepad1.dpad_down) {
				power -= 0.1;
				if (power < -1) {
					power = -1;
				}
				servo.setPower(power);
			} else if (gamepad1.dpad_left || gamepad1.dpad_right) {
				power = 0;
				servo.setPower(power);
			}

			telemetry.addData("Power", power);
			telemetry.update();

		}

	}

}
