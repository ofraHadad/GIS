package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Geom.Point3D;

public class LayerMetaData implements Meta_data {

	private String createdTime;

	public LayerMetaData(MyGisElement e) {
		this.createdTime= e.getData().getFirstSeen();	
	}




	public String getCreatedTime() {
		return createdTime;
	}

	@Override
	public long getUTC() {
		// https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds
		SimpleDateFormat sdf = new SimpleDateFormat(createdTime);
		Date date;
		try {
			date = sdf.parse(createdTime);
			long timeInMillis = date.getTime();
			return timeInMillis;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		return "Time: " +getCreatedTime();
	}

}
