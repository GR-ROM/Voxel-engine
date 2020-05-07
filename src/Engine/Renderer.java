package Engine;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import Block.Chunk;
import Entities.Entity;
import Models.TexturedModel;
import Shaders.StaticShader;
import Toolbox.Maths;

public class Renderer {
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000f;
	
	private Matrix4f projectionMatrix;
	
	public Renderer(StaticShader shader){
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void prepare() {	
	//	GL11.glEnable(GL11.GL_CULL_FACE);
	//	GL11.glCullFace(GL11.GL_BACK);
	
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	private void createProjectionMatrix() {
		float aspectRatio=(float) Display.getWidth() / (float) Display.getHeight();
		float yScale=(float) (1f / Math.tan(Math.toRadians(FOV / 2f)));
		float xScale= yScale / aspectRatio;
		float frustrum_length=FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix=new Matrix4f();
		projectionMatrix.m00 = xScale;
		projectionMatrix.m11 = yScale;
		projectionMatrix.m22 = -((FAR_PLANE+NEAR_PLANE)/frustrum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -(2 * FAR_PLANE * NEAR_PLANE/frustrum_length);
		projectionMatrix.m33 = 0; 
	}
	
	public void renderChunk(Chunk chunk, StaticShader shader)	{
		TexturedModel model=chunk.getTexturedModel();
		GL30.glBindVertexArray(model.getModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
		
		Matrix4f transformationMatrix=Maths.createTransformationMatrix(chunk.origin, 0, 0, 0, 1);
		shader.loadTransformationMatrix(transformationMatrix);
		int vertices= chunk.chunkMesh.positions.length;
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertices);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);	
	}


	public void renderModel(Entity entity, StaticShader shader)	{
		TexturedModel model=entity.getModel();
		GL30.glBindVertexArray(model.getModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
		
		Matrix4f transformationMatrix=Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getModel().getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);	
	}
	
}
