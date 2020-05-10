package Block;

import org.lwjgl.util.vector.Vector3f;
import Engine.Loader;
import Models.TexturedModel;
import Textures.ModelTexture;

public class Chunk {
	
	public final static int CHUNK_WIDTH=16;
	public final static int CHUNK_SIZE_SQR=CHUNK_WIDTH*CHUNK_WIDTH;
	public final static int CHUNK_HEIGHT=16;
	public final static int CHUNK_SIZE_CUBE=CHUNK_SIZE_SQR*CHUNK_HEIGHT;
	public Block[] blocks;
	public Vector3f origin;
	public ChunkMesh chunkMesh;
	private Loader loader;
	private ModelTexture textureAtlas;
	private TexturedModel texturedModel;
	private boolean hasMesh;
	private Block emptyBlock;
	
	private void loadResources(ModelTexture texture) {
		this.emptyBlock=new Block(Block.AIR);
		this.chunkMesh=new ChunkMesh(this);
		this.loader=new Loader();
		this.setTextureAtlas(texture);
		this.setHasMesh(false);
		this.setTexturedModel(new TexturedModel(this.loader.loadToVAO(chunkMesh.positions, chunkMesh.normals, chunkMesh.uvs, chunkMesh.light), texture));		
	}
	
	public final static int coordToIndex(int x, int y, int z) {
		return x+(y*Chunk.CHUNK_WIDTH)+(z*Chunk.CHUNK_SIZE_SQR);
	}
	
	public Block getBlock(int x, int y, int z) {
		if ((x>=Chunk.CHUNK_WIDTH) || (x<0)) return emptyBlock;
		if ((y>=Chunk.CHUNK_WIDTH) || (y<0)) return emptyBlock;
		if ((z>=Chunk.CHUNK_WIDTH) || (z<0)) return emptyBlock;

		int index=x+(y*Chunk.CHUNK_WIDTH)+(z*(Chunk.CHUNK_WIDTH*Chunk.CHUNK_WIDTH));		
		if (blocks[index]==null) 
			return emptyBlock;
		Block block=blocks[index];
		return block;
	}
	
	public void setBlock(int x, int y, int z, Block block) {
		int index=x+(y*Chunk.CHUNK_WIDTH)+(z*(Chunk.CHUNK_WIDTH*Chunk.CHUNK_WIDTH));
		blocks[index]=block;
	}
	
	public Chunk(Vector3f origin, ModelTexture texture) {
		this.blocks=new Block[CHUNK_SIZE_CUBE];
		this.origin=origin;
		this.origin.y=0;
		setHasMesh(false);
		loadResources(texture);
	}
	
	public Chunk(int x, int z, ModelTexture texture) {
		this.blocks=new Block[Chunk.CHUNK_WIDTH*Chunk.CHUNK_WIDTH*Chunk.CHUNK_WIDTH];
		this.origin=new Vector3f(x, 0, z);
		setHasMesh(false);
		loadResources(texture);
	}
	
	public void updateMesh() {
		this.chunkMesh.update(this);
		this.setHasMesh(true);
		this.loader.reLoadToVAO(chunkMesh.positions, chunkMesh.normals, chunkMesh.uvs, chunkMesh.light);
	}
	
	public void renderChunk() {
		
	}
	
	public TexturedModel getTexturedModel() {
		return texturedModel;
	}

	public void setTexturedModel(TexturedModel texturedModel) {
		this.texturedModel = texturedModel;
	}

	public Block getBlock(int index) {
		return null;
	}
	
	public int setBlock(int index, Block block) {
		
		return 0;
	}

	public ModelTexture getTextureAtlas() {
		return textureAtlas;
	}

	public void setTextureAtlas(ModelTexture textureAtlas) {
		this.textureAtlas = textureAtlas;
	}

	public boolean isHasMesh() {
		return hasMesh;
	}

	public void setHasMesh(boolean hasMesh) {
		this.hasMesh = hasMesh;
	}
}
