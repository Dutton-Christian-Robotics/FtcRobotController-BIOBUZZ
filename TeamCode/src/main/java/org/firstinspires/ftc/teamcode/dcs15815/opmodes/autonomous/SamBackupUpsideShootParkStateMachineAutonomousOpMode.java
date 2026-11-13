package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotShootState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotMotion;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.TimedDriveState;

@Autonomous(name = "UP: Shoot + Park", group = "Up", preselectTeleOp = "Driver Controlled")
public class SamBackupUpsideShootParkStateMachineAutonomousOpMode extends BuzzBotStateMachineAutonomousOpMode {

    @Override
    public void setup() {

        startingSide = StartingSide.UP;

        stateMachine
//            .setDebugging(true)
            .setBot(bot)
            .setOpMode(this)
            .startWithState(
                BuzzBotShootState
                    .make()
                    .setLabel("SHOOT THE PRELOADS")
            ).andThen(
                TimedDriveState
                    .moving(DefenderBotMotion.make(-0.4, 0, 0), 500)
                    .setLabel("MOVE AWAY FROM THE WALL")
            ).andThen(
                TimedDriveState
                    .moving(DefenderBotMotion.make(0, -.6, 0), 1300)
                    .setLabel("STRAFE")
            ).andThen(
                TimedDriveState
                    .moving(DefenderBotMotion.make(-0.40, 0, 0), 2500)
                    .setLabel("GO PARK")
            );
    }
}