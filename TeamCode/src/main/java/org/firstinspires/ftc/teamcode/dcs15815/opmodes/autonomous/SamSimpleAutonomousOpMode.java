package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;

@Autonomous(name = "UP: Shoot + Move", group = "Hive Up")
public class SamSimpleAutonomousOpMode extends LinearOpMode {

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
        sleep(3500);
        bot.shooter.moveGateToBlockPosition();
        bot.shooter.turnOffLift();
        bot.shooter.turnOff();
        bot.drivetrain.driveNoProportional(0.5, 0, 0);
        sleep(500);
        bot.drivetrain.stopDriving();

    }
}