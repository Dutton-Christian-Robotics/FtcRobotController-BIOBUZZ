package org.firstinspires.ftc.teamcode.dcs15815.opmodes.utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;

@Utility(name = "Agitator Test", description = "")
public class AgitatorTestOpMode extends LinearOpMode {
	Servo leftServo, rightServo;

	@Override
	public void runOpMode() {

		leftServo = hardwareMap.servo.get(BuzzBotConfiguration.SHOOTER_AGITATOR_SERVO_LEFT_NAME);
		rightServo = hardwareMap.servo.get(BuzzBotConfiguration.SHOOTER_AGITATOR_SERVO_RIGHT_NAME);
		double position = 0;

		waitForStart();
		leftServo.setPosition(0.5);
		rightServo.setPosition(0.5);
		while (!isStopRequested()) {
			if (gamepad1.xWasPressed()) {
				leftServo.setPosition(0.4);
				rightServo.setPosition(0.6);
			}
			if (gamepad1.yWasPressed()) {
				leftServo.setPosition(0.5);
				rightServo.setPosition(0.5);

			}


		}

	}

}
