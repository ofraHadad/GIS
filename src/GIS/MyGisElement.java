package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Point3D;

public class MyGisElement implements GIS_element{
	private Gps_Point gps;
	private ElementMetaData metaData;

	private int serch(String[] head,String s) {
		int index=head.length;
		for (int i=0; i<head.length; i++) {
			if(s.equals(head[i])) {
				index= i;
				return index;
			}
		}

		if(index>head.length-1) {
		throw new RuntimeException("ivalid input");	
		}
		return index;
	}

	public MyGisElement(String[] line, String[] head) {
		gps= new Gps_Point(Double.parseDouble(line[serch(head,"CurrentLatitude")]),
				Double.parseDouble(line[serch(head,"CurrentLongitude")])
				, Double.parseDouble(line[serch(head,"AltitudeMeters")]));
		metaData= new ElementMetaData(line, head);
	}
	
	

	

	private void setGps(Gps_Point geom) {
		this.gps = geom;
		
	}

	public boolean equals(GIS_element e) {
		if(getGeom().equals(e.getGeom()) && getData().equals(e.getData())) {
			return true;
		}
		return false;
	}
	@Override
	public Gps_Point getGeom() {
		return gps;
	}
	@Override
	public ElementMetaData getData() {

		return metaData;
	}


	public ElementMetaData getMetaData() {
		return (ElementMetaData) metaData;
	}

	@Override
	public void translate(Point3D vec) {
		MyCoords c= new MyCoords();
		Gps_Point newGps=	c.add((Gps_Point) getGeom(), vec);
		setGps(newGps);
	}
	
	public String toString() {
		return "GPS Point: "+getGeom().toString()+ "\n"+ getData().toString();
	}

	

	private void setMetaData(ElementMetaData metaData) {
		this.metaData = metaData;
	}
	
	


	


}
