package Geom;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Gps_PointTest {

	@Test
	void testGps_PointDoubleDoubleDouble() {
		Gps_Point gps=new Gps_Point(51.3, 80.999, 450);
		assertEquals(51.3, gps.get_x());
		assertEquals(80.999, gps.get_y());
		assertEquals(450, gps.get_z());
	}
	@Test
	void testGps_PointDoubleDoubleDoubleInvalidInput() {
		try {
			Gps_Point gps=new Gps_Point(51.3, 80.999, -4555);
		}
		catch (Exception e) {

		}

	}

	@Test
	void testGps_PointGps_Point() {
		Gps_Point p1=new Gps_Point(51.3, 80.999, 450);
		Gps_Point p2=new Gps_Point(p1);
		assertEquals(p1.get_x(), p2.get_x());
		assertEquals(p1.get_y(), p2.get_y());
		assertEquals(p1.get_z(), p2.get_z());
	}


	@Test
	void testDistance3D() {
		Gps_Point gps0= new Gps_Point(51.22,50.4,80);
		Gps_Point gps1= new Gps_Point(51.77,50.2,90);
		double dis=gps0.distance3d(gps1);
		assertEquals(62722.44629492102, dis);
	}

	@Test
	void testDistance2D() {
		Gps_Point gps0= new Gps_Point(32.103315,35.209039,670);
		Point3D gps1= new Point3D(32.106352,35.205225,650);
		double dis=gps0.distance2D(gps1);
		assertEquals(493.05233183241336, dis);
	}

	@Test
	void testIsValid_Gps_Point() {
		try {
			Gps_Point gps=new Gps_Point(51.3, 80.999, -4555);
		}
		catch (Exception e) {

		}
	}

	@Test
	void testEqualsGps_Point() {
		Gps_Point gps0= new Gps_Point(32.103315,35.209039,670);
		Gps_Point gps1= new Gps_Point(32.106352,35.205225,650);
		assertEquals(true, gps0.equals(gps0));
		assertEquals(false, gps0.equals(gps1));
	}

}

