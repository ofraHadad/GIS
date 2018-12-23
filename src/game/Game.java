package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_element;
import Geom.Gps_Point;
import Geom.Pixel;

/**
 * this class represent a game.
 * it's has fruits-ArrayList of fruits,packmans- aarayList of packmans and map-where the game is played.
 * @author ofra and shira
 *
 */
public class Game {

	private ArrayList<Fruit> fruits=new ArrayList<>();
	private ArrayList<Packman> packmans=new ArrayList<>();
	private Map map;

////////////////////////////////constructors///////////////////////////////////////////////
/**
 * 
 * @param map
 */
	public Game(Map map) {
		this.map= map; 
	}
	/**
	 * creates a game from a csv file
	 * @param csvFile
	 * @param map
	 */
	public Game(String csvFile, Map map) {
		this.map= map;
		
		read(csvFile, 0, map, this);
	}

	///////////////////////////////////////////methods/////////////////////////////////////////////
	public String toString() {
		String ans="";
		Iterator<Fruit> itFruits= getFruits().iterator();
		Iterator<Packman> itPackmans= getPackmans().iterator();
		while(itPackmans.hasNext()) {
			ans= ans+"Packman: "+itPackmans.next().toString()+"\n";
		}
		while(itFruits.hasNext()) {
			ans= ans+"Fruit: "+itFruits.next().toString()+"\n";
		}
		return ans;
	}

	/**
	 * write a csv file represent this game.
	 * the same format in that you can create a game from.
	 * @param fileName
	 */
	public  void toCSV(String fileName) {
		
		FileWriter writer;
		try {
			writer = new FileWriter(fileName+".csv");
			BufferedWriter bw = new BufferedWriter(writer);
			StringBuilder sb = new StringBuilder();
			
			sb.append("Type,id,Lat,Lon,Alt,Speed/Weight,Raius,"+getPackmans().size()+","+getFruits().size()+"\n");
			Iterator<Packman> packmans= getPackmans().iterator();
			Iterator<Fruit> fruits=getFruits().iterator();
			while(packmans.hasNext()) {
				Packman p= packmans.next();
				Gps_Point location=getMap().convertePixelToGps(p.getLocation());
				sb.append("P,"+p.getDataP().getId()+","+location.get_y()+","+location.get_x()+
						","+ p.getDataP().getAlt()+","+p.getDataP().getSpeed()+","+p.getDataP().getRadius()+"\n");
			}
			while(fruits.hasNext()) {
				Fruit f= fruits.next();
				Gps_Point location=getMap().convertePixelToGps(f.getLocation());
				sb.append("F,"+f.getDataF().getId()+","+location.get_y()+","+location.get_x()+
						","+ f.getDataF().getAlt()+","+f.getDataF().getWeight()+"\n");
			}
		

			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	private Game read(String csvFile,int firstLine, Map map, Game game) {

		String line = "";
		String cvsSplitBy = ",";
		int column=0;
		

		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			int count =firstLine;
			
			while (count==firstLine&&(line = br.readLine()) != null) 
			{
				String[] head= line.split(cvsSplitBy);
				column= head.length;
				count++;
				
				
			}
		}
			
		catch (IOException e1) {
			e1.printStackTrace();
		}
		String[] head= new String [column];

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			int count =firstLine;

			while ((line = br.readLine()) != null) 
			{	
				if(count== firstLine) {
					head = line.split(cvsSplitBy);

				}


				if(count>firstLine) {
					String[] userInfo = line.split(cvsSplitBy);
					int isValid= serch(head,"Type");
					if(isValid>=head.length) {
						throw new RuntimeException("invalid input");
					}
					int isPackman= serch(userInfo,"P");
					if(isPackman<userInfo.length) {
						game.getPackmans().add(new Packman(userInfo,head,map));

					}
					else {
						int isFruit= serch(userInfo, "F");
						if(isFruit<userInfo.length) {
							game.getFruits().add(new Fruit(userInfo,head,map));
						}
						else {
							throw new RuntimeException("the type of the point is not in the file");
						}

					}
				}
				count++;
			}

		

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return game;
	}
	
	private int serch(String[] head,String s) {
		int index=head.length;
		for (int i=0; i<head.length; i++) {
			if(s.equals(head[i])||s.contentEquals(head[i])) {
				index= i;
				return index;
			}
		}


		return index;
	}


	/////////////////////////Getters and Setters//////////////////////////////////////////////////

	
	protected ArrayList<Fruit> getFruits() {
		return fruits;
	}
	protected ArrayList<Packman> getPackmans() {
		return packmans;
	}



	public Map getMap() {
		return map;
	}
	
	


}
