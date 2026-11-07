package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;

@Autonomous(name = "Move Only", group = "Opposite Side")
public class RoyceAutoSuperSimpleOpMode extends LinearOpMode {

    BuzzBot bot;

    @Override
    public void runOpMode() {
        bot = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);

        waitForStart();

        while (opModeIsActive()) {


            bot.drivetrain.drive(-0.5, 0, 0);
            sleep(750);

            bot.drivetrain.stopDriving();
        }

    }
}