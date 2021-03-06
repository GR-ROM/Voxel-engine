package Engine;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import Models.RawModel;

public class Loader {
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	
public RawModel loadToVAO(float[] vertices, int[] indices, float[] uv, float[] lights) {
		
		int vaoID = createVAO();
		storeDataInAttributeList(0, 3, vertices);
		storeDataInAttributeList(1, 2, uv);
		storeDataInAttributeList(2, 1, lights);
		bindIndicesBuffer(indices);
		GL30.glBindVertexArray(0);
		return new RawModel(vaoID, indices.length);
	}
	
	public RawModel loadToVAO(float[] vertices, float[] normals, float[] uvs, float[] lights) {
		int vaoID = createVAO();
		storeDataInAttributeList(0, 3, vertices);
		storeDataInAttributeList(1, 2, normals);
		storeDataInAttributeList(2, 2, uvs);
		storeDataInAttributeList(3, 1, lights);
		GL30.glBindVertexArray(0);
		
		return new RawModel(vaoID, vertices.length);
		
	}
	
	public void reLoadToVAO(float[] vertices, float[] normals, float[] uvs, float[] lights)  {
		storeDataInAttributeList(vbos.get(0), 0, 3, vertices);
		storeDataInAttributeList(vbos.get(1), 1, 3, normals);
		storeDataInAttributeList(vbos.get(2), 1, 2, uvs);
		storeDataInAttributeList(vbos.get(3), 2, 1, lights);
		GL30.glBindVertexArray(0);		
	}
	
	public void reLoadToVAO(float[] vertices, int[] indices, float[] uvs, float[] lights) {
		storeDataInAttributeList(vbos.get(0), 0, 3, vertices);
		storeDataInAttributeList(vbos.get(1), 1, 2, uvs);
		storeDataInAttributeList(vbos.get(2), 2, 1, lights);
		bindIndicesBuffer(indices);
		GL30.glBindVertexArray(0);		
	}
	
	
	public void cleanUp(){
		for (int vao:vaos){
			GL30.glDeleteVertexArrays(vao);
		}
		
		for (int vbo:vbos){
			GL15.glDeleteBuffers(vbo);
		}
		
		for (int texture:textures){
			GL15.glDeleteBuffers(texture);
		}
	}
	
	private int createVAO(){
		int vaoID=GL30.glGenVertexArrays();
		this.vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void bindIndicesBuffer(int vboID, int[] indices){
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer=this.storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
	}
	
	private void storeDataInAttributeList(int vboID,int attributeNumber, int attributeSize, float[] data )	{
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer=this.storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, attributeSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private void bindIndicesBuffer(int[] indices){
		int vboID=GL15.glGenBuffers();
		this.vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer=this.storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
	}
	
	private void storeDataInAttributeList(int attributeNumber, int attributeSize, float[] data )	{
		int vboID=GL15.glGenBuffers();
		this.vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer=this.storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, attributeSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data){
		IntBuffer buffer=BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		FloatBuffer buffer=BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public int loadTexture(String fileName) {
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", Class.class.getResourceAsStream("/res/" + fileName + ".PNG"));
			
			
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST_MIPMAP_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		//	GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;	
	}	
}