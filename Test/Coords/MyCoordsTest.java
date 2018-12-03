package Coords;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Geom.Gps_Point;
import Geom.Point3D;
import junit.framework.Assert;

class MyCoordsTest {
	MyCoords mc;
	Gps_Point gps0;
	Gps_Point gps1;
	Point3D vector;

	@BeforeEach
	void setUp() throws Exception {
		mc=new MyCoords();
		gps0=new Gps_Point(32.103315,35.209039,670);
		gps1=new Gps_Point(32.106900280672086,35.204371143768846,650.0);
		vector=new Point3D(337.6989920612881,-359.24920693881893,-20);
	}

	@Test
	void testAdd() {
		assertEquals(true,mc.add(gps0,vector).equals(gps1));	
	}

	@Test
	void testDistance3d() {
		double dis=gps0.distance3d(gps1);
		assertEquals(593.8422703887146, dis);
	}
	@Test
	void testVector3D() {
		Point3D vec0=mc.vector3D(gps0, gps1);
		Point3D vec1=new Point3D(398.6650210723607,-439.67583862863955,-20.0);
		assertEquals(vec1.toString(), vec0.toString());
	}

	@Test
	void testAzimuth_elevation_dist() {
		double arr []= {312.20117898531686,-1.9300282050236335 ,593.8422703887146};

		assertEquals(Arrays.toString(arr), Arrays.toString(mc.azimuth_elevation_dist(gps0, gps1)));
	}

	@Test
	void testIsValid_GPS_Point() {
		mc.isValid_GPS_Point(vector);
		assertEquals(false, mc.isValid_GPS_Point(vector));
	}

}
