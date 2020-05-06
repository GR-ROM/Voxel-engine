package Block;

public class Block {
	public static int AIR = 0;
	public static int GRASS = 1;
	public static int DIRT = 2;
	public static int STONE = 3;
	public static int TREEBARK = 4;
	public static int TREELEAF = 5;
	
	public Short type;
	public Short lightLevel; 
	public boolean transparency;
	
	public Block(Short type, Short light) {
		this.transparency=false;		
		this.type=type;
		this.lightLevel=light;
	}
	
	
	public Block(Short type) {
		this.transparency=false;		
		this.type=type;
		this.lightLevel=0;
	}
	
}
