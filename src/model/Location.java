package model;

public class Location {
	private int nameOld;
	private int nameNew;
	private int pointerOld;
	private int pointerNew;
	public Location(int nameOld, int nameNew, int pointerOld, int pointerNew) {
		this.nameOld = nameOld;
		this.nameNew = nameNew;
		this.pointerOld = pointerOld;
		this.pointerNew = pointerNew;
	}
	public int getNameOld() {
		return nameOld;
	}
	public void setNameOld(int nameOld) {
		this.nameOld = nameOld;
	}
	public int getNameNew() {
		return nameNew;
	}
	public void setNameNew(int nameNew) {
		this.nameNew = nameNew;
	}
	public int getPointerOld() {
		return pointerOld;
	}
	public void setPointerOld(int pointerOld) {
		this.pointerOld = pointerOld;
	}
	public int getPointerNew() {
		return pointerNew;
	}
	public void setPointerNew(int pointerNew) {
		this.pointerNew = pointerNew;
	}
	@Override
	public String toString() {
		return "Location [nameOld=" + nameOld + ", nameNew=" + nameNew + ", pointerOld=" + pointerOld + ", pointerNew="
				+ pointerNew + "]";
	}
	
	
	

}
