package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;



public abstract class BuzzBotAutonomousOpMode extends LinearOpMode {

    public enum StartingSide {
        UP,
        DOWN,
        UNKNOWN
    }


    public BuzzBot bot; // = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);
    public DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;
    public StartingSide startingSide = StartingSide.UNKNOWN;

    public void setAlliance() {
        DefenderAlliance.getInstance().setColor(allianceColor);
    }


    public void setup() {

    }

    @Override
    public void runOpMode() {
        bot = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);


        bot.abortOpMode = () -> isStopRequested();

        setup();

        sleep(BuzzBotConfiguration.VISION_WAIT_FOR_READY_TIME_AUTONOMOUS);
        if (startingSide == StartingSide.UP) {
            allianceColor = bot.vision.inferAllianceStartingUp();

        } else if (startingSide == StartingSide.DOWN) {
            allianceColor = bot.vision.inferAllianceStartingDown();
        }


        setAlliance();

        if (DefenderAlliance.getInstance().isRed()) {
            telemetry.addData("Alliance", "RED");
            bot.effects.heartbeatRed();

        } else if (DefenderAlliance.getInstance().isBlue()) {
            telemetry.addData("Alliance", "BLUE");
            bot.effects.heartbeatBlue();

        } else {
            telemetry.addData("Alliance", "unknown");
            bot.effects.heartbeatGray();

        }
        telemetry.update();


        waitForStart();

        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.strobeRed();

        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.strobeBlue();

        } else {
            telemetry.addData("Alliance", "unknown");
            bot.effects.strobeWhite();

        }

        try {
            performAutonomous();

        } catch (Exception e) {

        }


        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.breathRed();

        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.breathBlue();

        } else {
            bot.effects.breathGray();
        }

        bot.shooter.moveGateToBlockPosition();

    }

    public abstract void performAutonomous();

}
