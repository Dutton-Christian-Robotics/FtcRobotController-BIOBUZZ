package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;


public abstract class BuzzBotAutonomousOpMode extends LinearOpMode {
    public BuzzBot bot;
    DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

    public abstract void setAlliance();

    public void setup() {

    }

    @Override
    public void runOpMode() {
        bot = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);


        bot.abortOpMode = () -> isStopRequested();

        setAlliance();

        if (DefenderAlliance.getInstance().isRed()) {
            telemetry.addData("Alliance", "RED");
        } else if (DefenderAlliance.getInstance().isBlue()) {
            telemetry.addData("Alliance", "BLUE");
        } else {
            telemetry.addData("Alliance", "unknown");
        }
        telemetry.update();

        if (DefenderAlliance.getInstance().isRed()) {
//            bot.effects.scanRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
//            bot.effects.scanBlue();
        } else {
//            bot.effects.wavesParty();
        }

        setup();

        waitForStart();

        if (DefenderAlliance.getInstance().isRed()) {
//            bot.effects.heartbeatRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
//            bot.effects.heartbeatBlue();
        } else {
//            bot.effects.wavesParty();
        }

        // DO SPECIFIC OPMODE STUFF HERE
//        bot.effects.startLiveStatus();
        try {
            performAutonomous();
        } catch (Exception e) {

        }


        if (DefenderAlliance.getInstance().isRed()) {
//            bot.effects.scanRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
//            bot.effects.scanBlue();
        } else {
//            bot.effects.wavesParty();
        }

    }

    public abstract void performAutonomous();

}
