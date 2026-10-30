package org.firstinspires.ftc.teamcode.dcs15815.BuzzBot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotDrivetrain;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

import java.util.HashMap;

public class BuzzBotIntake extends DefenderBotSystem {

    public DcMotor motor;
    public double currentPower = BuzzBotConfiguration.INTAKE_MOTOR_POWER_MAX;
    BuzzBotIntake(HardwareMap hm, DefenderBot b){

        super(hm, b);

        motor = hm.dcMotor.get(BuzzBotConfiguration.INTAKE_MOTOR_NAME);
        motor.setDirection(BuzzBotConfiguration.INTAKE_MOTOR_DIRECTION);
    }

    public void turnOn()
    {
       motor.setPower(currentPower);
    }
    public void turnOff()
    {
        motor.setPower(0);
    }

    public void changePower(double p){

        currentPower = Math.min(p, BuzzBotConfiguration.INTAKE_MOTOR_POWER_MAX);
        turnOn();

    }
    public void reverse(){

        motor.setPower(-currentPower);
    }
}

