package Engine;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class Camera {
	private Vector4f position;
	private float pitch;
	private float yaw;
	
	private Vector4f forward;
	private Vector3f right;
	
	private float speed=0.1f;
	private float moveAt=0;
	private float turnSpeed=1f;
	
	public Camera() {
		position = new Vector4f(0, 0, 0, 1);
		forward=new Vector4f(1, 1, 1, 1);
		yaw=0;
		pitch=0;
	}
	
	public Vector4f getPosition() {
		return position;
	}
	
	public void setPosition(float x, float y, float z) {
		position.x = x;
		position.y = y;
		position.z = z;
	}
	
	public Vector4f getLookVector() {
		Matrix4f matrix = new Matrix4f();
		Vector4f forward=new Vector4f();
		matrix.setIdentity();
		forward.set(0, 0, -1, 0);
		Matrix4f.rotate((float)Math.toRadians(this.pitch), new Vector3f(1, 0, 0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(this.yaw), new Vector3f(0, 1, 0), matrix, matrix);	
		matrix.invert();
		Matrix4f.transform(matrix, forward, forward);
		return forward;
	}
	
	public void move() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			moveAt=speed;
		} else
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			moveAt=-speed;
		} else	{
			moveAt=0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y+=speed;
		} else
		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			position.y-=speed;
		}
		
		this.pitch+=-Mouse.getDY()*turnSpeed;
		if (pitch>89f) pitch = 89f;
		if (pitch<-89f) pitch = -89f;
		this.yaw+=Mouse.getDX()*turnSpeed;
		
		forward=getLookVector();
		forward.x*=moveAt;
		forward.y*=moveAt;
		forward.z*=moveAt;
		Vector4f.add(position, forward, position);
	}
	

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}
}
