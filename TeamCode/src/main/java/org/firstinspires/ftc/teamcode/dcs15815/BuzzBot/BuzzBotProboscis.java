package org.firstinspires.ftc.teamcode.dcs15815.BuzzBot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class BuzzBotProboscis extends DefenderBotSystem {
    public CRServo servoLeft, servoRight;
    public double currentPower = BuzzBotConfiguration.PROBOSCIS_SERVO_POWER_MAX;
    BuzzBotProboscis(HardwareMap hm, DefenderBot b){
        super(hm, b);

        servoLeft = hm.crservo.get(BuzzBotConfiguration.PROBOSCIS_SERVO_LEFT_NAME);
        servoRight = hm.crservo.get(BuzzBotConfiguration.PROBOSCIS_SERVO_RIGHT_NAME);

    }

    public void goOut(){
//        servoLeft.setPower(currentPower * BuzzBotConfiguration.PROBOSCIS_SERVO_LEFT_DIRECTION);
        servoRight.setPower(currentPower * BuzzBotConfiguration.PROBOSCIS_SERVO_RIGHT_DIRECTION);
    }
    public void goIn(){
//        servoLeft.setPower(-1 * currentPower * BuzzBotConfiguration.PROBOSCIS_SERVO_LEFT_DIRECTION);
        servoRight.setPower(-1 * currentPower * BuzzBotConfiguration.PROBOSCIS_SERVO_RIGHT_DIRECTION);
    }
    public void stop(){
//        servoLeft.setPower(0);
        servoRight.setPower(0);
    }
    public void changePower(double p){
        currentPower = Math.min(p, BuzzBotConfiguration.PROBOSCIS_SERVO_POWER_MAX);
    }

}
