package Block;

public class Block {
	public final static Short AIR = 0;
	public final static Short GRASS = 1;
	public final static Short DIRT = 2;
	public final static Short STONE = 3;
	public final static Short TREEBARK = 4;
	public final static Short TREELEAF = 5;
	
	public Short type;
	public Short lightLevel; 
	public boolean transparent;
	
	public Block(Short type, Short light) {
		this.transparent=false;		
		this.type=type;
		this.lightLevel=light;
	}
	
	public Block(Short type) {
		this.transparent=false;		
		if (type==AIR) this.transparent=true;		
		this.type=type;
		this.lightLevel=0;
	}
	
}
