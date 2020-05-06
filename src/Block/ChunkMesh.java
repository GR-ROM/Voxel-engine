package Block;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;import org.lwjgl.util.vector.Vector4f;

import Models.CubeModel;

public class ChunkMesh {
    private List<Vertex> vertices;
    private List<Integer> indicesList;
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
		indicesList = new ArrayList<Integer>();
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
	
	private Vector4f lightToVector(int light) {
		float baseColor = .086f;
		float colorValue = (float) (Math.pow(light / 16f, 1.4f) + baseColor );
		return new Vector4f(colorValue, colorValue, colorValue, 1);
	}
	
	private void buildMesh(Chunk chunk) {
		Vector3f lightPos=new Vector3f(0f, 0f, 0f);
		Vector3f blockPos=new Vector3f(0f, 0f, 0f);
		Vector4f lightVec=new Vector4f(0.1f, 0.1f, 0.1f, 1f);
			for (int i = 0; i < chunk.blocks.size(); i++) {
				Block blockI = chunk.blocks.get(i);
				boolean px = false, nx = false, py = false, ny = false, pz = false, nz = false;
				for (int j = 0; j < chunk.blocks.size(); j++) {
					Block blockJ = chunk.blocks.get(j);
					
					if (((blockI.x+1)==(blockJ.x)) && ((blockI.y)==(blockJ.y)) && ((blockI.z)==(blockJ.z))) px=true; 
					if (((blockI.x-1)==(blockJ.x)) && ((blockI.y)==(blockJ.y)) && ((blockI.z)==(blockJ.z))) nx=true; 
					if (((blockI.x)==(blockJ.x)) && ((blockI.y+1)==(blockJ.y)) && ((blockI.z)==(blockJ.z))) py=true; 
					if (((blockI.x)==(blockJ.x)) && ((blockI.y-1)==(blockJ.y)) && ((blockI.z)==(blockJ.z))) ny=true; 
					if (((blockI.x)==(blockJ.x)) && ((blockI.y)==(blockJ.y)) && ((blockI.z+1)==(blockJ.z))) pz=true; 
					if (((blockI.x)==(blockJ.x)) && ((blockI.y)==(blockJ.y)) && ((blockI.z-1)==(blockJ.z))) nz=true; 
			}
			for (int l=0;l!=chunk.lights.size();l++) {
				Light light=chunk.lights.get(l);
				lightPos.x=light.x;
				lightPos.y=light.y;
				lightPos.z=light.z;
					
				blockPos.x=blockI.x;
				blockPos.y=blockI.y;
				blockPos.z=blockI.z;
					
				int distToLight=getDistanceBetweenVectors(blockPos, lightPos);
				short lightLevel=(short)(light.lightLevel-distToLight);
				if (distToLight<16) {
						blockI.lightLevel=(short)(lightLevel);
				}					
			}
				
			if (!px) {
				for (int k=0;k!=6;k++) {
					vertices.add(new Vertex(new Vector3f(CubeModel.PX_POS[k].x+blockI.x, CubeModel.PX_POS[k].y+blockI.y, CubeModel.PX_POS[k].z+blockI.z), CubeModel.UV[k], CubeModel.NORMALS[k], lightToVector(blockI.lightLevel)));
				}
			}
			if (!py) {  
				for (int k=0;k!=6;k++) {
					vertices.add(new Vertex(new Vector3f(CubeModel.PY_POS[k].x+blockI.x, CubeModel.PY_POS[k].y+blockI.y, CubeModel.PY_POS[k].z+blockI.z), CubeModel.UV[k], CubeModel.NORMALS[k],  lightToVector(blockI.lightLevel)));
				}
			}
			if (!pz) {  
				for (int k=0;k!=6;k++) {
					vertices.add(new Vertex(new Vector3f(CubeModel.PZ_POS[k].x+blockI.x, CubeModel.PZ_POS[k].y+blockI.y, CubeModel.PZ_POS[k].z+blockI.z), CubeModel.UV[k], CubeModel.NORMALS[k],  lightToVector(blockI.lightLevel)));
				}
			}
			if (!nx) {  
				for (int k=0;k!=6;k++) {
					vertices.add(new Vertex(new Vector3f(CubeModel.NX_POS[k].x+blockI.x, CubeModel.NX_POS[k].y+blockI.y, CubeModel.NX_POS[k].z+blockI.z), CubeModel.UV[k], CubeModel.NORMALS[k],  lightToVector(blockI.lightLevel)));
				}
			}
			if (!ny) {  
				for (int k=0;k!=6;k++) {
					vertices.add(new Vertex(new Vector3f(CubeModel.NY_POS[k].x+blockI.x, CubeModel.NY_POS[k].y+blockI.y, CubeModel.NY_POS[k].z+blockI.z), CubeModel.UV[k], CubeModel.NORMALS[k],  lightToVector(blockI.lightLevel)));
				}
			}
			if (!nz) {  
				for (int k=0;k!=6;k++) {
					vertices.add(new Vertex(new Vector3f(CubeModel.NZ_POS[k].x+blockI.x, CubeModel.NZ_POS[k].y+blockI.y, CubeModel.NZ_POS[k].z+blockI.z), CubeModel.UV[k], CubeModel.NORMALS[k],  lightToVector(blockI.lightLevel)));
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
			lightsList.add(vertices.get(i).light.x);
			lightsList.add(vertices.get(i).light.y);
			lightsList.add(vertices.get(i).light.z);
			lightsList.add(vertices.get(i).light.w);
		}
		
		positions = new float[positionsList.size()];
		uvs = new float[uvsList.size()];
		normals = new float[normalsList.size()];
		light = new float[lightsList.size()];
		//indices = new int[indicesList.size()];
		
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
