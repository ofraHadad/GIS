package File_format;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.LayerMetaData;
import GIS.MyGisElement;
import GIS.MyGisLayer;
import GIS.MyGisProject;
import GIS.ProjectMetaData;
/**
 * this class read a folder and take the csv file of gps point 
 * and generate it to one kml file
 * @author ofra&shira
 *
 */
public class DirectoryToKml {

	/**
	 * we took the code from this link:
	 * https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
	 * @param parentDirectory
	 * @param p
	 * @return
	 */
	public  MyGisProject read(String parentDirectory,MyGisProject p){
		File[] filesInDirectory = new File(parentDirectory).listFiles();
		for(File f : filesInDirectory){
			if(f.isDirectory()){
				read(f.getAbsolutePath(),p);
			}
			String filePath = f.getAbsolutePath();
			String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
			if("csv".equals(fileExtenstion)){
				//add to a list or array
				CsvToKml r= new CsvToKml();

				p.add(r.read(filePath));
			}

		} 
		Iterator<GIS_layer> it= p.iterator();
		p.setMetaData(new ProjectMetaData((MyGisLayer)it.next()));     
		return p;
	}

	/**
	 * make a kml file from a project- an arrayList of layers 
	 * @param csvfile
	 */
	public void multyKmlFile(String csvfile) {
		try 
		{

			FileWriter writer = new FileWriter(csvfile+".kml");
			BufferedWriter bw = new BufferedWriter(writer);
			StringBuilder sb = new StringBuilder();
			MyGisProject project=new MyGisProject();
			project=read(csvfile, project);
			Iterator<GIS_layer> itData= project.iterator();
			project.setMetaData(new ProjectMetaData((MyGisLayer)itData.next()));

			Iterator<GIS_layer> itP= project.iterator();
			//create kml file	



			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"blue\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/blue-dot.png</href>"
					+ "</Icon></IconStyle></Style><Style id=\"blue\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/blue-dot.png</href>"
					+ "</Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/green-dot.png</href>"
					+ "</Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\r\n" + "");



			while(itP.hasNext()) {
				GIS_layer layer= itP.next();
				Iterator<GIS_element> itL= layer.iterator();

				while(itL.hasNext()) {
					MyGisElement e= (MyGisElement) itL.next();
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
