package org.firstinspires.ftc.teamcode.dcs15815.BuzzBot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class BuzzBotShooter extends DefenderBotSystem  {

    public DcMotorEx motorLeft, motorRight, motorLift;
    public double currentShooterPower = BuzzBotConfiguration.SHOOTER_MOTOR_POWER_START;

    BuzzBotShooter(HardwareMap hm, DefenderBot b) {
        super(hm, b);

        motorLeft = hm.get(DcMotorEx.class, BuzzBotConfiguration.SHOOTER_MOTOR_LEFT_NAME);
        motorRight = hm.get(DcMotorEx.class, BuzzBotConfiguration.SHOOTER_MOTOR_RIGHT_NAME);
        motorLift = hm.get(DcMotorEx.class, BuzzBotConfiguration.SHOOTER_MOTOR_LIFT_NAME);
        motorLeft.setDirection(BuzzBotConfiguration.SHOOTER_MOTOR_LEFT_DIRECTION);
        motorRight.setDirection(BuzzBotConfiguration.SHOOTER_MOTOR_RIGHT_DIRECTION);
        motorLift.setDirection(BuzzBotConfiguration.SHOOTER_MOTOR_LIFT_DIRECTION);
    }
    public void turnOn() {
        motorLeft.setVelocity(currentShooterPower * BuzzBotConfiguration.SHOOTER_MOTOR_VELOCITY_MAX);
        motorRight.setVelocity(currentShooterPower * BuzzBotConfiguration.SHOOTER_MOTOR_VELOCITY_MAX);
    }
    public void turnOff() {
        motorLeft.setVelocity(0);
        motorRight.setVelocity(0);
    }
    public void turnOnLift() {
        motorLift.setPower(BuzzBotConfiguration.SHOOTER_MOTOR_LIFT_POWER_MAX);
    }
    public void turnOffLift() {
        motorLift.setPower(0);
    }
    public void changeShooterPower(double p) {
        currentShooterPower = Math.min(p, BuzzBotConfiguration.SHOOTER_MOTOR_POWER_MAX);
        turnOn();
    }

    public void beginShooting() {
        turnOn();
        sleep(BuzzBotConfiguration.SHOOTER_TIME_AFTER_MOTOR_START);
        turnOnLift();
    }
    public void endShooting() {
        turnOff();
        turnOffLift();
    }
}
