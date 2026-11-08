package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;

@Autonomous(name = "DOWN: Wait + Shoot + Park", group = "Hive Down", preselectTeleOp = "Driver Controlled")
public class RoyceAutoPlaceholder2 extends LinearOpMode {

    BuzzBot bot;

    @Override
    public void runOpMode() {
        bot = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);

        waitForStart();

        while (opModeIsActive()) {

            sleep(15000);

            bot.shooter.turnOn();
            sleep(500);

            bot.shooter.turnOnLift();
            sleep(250);

            bot.shooter.moveGateToShootPosition();
            sleep(3000);

            bot.shooter.moveGateToBlockPosition();
            bot.shooter.turnOff();
            bot.shooter.turnOffLift();

            bot.drivetrain.driveNoProportional(0.35, 0, 0);
            sleep(500);

            bot.drivetrain.stopDriving();

            bot.drivetrain.driveNoProportional(0, -1, 0);
            sleep(3000);

            bot.drivetrain.stopDriving();

        }

    }
}