package File_format;
import java.util.Iterator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import GIS.ElementMetaData;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.LayerMetaData;
import GIS.Meta_data;
import GIS.MyGisElement;
import GIS.MyGisLayer;


public class CsvToKml {



	public MyGisLayer read(String csvFile) {

		String firstLine="";
		String line = "";
		String cvsSplitBy = ",";
		MyGisLayer layer= new MyGisLayer();
		Meta_data layerData= new LayerMetaData();
		String[] head= new String [11];



		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			int count =0;
			
			while ((line = br.readLine()) != null) 
			{	
				
				if(count== 1) {
					head = line.split(cvsSplitBy);

				}


				if(count>1) {
					String[] userInfo = line.split(cvsSplitBy);
					layer.add(new MyGisElement(userInfo,head));
				}
				count++;
			}

			Iterator<GIS_element> it= layer.iterator();
			layer.setMetaData(new LayerMetaData((MyGisElement)it.next()));


		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return layer;
	}

	/**
	 * This function takes a csv file, reads it and transform it into a kml file
	 * @param csvfile1 which is the csv first file address
	 * @param csvfile2 which is the csv second file address
	 */
	public  void KmlWriter(String csvfile)
	{
		try 
		{

			FileWriter writer = new FileWriter(csvfile+".kml");
			BufferedWriter bw = new BufferedWriter(writer);
			StringBuilder sb = new StringBuilder();
			CsvToKml r= new CsvToKml();
			GIS_layer layer=r.read(csvfile);
			Iterator<GIS_element> itLayer= layer.iterator();
			//create kml file	



			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"blue\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/blue-dot.png</href>"
					+ "</Icon></IconStyle></Style><Style id=\"blue\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/blue-dot.png</href>"
					+ "</Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/green-dot.png</href>"
					+ "</Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\r\n" + "");




			while(itLayer.hasNext()) {
				MyGisElement e= (MyGisElement) itLayer.next();
				sb.append("<Placemark>\n");
				sb.append("<name>"+"<![CDATA["+e.getMetaData().getSSID()+"]]>"  +"</name>\n");
				sb.append("<description>"+"<![CDATA[BSSID: <b>"+e.getMetaData().getMAC()+"</b><br/>Capabilities: <b>"
						+e.getMetaData().getAuthMode()+"</b><br/>Channel: <b>"+e.getMetaData().getChannel()+
						"</b><br/>RSSI: <b>"+e.getMetaData().getRSSI()+"</b><br/>AccuracyMeters: <b>"
						+e.getMetaData().getAccuracyMeters()+"</b><br/>Type: <b>"+e.getMetaData().getType()+
						"</b><br/>Date: <b>"+e.getMetaData().getFirstSeen()+"</b>]]>"+"</description><styleUrl>#blue</styleUrl>\n");
				sb.append("<Point>\n");
				sb.append("<coordinates>"+e.getGeom().get_y()+","+e.getGeom().get_x()+","+e.getGeom().get_z()+"</coordinates></Point>\n");
				sb.append("</Placemark>\n");

			}

			sb.append("\n</Folder>\n");
			sb.append("</Document></kml>");

			bw.write(sb.toString());
			bw.close();



		} 
		catch (IOException e) {
			e.printStackTrace();
		}	

	}


}
