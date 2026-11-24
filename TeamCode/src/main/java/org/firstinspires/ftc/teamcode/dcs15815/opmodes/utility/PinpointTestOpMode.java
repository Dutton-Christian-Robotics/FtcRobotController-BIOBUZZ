package org.firstinspires.ftc.teamcode.dcs15815.opmodes.utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.util.Range;

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

        telemetry.addData("Status", "calibrating");
        telemetry.update();

        bot = new BuzzBot(hardwareMap, BuzzBotConfiguration.class, telemetry);
        sleep(3000);
        bot.navigation.setPosition(new DefenderBotPosition(95, -18, 0));
//        bot.navigation.setPosition(new DefenderBotPosition(0, 0, 0));

        telemetry.addData("Status", "READY");
        telemetry.update();

        while (!isStarted() && !opModeIsActive()) {
            displayTelemetry();
        }

        waitForStart();

        while (opModeIsActive()) {

//            if (gamepad1.xWasPressed()) {

            if (gamepad1.aWasPressed()) {
                boolean done = false;
                do {
                    done = bot.navigation.driveToFromCurrent(new DefenderBotPosition(-30, -42, 0));
                    if (gamepad1.aWasPressed()) {
                        done = true;
                        bot.drivetrain.stopDriving();
                    }
//                    displayTelemetry();

                } while (!done && !isStopRequested());


            }

            if (gamepad1.bWasPressed()) {
                boolean done = false;
                do {
                    done = bot.navigation.driveToFromCurrent(new DefenderBotPosition(0, 0, 90));
                    if (gamepad1.aWasPressed()) {
                        done = true;
                        bot.drivetrain.stopDriving();
                    }
//                    displayTelemetry();

                } while (!done && !isStopRequested());


            }

            if (gamepad1.xWasPressed()) {
                boolean done = false;
                do {
                    done = bot.navigation.driveToFromCurrent(new DefenderBotPosition(-24, 0, 0));
                    if (gamepad1.aWasPressed()) {
                        done = true;
                        bot.drivetrain.stopDriving();
                    }

//                    displayTelemetry();

                } while (!done && !isStopRequested());


            }
            if (gamepad1.yWasPressed()) {
                boolean done = false;
                do {
                    done = bot.navigation.driveToFromCurrent(new DefenderBotPosition(0, 24, 0));
                    if (gamepad1.aWasPressed()) {
                        done = true;
                        bot.drivetrain.stopDriving();
                    }

//                    displayTelemetry();

                } while (!done && !isStopRequested());


            }

//
//        while (!bot.navigation.driveToFromCurrent(TARGET_1.asPose2D(), 0.5, 0) && opModeIsActive()) {
//            telemetry.addData("x", bot.navigation.getPosition().getX(DistanceUnit.INCH));
//            telemetry.addData("y", bot.navigation.getPosition().getY(DistanceUnit.INCH));
//            telemetry.addData("h", bot.navigation.getPosition().getHeading(AngleUnit.DEGREES));
//            telemetry.addData("err", bot.navigation.totalError);
//            telemetry.update();
//            bot.navigation.updatePosition();
//        }
//        bot.drivetrain.stopDriving();


        }
    }
    public void displayTelemetry() {
        bot.navigation.updatePosition();
        telemetry.addData("x", bot.navigation.getPosition().getX(DistanceUnit.INCH));
        telemetry.addData("y", bot.navigation.getPosition().getY(DistanceUnit.INCH));
        telemetry.addData("h", bot.navigation.getPosition().getHeading(AngleUnit.DEGREES));
        telemetry.addData("err", bot.navigation.totalError);
        telemetry.update();

    }


}