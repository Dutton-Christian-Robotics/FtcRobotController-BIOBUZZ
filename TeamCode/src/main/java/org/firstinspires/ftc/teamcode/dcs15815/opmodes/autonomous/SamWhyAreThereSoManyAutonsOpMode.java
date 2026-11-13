package org.firstinspires.ftc.teamcode.dcs15815.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBot;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.BuzzBot.BuzzBotShootState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DriveToPositionState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.RunOnceState;

@Disabled
@Autonomous(name = "Sam's Why Are There So Many Autons", group = "Samples")
public class SamWhyAreThereSoManyAutonsOpMode extends BuzzbotAutonomousOpMode {

    BuzzBot bot;

    @Override
    public void setup() {

        sm
                .setOpMode(this)
                .setDebugging(true)
                .startWithState(
                        RunOnceState
                                .make(() -> {
                                    bot.navigation.setPosition(69, -12, 0);
                                })
                                .setLabel("START")
                ).andThen(
                        BuzzBotShootState
                                .make()
                                .setLabel("SHOOT PRELOAD")
                ).andThen(
                        DriveToPositionState
                                .where(-32, -69, -90)
                                .setLabel("RED LOADING ZONE PARK")
                );



    }
}