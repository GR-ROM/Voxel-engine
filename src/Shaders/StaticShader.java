package Shaders;

import org.lwjgl.util.vector.Matrix4f;

import Engine.Camera;
import Toolbox.Maths;

public class StaticShader extends ShaderProgram {
	
	private static final String vertexFile = "/Shaders/vertexShader.txt";
	private static final String fragmentFile = "/Shaders/fragmentShader.txt";

	private int location_transformMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	
	public StaticShader() {
		super(vertexFile, fragmentFile);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute("position", 0);
		super.bindAttribute("textureCoords", 1);
		super.bindAttribute("lights", 2);
	}

	@Override
	protected void getAllUniformLocations() {
		this.location_transformMatrix=super.getUniformLocation("transformationMatrix");
		this.location_projectionMatrix=super.getUniformLocation("projectionMatrix");
		this.location_viewMatrix=super.getUniformLocation("viewMatrix");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f matrix=Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, matrix);
	}
}
