package Block;

import java.util.Map;
import org.lwjgl.util.vector.Vector3f;
import Textures.ModelTexture;
import Toolbox.Vector2i;

public class World {
	
	public Map<Vector2i, Chunk> chunks;
	private ModelTexture textureAtlas;

	public World(Map<Vector2i, Chunk> chunks, ModelTexture textureAtlas) {
		this.chunks=chunks;
		this.setTextureAtlas(textureAtlas);
	}
	
	public Chunk getChunkAt(Vector3f cubePosition) {
		Vector2i pos=new Vector2i((int)Math.floor(cubePosition.x / Chunk.CHUNK_WIDTH), (int)Math.floor(cubePosition.z / Chunk.CHUNK_WIDTH));
		Chunk chunk=chunks.get(pos);
		return chunk;
	}
	
	public Chunk getChunkAt(int x, int z) {
		Vector2i pos=new Vector2i((int)Math.floor(x / Chunk.CHUNK_WIDTH), (int)Math.floor(z / Chunk.CHUNK_WIDTH));
		Chunk chunk=chunks.get(pos);
		return chunk;
	}
	
	public Block getBlock(Vector3f cubePosition) {		
		Chunk chunk=getChunkAt(cubePosition);
		int x=(int)cubePosition.x;
		int y=(int)cubePosition.y;
		int z=(int)cubePosition.z;
		if (chunk==null) return null;
		Block block=chunk.getBlock(x % Chunk.CHUNK_WIDTH, y, z % Chunk.CHUNK_WIDTH);
		return block;
	}
	
	public Block getBlock(int x, int  y, int z) {		
		Chunk chunk=getChunkAt(new Vector3f(x, y, z));
		if (chunk==null) return null;
		Block block=chunk.getBlock(x % Chunk.CHUNK_WIDTH, y, z % Chunk.CHUNK_WIDTH);
		return block;
	}
	
	public Block setBlock(int x, int y, int z, Block block) {
		Chunk chunk=getChunkAt(x, z);
		if (chunk==null) return null;
		chunk.setBlock(x % Chunk.CHUNK_WIDTH, y, z % Chunk.CHUNK_WIDTH, block);
		return block;
	}
	
	public Map<Vector2i, Chunk> getChunks() {
		return chunks;
	}

	public void setChunks(Map<Vector2i, Chunk> chunks) {
		this.chunks = chunks;
	}

	public ModelTexture getTextureAtlas() {
		return textureAtlas;
	}

	public void setTextureAtlas(ModelTexture textureAtlas) {
		this.textureAtlas = textureAtlas;
	}

	public void spawnNewChunk(int x, int z) {
		Chunk chunk=new Chunk(x, z, textureAtlas); 
		this.chunks.put(new Vector2i((int)Math.floor(x / Chunk.CHUNK_WIDTH), (int)Math.floor(z / Chunk.CHUNK_WIDTH)), chunk);		 
	}
	
}
