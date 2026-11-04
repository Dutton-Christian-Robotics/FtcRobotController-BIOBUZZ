package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;

@Autonomous(name = "Sample Simple Autonomous", group = "Samples")
public class RoyceAutoPlaceholder2 extends LinearOpMode {

    BuzzBot bot;

    @Override
    public void runOpMode() {
        bot = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);

        waitForStart();

        while (opModeIsActive()) {

            sleep(6700);

            bot.shooter.turnOn();
            sleep(500);

            bot.shooter.turnOnLift();
            sleep(250);

            bot.shooter.moveGateToShootPosition();
            sleep(3000);

            bot.shooter.turnOff();
            bot.shooter.turnOffLift();
            bot.shooter.moveGateToBlockPosition();

            bot.drivetrain.drive(0.5, 0, 0);
            sleep(500);

            bot.drivetrain.drive(0, 0.5, 0);
            sleep(4000);

        }

    }
}