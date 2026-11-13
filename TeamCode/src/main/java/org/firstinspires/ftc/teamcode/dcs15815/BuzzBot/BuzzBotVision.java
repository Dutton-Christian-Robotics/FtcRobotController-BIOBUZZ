package org.firstinspires.ftc.teamcode.dcs15815.BuzzBot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.List;



public class BuzzBotVision extends DefenderBotSystem {

    enum HiveOrientation {
        UNKNOWN,
        AUDIENCE,
        SCORING
    }

    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    private AprilTagProcessor aprilTagProcessor;

    private VisionPortal visionPortal;


    BuzzBotVision(HardwareMap hm, DefenderBot b){
        super(hm, b);

        initAprilTagProcesor();
    }

    private void initAprilTagProcesor() {

        // Create the AprilTag processor the easy way.
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();

        // Create the vision portal the easy way.
        if (USE_WEBCAM) {
            visionPortal = VisionPortal.easyCreateWithDefaults(
                    hardwareMap.get(WebcamName.class, BuzzBotConfiguration.VISION_WEBCAM1_NAME), aprilTagProcessor);
        } else {
            visionPortal = VisionPortal.easyCreateWithDefaults(
                    BuiltinCameraDirection.BACK, aprilTagProcessor);
        }

    }

    public List<AprilTagDetection> getCurrentDetections() {
        return aprilTagProcessor.getDetections();
    }

    public int numberOfAprilTagsDetected() {
        return getCurrentDetections().size();
    }

    public boolean canFindAprilTag(String name) {
        boolean found = false;

        for (AprilTagDetection detection : getCurrentDetections()) {
            if (detection instanceof AprilTagClusterDetection) {
                AprilTagClusterDetection clusterDet = (AprilTagClusterDetection) detection;
                if (clusterDet.metadata.name == name) {
                    found = true;
                }

            }
        }
        return found;
    }

    public AprilTagClusterDetection findAprilTag(String name) {
        AprilTagClusterDetection cluster = null;

        for (AprilTagDetection detection : getCurrentDetections()) {
            if (detection instanceof AprilTagClusterDetection) {
                AprilTagClusterDetection clusterDet = (AprilTagClusterDetection) detection;
                if (clusterDet.metadata.name == name) {
                    cluster = clusterDet;
                }

            }
        }
        return cluster;
    }

    public boolean canSeeRedAudienceAprilTag() {
        return canFindAprilTag("RED AUDIENCE");

    }

    public boolean canSeeRedScoringAprilTag() {
        return canFindAprilTag("RED SCORING");

    }

    public boolean canSeeBlueAudienceAprilTag() {
        return canFindAprilTag("BLUE AUDIENCE");
    }

    public boolean canSeeBlueScoringAprilTag() {
        return canFindAprilTag("BLUE SCORING");

    }

    public boolean isAprilTagUpsideDown(AprilTagClusterDetection cluster) {
        return cluster.ftcPose.roll > 90 || cluster.ftcPose.roll < -90;
    }

    public HiveOrientation redHiveOrientation() {
        AprilTagClusterDetection cluster = null;

        if (canSeeRedAudienceAprilTag()) {
            cluster = findAprilTag("RED AUDIENCE");

            if (cluster == null) {
                return HiveOrientation.UNKNOWN;
            } else if (isAprilTagUpsideDown(cluster)) {
                return HiveOrientation.SCORING;
            } else {
                return HiveOrientation.AUDIENCE;
            }

        } else if (canSeeRedScoringAprilTag()) {
            cluster = findAprilTag("RED SCORING");
            if (cluster == null) {
                return HiveOrientation.UNKNOWN;
            } else if (isAprilTagUpsideDown(cluster)) {
                return HiveOrientation.AUDIENCE;
            } else {
                return HiveOrientation.SCORING;
            }

        } else {
            return HiveOrientation.UNKNOWN;
        }

    }

    public HiveOrientation blueHiveOrientation() {
        AprilTagClusterDetection cluster = null;

        if (canSeeBlueAudienceAprilTag()) {
            cluster = findAprilTag("BLUE AUDIENCE");
            if (cluster == null) {
                return HiveOrientation.UNKNOWN;
            } else if (isAprilTagUpsideDown(cluster)) {
                return HiveOrientation.SCORING;
            } else {
                return HiveOrientation.AUDIENCE;
            }

        } else if (canSeeBlueScoringAprilTag()) {
            cluster = findAprilTag("BLUE SCORING");
            if (cluster == null) {
                return HiveOrientation.UNKNOWN;
            } else if (isAprilTagUpsideDown(cluster)) {
                return HiveOrientation.AUDIENCE;
            } else {
                return HiveOrientation.SCORING;
            }

        } else {
            return HiveOrientation.UNKNOWN;
        }

    }

    public DefenderAlliance.Color inferAllianceStartingUp() {

        if (canSeeRedAudienceAprilTag()) {
            return DefenderAlliance.Color.RED;
        } else if (canSeeBlueScoringAprilTag()) {
            return DefenderAlliance.Color.BLUE;
        } else {
            return DefenderAlliance.Color.UNKNOWN;
        }
    }

    public DefenderAlliance.Color inferAllianceStartingDown() {

        if (canSeeRedAudienceAprilTag()) {
            return DefenderAlliance.Color.BLUE;
        } else if (canSeeBlueScoringAprilTag()) {
            return DefenderAlliance.Color.RED;
        } else {
            return DefenderAlliance.Color.UNKNOWN;
        }
    }




}

