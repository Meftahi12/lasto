package myService;



public class MyGPS {
	private double x ;
	private double y ;

	public String toString(){
		return "[Laltitude = "+x+", Longitude = "+y+"]";
	}

	public MyGPS(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public MyGPS() {
		this.x = 0;
		this.y = 0;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
}
