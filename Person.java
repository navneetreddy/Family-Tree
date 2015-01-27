///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  FamilyTreeMain.java
// File:             Person.java
// Semester:         CS367 Fall 2013
//
// Author:           Navneet Reddy
// CS Login:         navneet
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Jason Tiedt
// CS Login:         jtiedt
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          N/A
//////////////////////////// 80 columns wide //////////////////////////////////

/** The Person class represents a single person that keeps track of her name 
 * (as a String), age (as an int), eye color (as a String) and weight (as a 
 * double).
 */
public class Person {
	private String name;
	private String eyeColor;
	private double weight;
	
	/**Constructs a person with name, age, eyeColor and weight. */
	public Person(String name, String eyeColor, double weight) {
		this.name = name;
		this.eyeColor = eyeColor;
		this.weight = weight;
	}
	
	/** Return the name of this person */
	public String getName() {
		return name;
	}
	
	/** Return the eye color of this person */
	public String getEyeColor() {
		return eyeColor;
	}
	
	/** Return the weight of this person */
	public double getWeight() {
		return weight;
	}
}
