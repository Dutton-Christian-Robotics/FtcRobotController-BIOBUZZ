package org.firstinspires.ftc.teamcode.dcs15815.BuzzBot;


import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPIDController;

import java.util.function.BooleanSupplier;

public class BuzzBot extends DefenderBot {

	private boolean useDebugging = false;
	public BuzzBotMecanumDrivetrain drivetrain;
	public BuzzBotNavigation navigation;
	public BuzzBotShooter shooter;
	public BuzzBotProboscis proboscis;
	public BuzzBotIntake intake;
	public BuzzBotEffects effects;
	public BuzzBotVision vision;

	public BooleanSupplier abortOpMode = () -> false;


	public BuzzBot(HardwareMap hm, Class configClass, Telemetry t) {
		super(hm, configClass, t);

		drivetrain = addSystem(new BuzzBotMecanumDrivetrain(hm, this));
		shooter = addSystem(new BuzzBotShooter(hm, this));
		intake = addSystem(new BuzzBotIntake(hm, this));
		proboscis = addSystem(new BuzzBotProboscis(hm, this));
		navigation = addSystem(new BuzzBotNavigation(hm, this));
		effects = addSystem(new BuzzBotEffects(hm, this));
		vision = addSystem(new BuzzBotVision(hm, this));

	}

	public void setUseDebugging(boolean b) {
		useDebugging = b;
	}



}