package Coords;

import java.util.Arrays;

import File_format.CsvToKml;
import File_format.DirectoryToKml;
import GIS.ElementMetaData;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.MyGisElement;
import GIS.MyGisLayer;
import GIS.MyGisProject;
import Geom.Gps_Point;
import Geom.Point3D;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
	DirectoryToKml d= new 	DirectoryToKml ();
	
	d.multyKmlFile( "data");
	CsvToKml r= new CsvToKml();
	MyGisLayer l= (MyGisLayer) r.read("WigleWifi_20171201110209.csv");
	GIS_layer l2= r.read("WigleWifi_20171203085618.csv");
	System.out.println(l.equals(l2));
	System.out.println(l);
	String[] h= {"MAC","SSID","AuthMode","FirstSeen","Channel","RSSI","AccuracyMeters","Type","CurrentLatitude","CurrentLongitude","AltitudeMeters"};
	String[] s= {"42501_13111_7943940","Partner","UNKNOWN;il","2017-12-01 11:01:23","0","-107","3","WIFI","32.1721826821653","34.8144640170275","13.6504088889507"};

	String[] w= {"42501_1311_7943940","Partner","UNKNOWN;il","2017-12-01 11:01:23","0","-107","3","WIFI","32.1721826821653","34.8144640170275","13.6504088889507"};
	ElementMetaData a= new ElementMetaData(s,h);
	ElementMetaData b= new ElementMetaData(w,h);
	System.out.println(a.getUTC());

	MyGisElement e= new MyGisElement(s,h);
	MyGisElement e1= new MyGisElement(w,h);
	System.out.println(e.equals(e1));
/*/
		Gps_Point gps0= new Gps_Point(51.22,50.4,80);
		Gps_Point gps1= new Gps_Point(51.77,50.2,90);
		double dis=gps0.distance3d(gps1);
		System.out.println(dis);

		

	}

}
