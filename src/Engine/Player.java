package Engine;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import Block.Block;
import Block.Chunk;
import Block.ChunkMesh;
import Entities.Entity;

public class Player {

	public Player() {
		
	}
	
	public Vector3f getBlockLookAt(Camera camera, Map<Vector2f, Chunk> chunks, int distance) {
		Vector3f target=new Vector3f();
		Vector3f pos=new Vector3f();
		Vector3f dir=new Vector3f();;
		pos.x=camera.getPosition().x;
		pos.y=camera.getPosition().y;
		pos.z=camera.getPosition().z;
		
		
		target=pos;
		int x = 0, z = 0;
		for (int i=0;i!=distance;i++) {
			Vector3f.add(target, dir, target);
			x=(int)target.x / 32;
			z=(int)target.z / 32;
	        Chunk chunk=chunks.get(new Vector2f(x, z));
	        if (chunk!=null) {
	        	Block block=chunk.blocks.get(new Vector3f((int)(target.x % 32), (int)(target.y  % 32), (int)(target.z  % 32)));
	        	if (block!=null) Display.setTitle("Block at x="+target.x+" y="+target.y+" z="+target.z);
	        } 
		}
		Display.setTitle("Chunk not detected x: "+x+" z: "+z);
		
		return target;
	}
	
}
