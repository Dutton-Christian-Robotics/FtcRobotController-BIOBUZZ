package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class DefenderBotMotion {
    private double forward;
    private double strafe;
    private double rotate;


    public DefenderBotMotion(double f, double s, double r) {
        forward = f;
        strafe = s;
        rotate = r;
    }

    public static DefenderBotMotion make(double f, double s, double r) {
        return new DefenderBotMotion(f, s, r);
    }


    public double getForward() {
        return forward;
    }

    public double getStrafe() {
        return strafe;
    }

    public double getRotate() {
        return rotate;
    }

}