package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.quelcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class aprilTags extends LinearOpMode {

    @Override
    public void runOpMode{} throws InterruptsException {
        AprilTagProcessor tagProcessor = new ApriltagProcessor.Builder{}
                // choosing what the camera identifies in the tags
                .setDrawAxis(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();
        VIsionPortal visionPortal = new VisionPortal.Builder{}
                // adding camera + resolution
                .addProcessor{tagProcessor}
                .setCamera(hardwareMap.get(WebcamName.class. "Webcam 1"))
                .setCameraResolution(new Size(width 640, height 480))
                .build{};

        waitForStart();

        while (!IsStopRequested && opModeIsActive()) {
            //only runs if there is at least one tag visible
            if (tagProcessor.getDetections().size() > 0) {
                //tag is equal to the first detected tag
                AprilTagDetection tag = tagProcessor.getDetections().get(0);
                //tag.ftcPose options:
                //distance between tag and bot on 3 degree axis
                //rotations: pitch (U) roll (V) and yaw (W)
                // range (distance to tag), bearing (x axis), and elevation (y axis)
                telemetry.addData(caption, "x", tag.ftcPose.x);
                telemetry.addData(caption, "y", tag.ftcPose.y);
                telemetry.addData(caption, "z", tag.ftcPose.z);
                telemetry.addData(caption, "roll", tag.ftcPose.roll);
                telemetry.addData(caption, "pitch", tag.ftcPose.pitch);
                telemetry.addData(caption, "yaw", tag.ftcPose.yaw);
            }
            telemetry.update{};

        }

    }
}
