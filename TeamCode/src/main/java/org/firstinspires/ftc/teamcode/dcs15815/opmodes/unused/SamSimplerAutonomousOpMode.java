package org.firstinspires.ftc.teamcode.dcs15815.opmodes.unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;

@Disabled
@Autonomous(name = "UP: Move Only", group = "Up")
public class SamSimplerAutonomousOpMode extends LinearOpMode {

    BuzzBot bot;

    @Override
    public void runOpMode() {
        bot = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);

        waitForStart();

        bot.drivetrain.drive(-0.5, 0, 0);
        sleep(1000);
        bot.drivetrain.stopDriving();

    }
}