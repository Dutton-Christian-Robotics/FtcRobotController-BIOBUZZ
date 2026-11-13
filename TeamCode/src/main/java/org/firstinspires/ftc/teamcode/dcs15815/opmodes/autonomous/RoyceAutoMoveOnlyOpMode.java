package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;

@Autonomous(name = "DOWN: Move", group = "Down", preselectTeleOp = "Driver Controlled")
public class RoyceAutoMoveOnlyOpMode extends LinearOpMode {

    BuzzBot bot;

    @Override
    public void runOpMode() {
        bot = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);

        waitForStart();

        while (opModeIsActive()) {


            bot.drivetrain.driveNoProportional(0.5, 0, 0);
            sleep(500);

            bot.drivetrain.stopDriving();

        }

    }
}