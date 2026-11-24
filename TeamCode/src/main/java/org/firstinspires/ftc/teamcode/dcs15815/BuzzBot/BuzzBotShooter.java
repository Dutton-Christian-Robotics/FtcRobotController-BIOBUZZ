package org.firstinspires.ftc.teamcode.dcs15815.BuzzBot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BuzzBotShooter extends DefenderBotSystem  {

    public DcMotorEx motorLeft, motorRight, motorLift;
    public double currentShooterPower = BuzzBotConfiguration.SHOOTER_MOTOR_POWER_START;

    public Servo gateServoLeft, gateServoRight, agitatorServoLeft, agitatorServoRight;

    private BuzzBotAgitatorCycle agitatorCycleRunnable = new BuzzBotAgitatorCycle();
    private Thread agitatorCycleThread;


    BuzzBotShooter(HardwareMap hm, DefenderBot b) {
        super(hm, b);

        motorLeft = hm.get(DcMotorEx.class, BuzzBotConfiguration.SHOOTER_MOTOR_LEFT_NAME);
        motorRight = hm.get(DcMotorEx.class, BuzzBotConfiguration.SHOOTER_MOTOR_RIGHT_NAME);
        motorLift = hm.get(DcMotorEx.class, BuzzBotConfiguration.SHOOTER_MOTOR_LIFT_NAME);
        motorLeft.setDirection(BuzzBotConfiguration.SHOOTER_MOTOR_LEFT_DIRECTION);
        motorRight.setDirection(BuzzBotConfiguration.SHOOTER_MOTOR_RIGHT_DIRECTION);
        motorLift.setDirection(BuzzBotConfiguration.SHOOTER_MOTOR_LIFT_DIRECTION);

        gateServoLeft = hm.servo.get(BuzzBotConfiguration.SHOOTER_GATE_SERVO_LEFT_NAME);
        gateServoRight = hm.servo.get(BuzzBotConfiguration.SHOOTER_GATE_SERVO_RIGHT_NAME);

        agitatorServoLeft = hm.servo.get(BuzzBotConfiguration.SHOOTER_AGITATOR_SERVO_LEFT_NAME);
        agitatorServoRight = hm.servo.get(BuzzBotConfiguration.SHOOTER_AGITATOR_SERVO_RIGHT_NAME);

        agitatorCycleRunnable.setShooter(this);
        agitatorCycleThread =  new Thread(agitatorCycleRunnable);
        agitatorCycleThread.start();

        moveAgitatorToRestPosition();

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
        moveGateToShootPosition();
        startAgitatorCycle();
    }
    public void endShooting() {
        stopAgitatorCycle();
        moveGateToBlockPosition();
        turnOff();
        turnOffLift();
    }

    public void shootAutonomously() {
        beginShooting();
        sleep(BuzzBotConfiguration.SHOOTER_SHOOT_TIME_AUTONOMOUS);
        endShooting();
    }

    public void moveAgitatorToRestPosition() {
        agitatorServoLeft.setPosition(BuzzBotConfiguration.SHOOTER_AGITATOR_SERVO_LEFT_POSITION_REST);
        agitatorServoRight.setPosition(BuzzBotConfiguration.SHOOTER_AGITATOR_SERVO_RIGHT_POSITION_REST);
    }

    public void moveAgitatorToPushPosition() {
        agitatorServoLeft.setPosition(BuzzBotConfiguration.SHOOTER_AGITATOR_SERVO_LEFT_POSITION_PUSH);
        agitatorServoRight.setPosition(BuzzBotConfiguration.SHOOTER_AGITATOR_SERVO_RIGHT_POSITION_PUSH);
    }

    public void startAgitatorCycle() {
        agitatorCycleRunnable.start();
    }

    public void stopAgitatorCycle() {
       agitatorCycleRunnable.stop();
    }


    public void moveGateToShootPosition() {
        gateServoLeft.setPosition(BuzzBotConfiguration.SHOOTER_GATE_SERVO_LEFT_POSITION_SHOOT);
        gateServoRight.setPosition(BuzzBotConfiguration.SHOOTER_GATE_SERVO_RIGHT_POSITION_SHOOT);
    }

    public void moveGateToBlockPosition() {
        gateServoLeft.setPosition(BuzzBotConfiguration.SHOOTER_GATE_SERVO_LEFT_POSITION_BLOCK);
        gateServoRight.setPosition(BuzzBotConfiguration.SHOOTER_GATE_SERVO_RIGHT_POSITION_BLOCK);
    }

    public void moveGateToOuttakePosition() {
        gateServoLeft.setPosition(BuzzBotConfiguration.SHOOTER_GATE_SERVO_LEFT_POSITION_OUTTAKE);
        gateServoRight.setPosition(BuzzBotConfiguration.SHOOTER_GATE_SERVO_RIGHT_POSITION_OUTTAKE);
    }

}
