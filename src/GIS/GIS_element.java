package GIS;

import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Point3D;

/**
 * This interface represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 * @author Boaz Ben-Moshe
 *
 */
public interface GIS_element {
	public Gps_Point getGeom();
	public ElementMetaData getData();
	public void translate(Point3D vec);
}
