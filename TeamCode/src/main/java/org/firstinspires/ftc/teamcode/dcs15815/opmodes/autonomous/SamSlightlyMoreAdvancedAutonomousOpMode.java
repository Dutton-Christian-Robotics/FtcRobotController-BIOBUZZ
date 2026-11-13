package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;

@Disabled
@Autonomous(name = "Sam's Slightly More Advanced Autonomous", group = "Samples")
public class SamSlightlyMoreAdvancedAutonomousOpMode extends LinearOpMode {

    BuzzBot bot;

    @Override
    public void runOpMode() {
        bot = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);

        waitForStart();

        bot.shooter.turnOn();
        sleep(100);
        bot.shooter.turnOnLift();
        sleep(1000);
        bot.shooter.moveGateToShootPosition();
        bot.shooter.turnOff();
        bot.shooter.turnOffLift();
        bot.shooter.moveGateToBlockPosition();
        bot.drivetrain.drive(-0.75, 0, 0);
        sleep(3000);
        bot.drivetrain.drive(0, 0, -0.25);
        sleep(1000);
        bot.drivetrain.drive(0.25, 0, 0);
        bot.intake.turnOn();
        bot.proboscis.goOut();
        sleep(3000);
        bot.drivetrain.drive(0, 0, 0);
        bot.proboscis.goIn();
        sleep(3000);
        bot.proboscis.stop();
        bot.intake.turnOff();


    }
}