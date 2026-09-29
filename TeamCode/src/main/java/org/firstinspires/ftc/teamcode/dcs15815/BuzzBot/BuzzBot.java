package org.firstinspires.ftc.teamcode.dcs15815.BuzzBot;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;

import java.util.function.BooleanSupplier;

public class BuzzBot extends DefenderBot {

//    public DecodeIntake intake;
//    public DecodeEffects effects;
//    public DecodeShooter shooter;
//    public DecodeMecanumDrivetrain drivetrain;
//    public DecodeNavigation navigation;
//    public VoltageSensor voltageSensor;
//
//
    private boolean useDebugging = false;
//    public boolean useSpeech = false;
//    public BooleanSupplier abortOpMode = () -> false;


    public BuzzBot(HardwareMap hm, Class configClass, Telemetry t) {
        super(hm, configClass, t);

//        intake = addSystem(new DecodeIntake(hm, this));
//        effects = addSystem(new DecodeEffects(hm, this));
//        shooter = addSystem(new DecodeShooter(hm, this));
//        drivetrain = addSystem(new DecodeMecanumDrivetrain(hm, this));
//
//        navigation = addSystem(new DecodeNavigation(hm, this));
//
//        voltageSensor = hm.voltageSensor.iterator().next();
    }

    public void setUseDebugging(boolean b) {
        useDebugging = b;
    }



}