package Block;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;import org.lwjgl.util.vector.Vector4f;

import Models.CubeModel;

public class ChunkMesh {
    private List<Vertex> vertices;
    //private List<Integer> indicesList;
	private List<Float> positionsList;
	private List<Float> uvsList;
	private List<Float> normalsList;
	private List<Float> lightsList;
	
	protected final int LIGHT_MAX_RADIUS=16;
	protected final int LIGHT_MAX_HALF_RADIUS=LIGHT_MAX_RADIUS/2;
	
	public float[] positions, uvs, normals, light;
	public int[] indices;
	
	public ChunkMesh(Chunk chunk) {

		vertices = new ArrayList<Vertex>();
		positionsList = new ArrayList<Float>();
		//indicesList = new ArrayList<Integer>();
		uvsList = new ArrayList<Float>();
		normalsList = new ArrayList<Float>();
		lightsList=new ArrayList<Float>();
		
		buildMesh(chunk);
		populateLists();
		
	}
	
	public void update(Chunk chunk) {
		//applyLights(chunk);
		buildMesh(chunk);
		populateLists();	
	}
	

	private int getDistanceBetweenVectors(Vector3f a, Vector3f b) {
		int length=0;
		Vector3f c=new Vector3f(0,0,0);
		Vector3f.sub(a, b, c);
		length=(int)c.length();
		return length;
	}
	
	private void buildMesh(Chunk chunk) {
		Vector3f blockPos=new Vector3f(0, 0, 0);
		Vector3f t=new Vector3f(0, 0, 0);
		Vector3f lightPos=new Vector3f(0f, 0f, 0f);
		Vector4f lightVec=new Vector4f(0.4f, 0.4f, 0.4f, 1f);
		Block block;
			for (int x=0; x!=Chunk.CHUNK_WIDTH; x++) {
				for (int y=0; y!=Chunk.CHUNK_WIDTH ; y++) {
					for (int z=0; z!=Chunk.CHUNK_WIDTH; z++) {
						boolean px=true; boolean py=true; boolean pz=true; 
						boolean nx=true; boolean ny=true; boolean nz=true;
						block=chunk.getBlock(x, y, z);
						blockPos.x=x;
						blockPos.y=y;
						blockPos.z=z;
						if (block==null) break;
						if (block.type==Block.AIR) break;
						if (chunk.getBlock(x+1, y, z).transparent) { px=false; }
						if (chunk.getBlock(x-1, y, z).transparent) { nx=false; }
						if (chunk.getBlock(x, y+1, z).transparent) { py=false; }
						if (chunk.getBlock(x, y-1, z).transparent) { ny=false; }
						if (chunk.getBlock(x, y, z+1).transparent) { pz=false; }
						if (chunk.getBlock(x, y, z-1).transparent) { nz=false; }
					 	
						if (!px) {
							for (int k=0;k!=6;k++) {
								Vector3f.add(blockPos, CubeModel.PX_POS[k], t);
								vertices.add(new Vertex(new Vector3f(t), CubeModel.getUVByIndex(CubeModel.blockTypeToAtlasTextureIndex(block.type, CubeModel.SIDE), k), CubeModel.NORMALS[0], 10));
							}
						}
						if (!py) {  
							for (int k=0;k!=6;k++) { 
								Vector3f.add(blockPos, CubeModel.PY_POS[k], t);
								vertices.add(new Vertex(new Vector3f(t), CubeModel.getUVByIndex(CubeModel.blockTypeToAtlasTextureIndex(block.type, CubeModel.TOP), k), CubeModel.NORMALS[1], 12));
							}
						}
						if (!pz) {  
							for (int k=0;k!=6;k++) { 
								Vector3f.add(blockPos, CubeModel.PZ_POS[k], t);
								vertices.add(new Vertex(new Vector3f(t), CubeModel.getUVByIndex(CubeModel.blockTypeToAtlasTextureIndex(block.type, CubeModel.SIDE), k), CubeModel.NORMALS[2], 5));
							}
						}
						if (!nx) {  
							for (int k=0;k!=6;k++) {
								Vector3f.add(blockPos, CubeModel.NX_POS[k], t);
								vertices.add(new Vertex(new Vector3f(t), CubeModel.getUVByIndex(CubeModel.blockTypeToAtlasTextureIndex(block.type, CubeModel.SIDE), k), CubeModel.NORMALS[3], 5));
							}
						}
						if (!ny) {  
							for (int k=0;k!=6;k++) {
								Vector3f.add(blockPos, CubeModel.NY_POS[k], t);
								vertices.add(new Vertex(new Vector3f(t), CubeModel.getUVByIndex(CubeModel.blockTypeToAtlasTextureIndex(block.type, CubeModel.BOTTOM), k), CubeModel.NORMALS[4], 5));
							}
						}
						if (!nz) {  
							for (int k=0;k!=6;k++) {
								Vector3f.add(blockPos, CubeModel.NZ_POS[k], t);
								vertices.add(new Vertex(new Vector3f(t), CubeModel.getUVByIndex(CubeModel.blockTypeToAtlasTextureIndex(block.type, CubeModel.SIDE), k), CubeModel.NORMALS[5], 5));
							}
						}
					}
				}
			}
		}
	
	private void populateLists() {
		for (int i=0; i!=vertices.size(); i++) {			
			positionsList.add(vertices.get(i).positions.x);
			positionsList.add(vertices.get(i).positions.y);
			positionsList.add(vertices.get(i).positions.z);
			uvsList.add(vertices.get(i).uvs.x);
			uvsList.add(vertices.get(i).uvs.y);
			normalsList.add(vertices.get(i).normals.x);
			normalsList.add(vertices.get(i).normals.y);
			normalsList.add(vertices.get(i).normals.z);
			lightsList.add(vertices.get(i).light);
		}
		
		positions = new float[positionsList.size()];
		uvs = new float[uvsList.size()];
		normals = new float[normalsList.size()];
		light = new float[lightsList.size()];
	//	indices = new int[indicesList.size()];
		
		for (int i = 0; i < positionsList.size(); i++) positions[i] = positionsList.get(i);
		for (int i = 0; i < uvsList.size(); i++) uvs[i] = uvsList.get(i);
		for (int i = 0; i < normalsList.size(); i++) normals[i] = normalsList.get(i);		
		for (int i = 0; i != lightsList.size(); i++) light[i]=lightsList.get(i);
		//for (int i = 0; i < indicesList.size(); i++) indices[i] = indicesList.get(i);
		cleanup();
	}
	
	public void cleanup() {
    	normalsList.clear();
    	uvsList.clear();
    	positionsList.clear();
    	vertices.clear();
    	lightsList.clear();
    //	indicesList.clear();
    }
	
}
