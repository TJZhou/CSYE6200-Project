package edu.neu.csye6200.bg;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @author Tianju Zhou NUID 001420546
 */
public class BGStem {
	
	private static Logger log = Logger.getLogger(BGStem.class.getName());
	
	//create a static HashMap to store all the stems 
	private static HashMap<Integer, BGStem> stemMap = new HashMap<Integer, BGStem>();
	
	private static int stemCounter = 0;
	
	private int stemID;			//create a unique stemID
	private double locationX;		
	private double locationY;	
	private double length;		
	private double radians;		
	
	// constructor
	public BGStem() {
		log.info("Constructing a Stem instance");
	}

	public BGStem(double locationX, double locationY, double length, double radians) {
		this.stemID = stemCounter++;
		setLocationX(locationX);
		setLocationY(locationY);
		setLength(length);
		setRadians(radians);
		addToHashMap(this);
		log.info("Constructing a Stem instance");
	}


	//getter ans setter of locationX,Y
	public double getLocationX() {
		return locationX;
	}
	public void setLocationX(double locationX) {
		
		//the location of X should not exceed -1000 or 1000
		if (locationX > 1000)
			this.locationX = 1000;
		else if (locationX < -1000)
			this.locationX = -1000;
		else
			this.locationX = locationX;
	}
	public double getLocationY() {
		return locationY;
	}
	public void setLocationY(double locationY) {
		
		//the location of X should not exceed -500 or 500
		if (locationY > 500)
			this.locationY = 500;
		else if (locationY < 0)
			this.locationY = 0;
		else
			this.locationY = locationY;
	}

	//getter and setter of radians
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		
		//the legnth should not less than 0
		if (length <= 0)
			length = 0;
		this.length = length;
	}
	public double getRadians() {
		return radians;
	}
	public void setRadians(double radians) {
		
		//the radians should not greater than 360
		if (radians > 360)
			this.radians = radians % 360;
		this.radians = radians;
	}

	//get stemID, get stemMap
	public int getStemID() {
		return stemID;
	}
	public static HashMap<Integer, BGStem> getStemMap() {
		return stemMap;
	}

	/**
	 * @param stemID
	 * @return get the stem instance from stemMap
	 */
	public static BGStem getFromHashMap(int stemID) {
		return stemMap.get(stemID);
	}

	/**
	 * @param stem
	 *            add stem to HashMap
	 */
	public void addToHashMap(BGStem stem) {
		stemMap.put(stemID, stem);
	}

	/**
	 * @param stemID
	 *            remove stem from HashMap
	 */
	public static void removeFromHashMap(int stemID) {
		stemMap.remove(stemID);
	}

	//create a method to judge if the present stem is the base stem
	boolean isBaseStem(BGStem stem) {
			if(stem.getLocationX() == 0 && stem.getLocationY() == 0)
				return true;
			else
				return false;
	}

	// return the number of childr stem (current plant)
	public int childStemNumbers(BGStem baseStem) {
		int count = 0;
		for (int i = baseStem.stemID; i < stemMap.size(); i++) {		
			/*
			 * if the present stem is base stem
			 * it should be the next plant
			 * then break the for loop to return the stem number of the current plant
			 */			
			if (i!=baseStem.stemID && isBaseStem(stemMap.get(i)) == true)
				break;
			count++;
		}
		return count;
	}

	//return the highest stem (locationY) of all the child stem
	public double totalHeight(BGStem baseStem){
		double highestStem = 0;
		for (int i = baseStem.stemID; i < stemMap.size(); i++) {		
			if (i != baseStem.stemID && isBaseStem(stemMap.get(i)) == true)
				break;
			if(stemMap.get(i).locationY > highestStem)
				highestStem = stemMap.get(i).locationY;
		}
		return highestStem;
	}
	
	//return the widest stem (locationX) of all the child stem
	public double totalWidth(BGStem baseStem){
		double widestStem = 0;
		for (int i = baseStem.stemID; i < stemMap.size(); i++) {		
			if (i != baseStem.stemID && isBaseStem(stemMap.get(i)) == true)
				break;
			if(stemMap.get(i).locationX > widestStem)
				widestStem = stemMap.get(i).locationX;
		}
		//the plant grow two sides
		return widestStem * 2;
	}
	
	// if the child list is empty
	public boolean isEmpty() {
		if (stemMap.isEmpty() == true)
			return true;
		else
			return false;
	}

	// traverse and print the HashMap
	public String traverseHashMap(BGStem baseStem) {
		String str = "\n";
		
		for (int i = baseStem.stemID; i < stemMap.size(); i++) {		
			/*
			 * if the present stem is base stem
			 * it should be the next plant
			 * then break the for loop to only print the stem of the current plant
			 */		
			if (i != baseStem.stemID && isBaseStem(stemMap.get(i)) == true)
				break;
			str = str + stemMap.get(i).toString() + '\n';
			//System.out.println(stemMap.get(i).toString());
		}
		return str + '\n';
	}

	//print the format of stem
	public String toString() {
		return String.format("%1$-16d %2$-16.2f %3$-16.2f %4$-16.2f %5$-16.2f", this.stemID, this.locationX,
				this.locationY, this.length, this.radians);
	}
}