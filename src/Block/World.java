package Block;

import java.util.Map;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import Textures.ModelTexture;

public class World {
	
	public Map<Vector2f, Chunk> chunks;
	private ModelTexture textureAtlas;

	public World(Map<Vector2f, Chunk> chunks, ModelTexture textureAtlas) {
		this.chunks=chunks;
		this.setTextureAtlas(textureAtlas);
	}
	
	public Chunk getChunkAt(Vector3f cubePosition) {
		Vector2f pos=new Vector2f(cubePosition.x, cubePosition.z);
		pos.x=(int)pos.x / Chunk.CHUNK_SIZE;
		pos.y=(int)pos.y / Chunk.CHUNK_SIZE;
		Chunk chunk=chunks.get(pos);
		return chunk;
	}
	
	public Block getBlock(Vector3f cubePosition) {		
		Chunk chunk=getChunkAt(cubePosition);
		int x=(int)cubePosition.x;
		int y=(int)cubePosition.y;
		int z=(int)cubePosition.z;
		if (chunk==null) return null;
		Block block=chunk.getBlock(x+(y*Chunk.CHUNK_SIZE)+(z*Chunk.CHUNK_SIZE_SQR));
		return block;
	}
	
	public Block getBlock(int x, int  y, int z) {		
		Chunk chunk=getChunkAt(new Vector3f(x, y, z));
		if (chunk==null) return null;
		Block block=chunk.getBlock(x+(y*Chunk.CHUNK_SIZE)+(z*Chunk.CHUNK_SIZE_SQR));
		return block;
	}
	
	public Block setBlock(int x, int y, int z, Block block) {
		Chunk chunk=getChunkAt(new Vector3f(x, y, z));
		if (chunk==null) return null;
		chunk.setBlock(x+(y*Chunk.CHUNK_SIZE)+(z*Chunk.CHUNK_SIZE_SQR), block);
		return block;
	}
	
	public Map<Vector2f, Chunk> getChunks() {
		return chunks;
	}

	public void setChunks(Map<Vector2f, Chunk> chunks) {
		this.chunks = chunks;
	}

	public ModelTexture getTextureAtlas() {
		return textureAtlas;
	}

	public void setTextureAtlas(ModelTexture textureAtlas) {
		this.textureAtlas = textureAtlas;
	}
	
}
