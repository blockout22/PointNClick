package paint.n.click;

import ce.core.Camera;
import ce.core.Scene;
import ce.core.Window;
import ce.core.input.Key;
import ce.core.shader.Default3DShader;

public class PointNClick {
	
	private Window window;
	private Default3DShader shader;
	private Camera camera;
	
	public PointNClick() {
		init();
		loop();
		close();
	}

	private void init() {
		Scene scene = new Scene(800, 600);
		window = Window.createWindow(scene, "Point N Click");
		scene.setClearColor(1, 0.5f, 0, 1);
		window.enableDepthBuffer();
		
		camera = new Camera(window, 70f, 0.1f, 1000f);
		shader = new Default3DShader();
		shader.bind();
		shader.loadMatrix(shader.getProjectionMatrix(), camera.getProjectionMatrix());
		shader.unbind();
	}

	private void loop() {
		while(!window.isCloseRequested()) {
			camMovement();
			camera.update();
			shader.bind();
			shader.loadViewMatrix(camera);
			{
				//do terrain stuffs
			}
			shader.unbind();
			window.update();
		}
	}

	private void camMovement() {
		float SPEED = 0.01f;
		if (window.input.isKeyDown(Key.KEY_W)) {
			camera.getPosition().x += Math.sin(camera.getYaw() * Math.PI / 180) * SPEED; // * Time.getDelta();
			camera.getPosition().z += -Math.cos(camera.getYaw() * Math.PI / 180) * SPEED; // * Time.getDelta();
		}

		if (window.input.isKeyDown(Key.KEY_S)) {
			camera.getPosition().x -= Math.sin(camera.getYaw() * Math.PI / 180) * SPEED; // * Time.getDelta();
			camera.getPosition().z -= -Math.cos(camera.getYaw() * Math.PI / 180) * SPEED; // * Time.getDelta();
		}

		if (window.input.isKeyDown(Key.KEY_A)) {
			camera.getPosition().x += Math.sin((camera.getYaw() - 90) * Math.PI / 180) * SPEED; // * Time.getDelta();
			camera.getPosition().z += -Math.cos((camera.getYaw() - 90) * Math.PI / 180) * SPEED; // * Time.getDelta();
		}

		if (window.input.isKeyDown(Key.KEY_D)) {
			camera.getPosition().x += Math.sin((camera.getYaw() + 90) * Math.PI / 180) * SPEED; // * Time.getDelta();
			camera.getPosition().z += -Math.cos((camera.getYaw() + 90) * Math.PI / 180) * SPEED; // * Time.getDelta();
		}
	}

	private void close() {
		shader.dispose();
		window.close();
		window.disposeGLFW();
	}

	public static void main(String[] args) {
		new PointNClick();
	}

}
