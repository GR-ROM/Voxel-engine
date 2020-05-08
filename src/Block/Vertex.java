package Block;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class Vertex {
	public Vector3f positions, normals;
	public Vector2f uvs;
	public float light;
	
	public Vertex(Vector3f position, Vector2f uv, Vector3f normal, float light) {
		this.positions=position;
		this.uvs=uv;
		this.normals=normal;
		this.light=light;
	}
}
