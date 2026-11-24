package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotShootState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotMotion;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotPosition;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DriveToPositionState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.RunOnceState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.TimedDriveState;

@Autonomous(name = "UP Test: Park", group = "Up", preselectTeleOp = "Driver Controlled")
public class TestStateMachineAutonomousOpMode extends BuzzBotStateMachineAutonomousOpMode {

    @Override
    public void setup() {

        startingSide = StartingSide.UP;

        stateMachine
//            .setDebugging(true)
            .setBot(bot)
            .setOpMode(this)
            .startWithState(
                RunOnceState
                    .make(() -> {
                        bot.navigation.setPosition(new DefenderBotPosition(95, -18, 0));
//                                bot.shooter.changeShooterPower(DecodeConfiguration.BLUE4_SHOOTER_MOTOR_POWER);
                    })
                    .setLabel("START")
            ).andThen(
                BuzzBotShootState
                    .make()
                    .setLabel("SHOOT THE PRELOADS")
            ).andThen(
                DriveToPositionState
                    .where(new DefenderBotPosition(90, -42, 0), 0)
                    .setLabel("LEAVE THE WALL")
            ).andThen(
                DriveToPositionState
                    .where(new DefenderBotPosition(-30, -42, 0), 0)
                    .setLabel("PARK")

            );
    }
}