///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Family Tree (P4)
// Files:            FamilyTreeMain.java, FamilyTree.java
//					 TreeNode.java, Person.java
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

import java.util.*;
import java.io.*;

/**
 * Creates and manipulates a maternal family tree.
 * 
 * @author Navneet Reddy
 * @author Jason Tiedt
 */
public class FamilyTreeMain {
	
	/**
	 * Main method for processing user commands.
	 * 
	 * @param args file storing information of family members
	 */
	public static void main(String[] args) {
		//Check whether exactly one command-line argument is given
		if (args.length != 1){
			System.out.println("Usage: java FamilyTreeMain FileName");
			System.exit(-1);
		}

		//Name of file in the command line arguments
		String filename = args[0];
		//Creates a new family tree
		FamilyTree famTree = new FamilyTree();
		
		try {
			File file = new File(filename);
			Scanner scnr = new Scanner(file);
			
			//Check if the file exists and is readable
			if (!file.isFile() || !file.canRead())
			{
				System.out.println("Error: Cannot access input file");
				System.exit(-1);
			}
			
			//Stores the next line in the scanner
			String x;
			//Stores the person's information as separate strings
			String[] info = new String[4];
			//Creates new person to add to the tree
			Person person;
			
			//Loads the data from the input file
			while(scnr.hasNextLine())
			{
				x = scnr.nextLine();
				info = x.split(",");
				
				//Check if there is a parent given
				if (info.length == 3)
				{
					person = new Person(info[0],info[1],
									Double.parseDouble(info[2]));
					famTree.addPerson(person,null);
				}
				else
				{
					person = new Person(info[0],info[1],
									Double.parseDouble(info[2]));
					famTree.addPerson(person,info[3]);
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Error: Cannot access input file");
			System.exit(-1);
		}

		//Scanner for user input
		Scanner stdin = new Scanner(System.in);	
		//While loop condition
		boolean stop = false;

		while (!stop) {
			//Prompt the user to enter command options
			System.out.println("\nEnter Command:");
			String input = stdin.nextLine();
			String remainder = null;
			if (input.length() > 0) {
				char option = input.charAt(0);
				if (input.length() > 1) {
					remainder = input.substring(1).trim();
				}

				switch (option) {

				case 'c':{
					//Store all the siblings and cousins
					List<Person> cousins = 
							famTree.getAllSiblingsCousins(remainder);
					
					//Check if the person is in the tree
					if (!famTree.contains(remainder))
						System.out.println("person not found");
					//Check if the person has any siblings or cousins
					else if (cousins == null)
						System.out.println
								("the person has no sibling or cousin");
					else
						for (int i = 0; i < cousins.size(); i++)
							System.out.println(cousins.get(i).getName());
					
					break;
				}

				case 'd':{
					System.out.println("# of persons in family tree: " + 
											famTree.getNumPeople());
					System.out.println("max generations in family tree: " + 
											famTree.getMaxGenerations());
					if (famTree.getFirstAncestor() == null)
						System.out.println("first ancestor: there is no ancestor");
					else
						System.out.println("first ancestor: " + 
											famTree.getFirstAncestor());
					
					break;
				}

				case 'e':{
					//Store all the people with the given eye color
					List<Person> eyeColor = 
							famTree.getPeopleWithEyeColor(remainder);

					//Check if anyone in the family tree has the 
					//given eye color
					if (eyeColor == null)
						System.out.println("person not found");
					else
						for (int i = 0; i < eyeColor.size(); i++)
							System.out.println(eyeColor.get(i).getName());

					break;
				}

				case 'g':{
					//Store all the great aunts
					List<Person> greatAunts = famTree.getGreatAunts(remainder);
					
					//Check if the person is in the tree
					if (!famTree.contains(remainder))
						System.out.println("person not found");
					//Check if the person has any great aunts
					else if (greatAunts == null)
						System.out.println("the person has no great aunt");
					else
						for (int i = 0; i < greatAunts.size(); i++)
							System.out.println(greatAunts.get(i).getName());
					
					break;
				}

				case 'r':{
					//Store whether the person was removed or not
					boolean removed = famTree.removeWithName(remainder);
					
					//Check whether the person was removed or not
					if (removed == true)
						System.out.println("person removed");
					else
						System.out.println("person not found");
					
					break;
				}

				case 's':{
					//Store all the siblings
					List<Person> siblings = famTree.getSiblings(remainder);

					//Check if the person is in the tree
					if (!famTree.contains(remainder))
						System.out.println("person not found");
					//Check if the person has any siblings
					else if (siblings == null)
						System.out.println("the person has no sibling");
					else
						for (int i = 0; i < siblings.size(); i++)
							System.out.println(siblings.get(i).getName());
					
					break;
				}

				case 'u':{
					//Store all the aunts
					List<Person> aunts = famTree.getAunts(remainder);
					
					//Check if the person is in the tree
					if (!famTree.contains(remainder))
						System.out.println("person not found");
					//Check if the person has any aunts
					else if (famTree.getAunts(remainder) == null)
						System.out.println("the person has no aunt");
					else
						for (int i = 0; i < aunts.size(); i++)
							System.out.println(aunts.get(i).getName());
					
					break;
				}

				case 'w':{
					//Get the weights as doubles
					String[] remainders = remainder.split(",");
					Double[] weights = new Double[2];
					weights[0] = Double.parseDouble(remainders[0]);
					weights[1] = Double.parseDouble(remainders[1]);
					
					//Store all the people who are between the given weights
					List<Person> weight = famTree.getPeopleInWeightRange
							(weights[0],weights[1]);

					//Check if anyone in the family tree has the given eye color
					if (weight == null)
						System.out.println("person not found");
					else
						for (int i = 0; i < weight.size(); i++)
							System.out.println(weight.get(i).getName());

					break;
				}

				//Exits program
				case 'x':{
					stop = true;
					System.out.println("exit");
					break;
				}
				default:
					break;
				}

			}
		}
	}
}
