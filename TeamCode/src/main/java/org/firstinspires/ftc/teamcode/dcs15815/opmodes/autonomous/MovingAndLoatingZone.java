package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;

@Disabled
@Autonomous(name = "MovingOpMode", group = "Samples")
public class MovingAndLoatingZone extends LinearOpMode {

    BuzzBot bot;

    @Override
    public void runOpMode() {

        bot = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);

        waitForStart();

        while (opModeIsActive()) {


            bot.drivetrain.setPower(.5);

            bot.drivetrain.sleep(1000);

            sleep(2000);

            bot.drivetrain.sleep(1000);





        }

    }
}