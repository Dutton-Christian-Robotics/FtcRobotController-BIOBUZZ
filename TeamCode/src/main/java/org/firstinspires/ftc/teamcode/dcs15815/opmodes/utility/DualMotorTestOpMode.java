package org.firstinspires.ftc.teamcode.dcs15815.opmodes.utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Dual Motor Test", group="")
public class DualMotorTestOpMode extends LinearOpMode {
	DcMotor motor1;
	DcMotor motor2;

	@Override
	public void runOpMode() {
		motor1 = hardwareMap.dcMotor.get("shooter_motor_left");
		motor2 = hardwareMap.dcMotor.get("shooter_motor_right");

		motor1.setDirection(DcMotor.Direction.REVERSE);
		motor2.setDirection(DcMotor.Direction.FORWARD);
		double power = 0;
		waitForStart();
		while (!isStopRequested()) {
			if (gamepad1.dpad_up) {
				power += 0.1;
			} else if (gamepad1.dpad_down) {
				power -= 0.1;
			} else if (gamepad1.dpad_left || gamepad1.dpad_right) {
				power = 0;
			}
			motor1.setPower(power);
			motor2.setPower(power);

			telemetry.addData("Power", power);
			telemetry.update();
		}
	}
}
