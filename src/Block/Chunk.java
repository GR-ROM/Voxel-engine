package Block;

import org.lwjgl.util.vector.Vector3f;
import Engine.Loader;
import Models.TexturedModel;
import Textures.ModelTexture;

public class Chunk {
	
	public final static int CHUNK_SIZE=64;
	public final static int CHUNK_SIZE_SQR=1025;
	public Block[] blocks;
	public Vector3f origin;
	public ChunkMesh chunkMesh;
	private Loader loader;
	private ModelTexture textureAtlas;
	private TexturedModel texturedModel;
	private boolean hasMesh;
	
	private void loadResources(ModelTexture texture) {
		this.chunkMesh=new ChunkMesh(this);
		this.loader=new Loader();
		this.setTextureAtlas(texture);
		this.setHasMesh(false);
		this.setTexturedModel(new TexturedModel(this.loader.loadToVAO(chunkMesh.positions, chunkMesh.uvs, chunkMesh.light), texture));		
	}
	
	public Chunk(Vector3f origin, ModelTexture texture) {
		this.blocks=new Block[CHUNK_SIZE*CHUNK_SIZE_SQR];
		this.origin=origin;
		this.origin.y=0;
		setHasMesh(false);
		loadResources(texture);
	}
	
	public Chunk(int x, int z, ModelTexture texture) {
		this.blocks=new Block[CHUNK_SIZE*CHUNK_SIZE_SQR];
		this.origin.x=x;
		this.origin.z=z;
		this.origin.y=0;
		loadResources(texture);
	}
	
	public void updateMesh() {
		this.chunkMesh.update(this);
		this.setHasMesh(true);
		this.loader.reLoadToVAO(chunkMesh.positions, chunkMesh.uvs, chunkMesh.light);
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
