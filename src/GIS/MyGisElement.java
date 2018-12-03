package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Point3D;

public class MyGisElement implements GIS_element{
	private Gps_Point gps;
	private ElementMetaData metaData;

	/**
	 * Constructor that get two arrays of String
	 * used in the read method , to read csv files 
	 * @param line
	 * @param head
	 */
	public MyGisElement(String[] line, String[] head) {
		gps= new Gps_Point(Double.parseDouble(line[serch(head,"CurrentLatitude")]),
				Double.parseDouble(line[serch(head,"CurrentLongitude")])
				, Double.parseDouble(line[serch(head,"AltitudeMeters")]));
		metaData= new ElementMetaData(line, head);
	}
	
/////////////////////////////////GIS_element/////////////////////////////////////
	@Override
	public Gps_Point getGeom() {
		return gps;
	}
	@Override
	public ElementMetaData getData() {

		return metaData;
	}
	
	@Override
	public void translate(Point3D vec) {
		MyCoords c= new MyCoords();
		Gps_Point newGps=	c.add((Gps_Point) getGeom(), vec);
		setGps(newGps);
	}
	
	/////////////////////////////////methods////////////////////////////
	/**
	 * check if two elements are equals
	 * @param e
	 * @return
	 */
	public boolean equals(GIS_element e) {
		if(getGeom().equals(e.getGeom()) && getData().equals(e.getData())) {
			return true;
		}
		return false;
	}
	
	/**
	 * return a String that represent the class
	 */
	public String toString() {
		return "GPS Point: "+getGeom().toString()+ "\n"+ getData().toString();
	}

	
//////////////////////////////getters and setters//////////////////////////////////////////
	private void setMetaData(ElementMetaData metaData) {
		this.metaData = metaData;
	}
	
	public ElementMetaData getMetaData() {
		return (ElementMetaData) metaData;
	}
	
	private void setGps(Gps_Point geom) {
		this.gps = geom;
		
	}


	
///////////////////////////////private///////////////////////////	
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


	


}
