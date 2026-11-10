package org.firstinspires.ftc.teamcode.dcs15815.opmodes.utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Utility;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotPosition;

@Utility(name = "Pinpoint Test", description = "Testing")
public class PinpointTestOpMode extends LinearOpMode {
    BuzzBot bot;

//    static final Pose2D TARGET_1 = new Pose2D(DistanceUnit.INCH, -24, 24, AngleUnit.DEGREES, 180);
//    static final Pose2D TARGET_1 = new Pose2D(DistanceUnit.INCH, 0, 24, AngleUnit.DEGREES, 90);
    static final DefenderBotPosition TARGET_1 = new DefenderBotPosition(24, 0, 0);
    @Override
    public void runOpMode() {


        bot = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);
        bot.navigation.setPosition(new DefenderBotPosition(0, 0, 0));


        while (!isStarted() && !opModeIsActive()) {
            bot.navigation.updatePosition();

            telemetry.addData("x", bot.navigation.getPosition().getX(DistanceUnit.INCH));
            telemetry.addData("y", bot.navigation.getPosition().getY(DistanceUnit.INCH));
            telemetry.addData("h", bot.navigation.getPosition().getHeading(AngleUnit.DEGREES));
            telemetry.addData("err", bot.navigation.totalError);
            telemetry.update();
        }

        waitForStart();

        while (!bot.navigation.driveToFromCurrent(TARGET_1.asPose2D(), 0.5, 0) && opModeIsActive()) {
            telemetry.addData("x", bot.navigation.getPosition().getX(DistanceUnit.INCH));
            telemetry.addData("y", bot.navigation.getPosition().getY(DistanceUnit.INCH));
            telemetry.addData("h", bot.navigation.getPosition().getHeading(AngleUnit.DEGREES));
            telemetry.addData("err", bot.navigation.totalError);
            telemetry.update();
            bot.navigation.updatePosition();
        }
        bot.drivetrain.stopDriving();


    }

}