package org.firstinspires.ftc.teamcode.dcs15815.opmodes.utility;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Drivetrain Test", group="Test")
public class DrivetrainTestOpMode extends LinearOpMode {
	public DcMotorEx backLeft, frontLeft, frontRight, backRight;

	@Override
	public void runOpMode() {

		backLeft = (DcMotorEx) hardwareMap.dcMotor.get("drivetrain_motor_back_left");
		frontLeft = (DcMotorEx) hardwareMap.dcMotor.get("drivetrain_motor_front_left");
		frontRight = (DcMotorEx) hardwareMap.dcMotor.get("drivetrain_motor_front_right");
		backRight = (DcMotorEx) hardwareMap.dcMotor.get("drivetrain_motor_back_right");

		frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
		backRight.setDirection(DcMotorSimple.Direction.REVERSE);

		waitForStart();

		backLeft.setPower(1);
		frontLeft.setPower(1);
		frontRight.setPower(1);
		backRight.setPower(1);

		while(true) {

		}
//		sleep(5000);

	}

}
