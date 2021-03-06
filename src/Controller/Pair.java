package controller;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

import controller.xmlGestureParser.xmlStatics;


/**
 * Class used to hold pairs of joints as all recordings are done in two
 * joint format currently this allows for comparison between linked joints and
 * access to both joints being compared. The joint represented by First is compared
 * to the joint represented by Second for gesture purposes. 
 * 
 * @author Levi Lindsley
 *
 */
public class Pair implements Serializable{
	/**Generated serialVersionUID*/
	private static final long serialVersionUID = -449449065485240596L;
	final private String classTag = "pair";
	/** First Joint in focus */
	Integer First;
	
	/** Second Joint in focus */
	Integer Second;

	/**
	 * Initialize First and Second with the values f and s
	 * @param f : Value to initialize First with
	 * @param s : Value to initialize Second with
	 */
	public Pair(int f, int s){
		First = new Integer(f);
		Second = new Integer(s);
	}
	/**@return this.First*/
	public Integer getFirst(){
		return First;
	}
	/**@return this.Second*/
	public Integer getSecond(){
		return Second;
	}
	/**
	 * Override of equals to check for comparison between two JointPairs
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof Pair){
			return (this.First.equals(((Pair)o).First) && this.Second.equals(((Pair)o).Second));
		}
		return false;
	}
	/**
	 * Override of hashCode because equals got an override now the hashCode will be
	 * equal when equals() returns true
	 */
	@Override
	public int hashCode(){
		Vector<Integer> h = new Vector<Integer>();
		h.add(First);
		h.add(Second);
		return h.hashCode();
	}
	/**Prints out first and second being enclosed by chevrons*/
	@Override
	public String toString(){
		return "<"+First+", "+Second+">";
	}
	public static Pair load(Scanner xmlInput){
		Pair p = new Pair(0,0);
		String next = xmlInput.next();
		if (next.compareTo("null")==0)
			return null;
		while (next.compareTo("<"+p.classTag+">")!=0){
			if (!xmlInput.hasNext())
				return null;
			next = xmlInput.next();
		}
		p.First = Integer.parseInt(xmlStatics.parseElement(xmlInput));
		p.Second = Integer.parseInt(xmlStatics.parseElement(xmlInput));
		
		xmlInput.next();//</classTag>
		return p;
	}
	/**
	 * Creates and xml representation of this with no default leading tabs
	 * @return String xml representation of this
	 */
	public String toXML(){
		String context = new String();
		context +="<"+classTag+">"+'\n';
		context += xmlStatics.createElement("first", First.toString());
		context += xmlStatics.createElement("second", Second.toString());
		context +="</"+classTag+">"+'\n';
		return context;
	}
}
