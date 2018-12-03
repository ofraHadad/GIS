package Geom;

public class Gps_Point implements Geom_element   {


	private double _x;
	private double _y;
	private double _z;

	////////////////////constructor\\\\\\\\\\\\\\\\\\\\

	public Gps_Point(double x, double y,double z) {
		set_x(x);
		set_y(y);
		set_z(z);
		if(! isValid_Gps_Point()) {
			throw new RuntimeException("is invalid input");
		}
	}

	public Gps_Point(Gps_Point p) {
		set_x(p.get_x());
		set_y(p.get_y());
		set_z(p.get_z());
		if(! isValid_Gps_Point()) {
			throw new RuntimeException("is invalid input");
		}
	}





	////////////////////Methods\\\\\\\\\\\\\\\\\\\\

	public boolean isValid_Gps_Point() {
		if(get_x()>180 || get_x()<-180)
			return false;
		if(get_y()>90 || get_y()<-90)
			return false;
		if(get_z()<-450)
			return false;
		return true;
	}


	public double distance3d(Gps_Point gps1) {
		//Gps_Point gps0= new Gps_Point(get_x(), get_y(),get_z());
		Gps_Point diff=new Gps_Point(gps1.get_x()-get_x(),gps1.get_y()-get_y(),gps1.get_z()-get_z());
		double LonNorm= computeLonNorm(this);
		Point3D diffMeter=ConvertsDegreesToMeters(diff,LonNorm);
		double distanceXY=Math.sqrt((Math.pow(diffMeter.x(), 2))+(Math.pow(diffMeter.y(),2)));
		double diffZ= gps1.get_z()-get_z();
		if(Math.sqrt((Math.pow(distanceXY, 2))+(Math.pow(diffZ,2)))>100000) {
			throw new RuntimeException("over 100 km");
		}

		return Math.sqrt((Math.pow(distanceXY, 2))+(Math.pow(diffZ,2)));


	}
	private Point3D ConvertsDegreesToMeters(Gps_Point gps, double LonNorm) {
		long Radius_earth=6371000;
		Point3D rad=new Point3D(d2r(gps.get_x()), d2r(gps.get_y()), gps.get_z());
		Point3D meter=new Point3D((Math.sin(rad.x()))*Radius_earth, (Math.sin(rad.y()))*Radius_earth*LonNorm,rad.z());
		return meter;
	}

	private Gps_Point ConvertsMeterToDegree(Point3D meter,double LonNorm) {
		long Radius_earth=6371000;
		Point3D rad=new Point3D((Math.asin((meter.x())/(Radius_earth))),(Math.asin((meter.y())/(Radius_earth*LonNorm))),meter.z());
		Gps_Point dig=new Gps_Point(rad.r2d(rad.x()), rad.r2d(rad.y()),rad.z());
		return dig;
	}
	private double computeLonNorm(Gps_Point p) {

		return Math.cos(Math.toRadians(p.get_x()));
	}
	private static double d2r(double a) { return Math.toRadians(a);}
	private static double r2d(double a) { return Math.toDegrees(a);}
	public double get_x() {
		return _x;
	}

	public void set_x(double _x) {
		this._x = _x;
	}

	public double get_y() {
		return _y;
	}

	public void set_y(double _y) {
		this._y = _y;
	}

	public double get_z() {
		return _z;
	}

	public void set_z(double _z) {
		this._z = _z;
	}

	@Override
	public double distance3D(Point3D p) {
		Gps_Point gps=new Gps_Point(p.x(),p.y(),p.z()) ;
		return distance3d(gps);
	}

	@Override
	public double distance2D(Point3D p) {
		Gps_Point gps=new Gps_Point(p.x(),p.y(),p.z()) ;
		Gps_Point diff=new Gps_Point(gps.get_x()-get_x(),gps.get_y()-get_y(),gps.get_z()-get_z());
		double LonNorm= computeLonNorm(this);
		Point3D diffMeter=ConvertsDegreesToMeters(diff,LonNorm);
		double distanceXY=Math.sqrt((Math.pow(diffMeter.x(), 2))+(Math.pow(diffMeter.y(),2)));
		return distanceXY;
	}
}

