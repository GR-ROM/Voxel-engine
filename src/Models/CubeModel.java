package Models;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class CubeModel {
	
public static Vector3f[] PX_POS = {
			new Vector3f(0.5f,-0.5f,0.5f),// 3	
			new Vector3f(0.5f,-0.5f,-0.5f),// 2
			new Vector3f(0.5f,0.5f,-0.5f), //1
			new Vector3f(0.5f,0.5f,-0.5f), // 3
			new Vector3f(0.5f,0.5f,0.5f), // 2
			new Vector3f(0.5f,-0.5f,0.5f) // 1
	};
	
	public static Vector3f[] NX_POS = {
			new Vector3f(-0.5f,0.5f,-0.5f),
			new Vector3f(-0.5f,-0.5f,-0.5f),
			new Vector3f(-0.5f,-0.5f,0.5f),
			new Vector3f(-0.5f,-0.5f,0.5f),
			new Vector3f(-0.5f,0.5f,0.5f),
			new Vector3f(-0.5f,0.5f,-0.5f)
	};
	
	public static Vector3f[] PY_POS = {
			new Vector3f(0.5f,0.5f,-0.5f), // 3
			new Vector3f(-0.5f,0.5f,-0.5f), // 2
			new Vector3f(-0.5f,0.5f,0.5f), // 1
			new Vector3f(-0.5f,0.5f,0.5f), //3
			new Vector3f(0.5f,0.5f,0.5f), // 2
			new Vector3f(0.5f,0.5f,-0.5f), // 1
	};
	
	public static Vector3f[] NY_POS = {
			new Vector3f(-0.5f,-0.5f,0.5f),
			new Vector3f(-0.5f,-0.5f,-0.5f),
			new Vector3f(0.5f,-0.5f,-0.5f),
			new Vector3f(0.5f,-0.5f,-0.5f),
			new Vector3f(0.5f,-0.5f,0.5f),
			new Vector3f(-0.5f,-0.5f,0.5f)
	};
	
	public static Vector3f[] PZ_POS = {
			new Vector3f(-0.5f,0.5f,0.5f),
			new Vector3f(-0.5f,-0.5f,0.5f),
			new Vector3f(0.5f,-0.5f,0.5f),
			new Vector3f(0.5f,-0.5f,0.5f),
			new Vector3f(0.5f,0.5f,0.5f),
			new Vector3f(-0.5f,0.5f,0.5f)
	};
	
	public static Vector3f[] NZ_POS = {
			new Vector3f(0.5f,-0.5f,-0.5f), // 3
			new Vector3f(-0.5f,-0.5f,-0.5f), // 2
			new Vector3f(-0.5f,0.5f,-0.5f), // 1
			new Vector3f(-0.5f,0.5f,-0.5f), // 3
			new Vector3f(0.5f,0.5f,-0.5f), // 2
			new Vector3f(0.5f,-0.5f,-0.5f) // 1			
	};
	
	
	public static Vector3f[] voxelVertices = {
		new Vector3f(0.0f, 0.0f, 0.0f),
		new Vector3f(1.0f, 0.0f, 0.0f),
		new Vector3f(1.0f, 1.0f, 0.0f),
		new Vector3f(0.0f, 1.0f, 0.0f),
		new Vector3f(0.0f, 0.0f, 1.0f),
		new Vector3f(1.0f, 0.0f, 1.0f),
		new Vector3f(1.0f, 1.0f, 1.0f),
		new Vector3f(0.0f, 1.0f, 1.0f)
	};
	
	public static int[][] voxelIndices = {
		{ 0, 3, 1, 1, 3, 2 }, // NZ 
		{ 5, 6, 4, 4, 6, 7 }, // PZ
		{ 3, 7, 2, 2, 7, 6 }, // PY
		{ 1, 5, 0, 0, 5, 4 }, // NY
		{ 4, 7, 0, 0, 7, 3 }, // PX
		{ 1, 1, 5, 5, 2, 6 }, // NX
	};
	
	public static Vector2f[] UV = {	
			new Vector2f(1.f, 1.f),new Vector2f(1.f, 0.f),
			new Vector2f(0.f, 0.f),
			
			
			new Vector2f(0.f, 0.f),new Vector2f(1.f, 0.f),new Vector2f(1.f, 1.f)
			
			
	};
	
public static Vector2f[] UV_PX = {
			
			// GRASS
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(4.f / 16.f, 0.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f)
			
	};
	
	public static Vector2f[] UV_NX = {
			
			// GRASS
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(4.f / 16.f, 0.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f)
			
	};

	public static Vector2f[] UV_PY = {
			
			// GRASS
			new Vector2f(0.f, 0.f),
			new Vector2f(0.f, 1.f / 16.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(0.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(5.f / 16.f, 0.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f)
			
	};
	
	public static Vector2f[] UV_NY = {
			
			// GRASS
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(5.f / 16.f, 0.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f)
			
	};
	
	public static Vector2f[] UV_PZ = {
			
			// GRASS
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(4.f / 16.f, 0.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f)
			
	};

	public static Vector2f[] UV_NZ = {
			
			// GRASS
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(4.f / 16.f, 0.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f)
		
	};
	
	public static Vector3f[] NORMALS = {
			
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f)
			
	};
	
	public static float[] vertices = {
			
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,0.5f,-0.5f,		
			
			-0.5f,0.5f,0.5f,	
			-0.5f,-0.5f,0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			0.5f,0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			-0.5f,-0.5f,0.5f,	
			-0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,0.5f,
			-0.5f,0.5f,-0.5f,
			0.5f,0.5f,-0.5f,
			0.5f,0.5f,0.5f,
			
			-0.5f,-0.5f,0.5f,
			-0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,0.5f
			
	};
	
	public static int[] indices = {
			
			0,1,3,	
			3,1,2,	
			4,5,7,
			7,5,6,
			8,9,11,
			11,9,10,
			12,13,15,
			15,13,14,	
			16,17,19,
			19,17,18,
			20,21,23,
			23,21,22
			
	};
	
	public static Vector2f[] uv = {
			new Vector2f(0f, 0f),
			new Vector2f(0f, 1f),
			new Vector2f(1f, 1f),
			new Vector2f(1f, 0f),
			
			new Vector2f(0f, 0f),
			new Vector2f(0f, 1f),
			new Vector2f(1f, 1f),
			new Vector2f(1f, 0f),
			
			new Vector2f(0f, 0f),
			new Vector2f(0f, 1f),
			new Vector2f(1f, 1f),
			new Vector2f(1f, 0f),
			
			new Vector2f(0f, 0f),
			new Vector2f(0f, 1f),
			new Vector2f(1f, 1f),
			new Vector2f(1f, 0f),
			
			new Vector2f(0f, 0f),
			new Vector2f(0f, 1f),
			new Vector2f(1f, 1f),
			new Vector2f(1f, 0f),
			
			new Vector2f(0f, 0f),
			new Vector2f(0f, 1f),
			new Vector2f(1f, 1f),
			new Vector2f(1f, 0f),
	};
	
}