package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotShootState;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotWaitThenShootState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotMotion;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.TimedDriveState;

@Autonomous(name = "DOWN: Wait + Shoot + Park", group = "Down", preselectTeleOp = "Driver Controlled")
public class RoyceBackupDownsideWaitShootParkStateMachineAutonomousOpMode extends BuzzBotStateMachineAutonomousOpMode {

    @Override
    public void setup() {

        startingSide = StartingSide.DOWN;

        stateMachine
//            .setDebugging(true)
            .setBot(bot)
            .setOpMode(this)
            .startWithState(
                BuzzBotWaitThenShootState
                    .make()
                    .setLabel("WAIT THEN SHOOT")
            ).andThen(
                TimedDriveState
                    .moving(DefenderBotMotion.make(-0.3, 0, 0), 700)
                    .setLabel("MOVE AWAY FROM THE WALL")
            ).andThen(
                TimedDriveState
                    .moving(DefenderBotMotion.make(0, .6, 0), 1800)
                    .setLabel("STRAFE")
            );
    }
}