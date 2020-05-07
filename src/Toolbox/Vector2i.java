package Toolbox;

public class Vector2i {
	public int x;
	public int z;
	
	public Vector2i(int x, int z) {
		this.x=x;
		this.z=z;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	
	@Override    
    public boolean equals(Object o) {        
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; 
        Vector2i vec=(Vector2i)o;
        if ((this.x==vec.x) && (this.z==vec.z)) return true;
        return false;
	}
	
	 @Override    
	    public int hashCode() {        
	        int result = (int) (x ^ (x >>> 32));        
	        result = 31 * result + (Integer.hashCode(z));        
	        return result;    
	    }
}
