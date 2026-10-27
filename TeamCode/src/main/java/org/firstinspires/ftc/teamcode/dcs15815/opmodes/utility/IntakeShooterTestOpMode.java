package org.firstinspires.ftc.teamcode.dcs15815.opmodes.utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Intake and Shooter Test", group="")
public class IntakeShooterTestOpMode extends LinearOpMode {
	DcMotor intakeMotor;
	DcMotor shooterLiftMotor;
	DcMotor shooterMotorLeft;
	DcMotor shooterMotorRight;

	double intakePower = 1;

	@Override
	public void runOpMode() {
		intakeMotor = hardwareMap.dcMotor.get("intake_motor");
		shooterLiftMotor = hardwareMap.dcMotor.get("shooter_lift_motor");
		shooterMotorLeft = hardwareMap.dcMotor.get("shooter_motor_left");
		shooterMotorRight = hardwareMap.dcMotor.get("shooter_motor_right");

		intakeMotor.setDirection(DcMotor.Direction.FORWARD);
		shooterLiftMotor.setDirection(DcMotor.Direction.REVERSE);
		shooterMotorLeft.setDirection(DcMotor.Direction.REVERSE);
		shooterMotorRight.setDirection(DcMotor.Direction.FORWARD);

		double power = 0;
		waitForStart();
		while (!isStopRequested()) {
			if (gamepad1.dpad_up) {
				intakeMotor.setPower(intakePower);
			} else if (gamepad1.dpad_down) {
				intakeMotor.setPower(-1 * intakePower);
			} else if (gamepad1.dpad_left || gamepad1.dpad_right) {
				intakeMotor.setPower(0);
			}

			if (gamepad1.right_trigger_pressed) {
				shooterMotorLeft.setPower(1);
				shooterMotorRight.setPower(1);
			} else if (gamepad1.right_bumper) {
				shooterMotorLeft.setPower(0);
				shooterMotorRight.setPower(0);
			}
			if (gamepad1.y) {
				shooterLiftMotor.setPower(1);
			} else if (gamepad1.a) {
				shooterLiftMotor.setPower(0);
			}

			telemetry.update();
		}
	}
}
