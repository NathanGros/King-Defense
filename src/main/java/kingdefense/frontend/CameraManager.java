package kingdefense.frontend;

import static com.raylib.Raylib.*;
import static java.lang.Math.*;

public class CameraManager {
    private Camera3D camera;
	private Float angle;
    private Float distance;
    private Float cameraX;
    private Float cameraY;
    private Float cameraZ;
    private Float targetX;
    private Float targetY;
    private Float targetZ;

    public Camera3D getCamera() {
		return camera;
	}

    public CameraManager() {
        angle = 0.f;
        distance = 15.f;
        targetX = 3.5f;
        targetY = 0.f;
        targetZ = 3.5f;
        cameraX = targetX + distance * (float) cos(angle);
        cameraY = targetY + distance;
        cameraZ = targetZ + distance * (float) sin(angle);
        camera = new Camera3D()
            ._position(new Vector3().x(cameraX).y(cameraY).z(cameraZ))
            .target(new Vector3().x(targetX).y(targetY).z(targetZ))
            .up(new Vector3().x(0).y(1).z(0))
            .fovy(distance)
            .projection(CAMERA_ORTHOGRAPHIC);
    }

    private void updateCameraPos() {
        cameraX = targetX + distance * (float) cos(angle);
        cameraY = targetY + distance;
        cameraZ = targetZ + distance * (float) sin(angle);
        camera._position().x(cameraX).y(cameraY).z(cameraZ);
        camera.target().x(targetX).y(targetY).z(targetZ);
    }

    public void rotate() {
        Float circleEighth = (float) (Math.PI / 4.);
        angle = (angle / circleEighth - 1) * circleEighth;
        updateCameraPos();
    }

    public void zoomIn() {
        distance /= 1.5f;
        if (distance < 5.f) distance = 5.f;
        camera.fovy(distance);
        updateCameraPos();
    }

    public void zoomOut() {
        distance *= 1.5f;
        camera.fovy(distance);
        if (distance > 40.f) distance = 40.f;
        updateCameraPos();
    }
}
