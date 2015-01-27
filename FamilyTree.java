///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  FamilyTreeMain.java
// File:             FamilyTree.java
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
import java.lang.IllegalArgumentException;

/**
 * Create and manipulate a maternal family tree.
 * 
 * @author Navneet Reddy
 * @author Jason Tiedt
 */
public class FamilyTree {

	//Root of the tree
	private TreeNode root;
	//Number of people in the tree
	private int numPeople;
	//Temporarily stores the tree node of person
	private TreeNode tempNode;
	//Temporarily stores whether a person is in the tree
	private boolean tempContains;
	//Temporarily stores the people with the given eye color
	private List<Person> tempEyeColor;
	//Temporarily stores the people in the given weight range
	private List<Person> tempWeight;
	//Temporarily stores the siblings and cousins of a person
	private List<Person> tempCousins;
	//Stores the number of generations
	private int generations;

	/**
	 * Constructs an empty family tree.
	 */
	public FamilyTree(){
		root = null;
		numPeople = 0;

		tempNode = null;
		tempContains = false;
		tempEyeColor = new ArrayList<Person>();
		tempWeight = new ArrayList<Person>();
		tempCousins = new ArrayList<Person>();
		generations = 0;
	}

	/**
	 * Get the name of the first ancestor in this family tree.
	 * 
	 * @return name of the first ancestor in the family tree
	 */
	public String getFirstAncestor() {
		//Check if there is at least one person in the tree
		if (root == null)
			return null;
		
		return root.getPerson().getName();
	}

	/**
	 * Return the number of people in this family tree.
	 * 
	 * @return number of people in the family tree
	 */
	public int getNumPeople() {
		return numPeople;
	}

	/**
	 * Get the maximum number of generations from the first ancestor 
	 * to the farthest descendant in the family tree.
	 * 
	 * @return maximum number of generations in the tree
	 */
	public int getMaxGenerations() {
		//Check if there is at least one person in the tree
		if (root == null)
			return 0;
		
		//Gets the max number of generations from the root
		generations = countHeight(root);

		return generations + 1;
	}

	/**
	 * Recursively count the number of generations in the tree under the 
	 * given node.
	 * 
	 * @param node node to count the number of generations from
	 * @return maximum number of generations from the given node
	 */
	private int countHeight(TreeNode node) {
		//Base case to check if there are any more nodes
		if (node == null)
			return 0;
		
		//Store number of generations in all the subtrees
		List<Integer> heights = new ArrayList<Integer>();
		//Store number of generations in a subtree
		int x = 0;
		
		//Run through each subtree and store the number of generations in each
		for (int i = 0; i < node.getChildren().size();  i++)
		{
			x = countHeight(node.getChildren().get(i));
			heights.add(x);
		}
		
		//Sort the number of generations in each subtree to find the largest 
		//number of generations in the tree
		Collections.sort(heights);
		
		if (heights.size() == 0)
			return 0;
		else
			return 1 + heights.get(heights.size() - 1);
		
	}

	/**
	 * Get the person with the given name.
	 * 
	 * @param name name of the person to get
	 * @return person with the given name
	 */
	public Person getPerson(String name) {
		if (name == null)
			throw new IllegalArgumentException();

		//Check if the person is in the tree
		if (!contains(name))
			return null;
		else
		{
			getTreeNode(name,root);
			return tempNode.getPerson();
		}
	}

	/**
	 * Add the given person into the tree with the given parent.
	 * 
	 * @param person person to add to the tree
	 * @param parentName parent to add the person to
	 * @return true if the person is successfully added to the tree
	 */
	public boolean addPerson(Person person, String parentName) {
		//New node to add to the tree
		TreeNode newPerson;
		//Node of the parent
		TreeNode parent;

		//Checks if there is a person already in the tree 
		//or if there is no parent name given
		if (root == null && parentName == null)
		{
			root = new TreeNode(person,null);
			numPeople++;
			return true;
		}
		//Checks if the person is already in the family tree
		else if (contains(person.getName()))
			return false;
		//Checks if there is such parent
		else if (!contains(parentName))
			return false;

		getTreeNode(parentName,root);

		//Gets the tree node of the parent
		parent = tempNode;

		//Adds the new person into the tree
		newPerson = new TreeNode(person,parent);
		parent.addChild(newPerson);
		numPeople++;

		return true;
	}

	/**
	 * Checks if the given person is in the tree
	 * 
	 * @param name name of the person to search the tree for
	 * @return true if and only if the person is in the tree
	 */
	public boolean contains(String name) {
		if (name == null)
			throw new IllegalArgumentException();

		tempContains = false;
		contains(name,root);

		return tempContains;
	}

	/**
	 * Recursively search the tree to find if the person is in the tree.
	 * 
	 * @param name name of the person to search the tree for
	 * @param node the starting node to start search at
	 */
	private void contains(String name,TreeNode node) {
		//Base case to check if there are any more nodes
		if (node == null)
			return;

		//Check if the current node is the given person
		if (node.getPerson().getName().equals(name))
			tempContains = true;

		//Recursively search through the tree
		for (int i = 0; i < node.getChildren().size(); i++)
			contains(name,node.getChildren().get(i));
	}

	/**
	 * Remove the given person and all of their descendants from 
	 * the family tree.
	 * 
	 * @param name name of the person to remove from the tree
	 * @return true if the person is successfully removed from the tree
	 */
	public boolean removeWithName(String name) {
		if (name == null)
			throw new IllegalArgumentException();

		//Check if the person is in the tree
		if (!contains(name))
			return false;

		//Get the tree node of the person
		getTreeNode(name,root);

		//Check if the tree is empty or if the person is the first ancestor
		if (root == null)
			return false;
		else if (getFirstAncestor().equals(name))
		{
			root = null;
			numPeople = 0;
			return true;
		}
		
		//Store all the siblings of the person
		List<TreeNode> removerSibs = tempNode.getParent().getChildren();

		for (int i = 0; i < removerSibs.size(); i++)
		{
			//Remove all the descendants of the person
			if (removerSibs.get(i).getPerson().getName().equals(tempNode.getPerson().getName()))
			{
				removeChildren(tempNode);
				removerSibs.remove(i);
				numPeople--;
				
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Recursive remove all the descendants of the given tree node.
	 * 
	 * @param node tree node to remove the children of
	 */
	private void removeChildren(TreeNode node) {
		//Check if the node has any children
		if (node.getChildren().size() == 0)
			return;
		
		//Recursively search through the tree for all the children
		for (int i = 0; i < node.getChildren().size(); i++)
			removeChildren(node.getChildren().get(i));
		
		//Decrease the number of people in the tree
		numPeople = numPeople - node.getChildren().size();
	}

	/**
	 * Get all the people with the given eye color.
	 * 
	 * @param eyeColor eye color to search for
	 * @return list of people with the given eye color
	 */
	public List<Person> getPeopleWithEyeColor(String eyeColor) {
		if (eyeColor == null)
			throw new IllegalArgumentException();

		//Reset the list of people with the eye color
		tempEyeColor = new ArrayList<Person>();
		containsEyeColor(eyeColor,root);

		//Check if at least one person has the eye color
		if (tempEyeColor.size() == 0)
			return null;
		else
			return tempEyeColor;

	}

	/**
	 * Recursively search the tree for the people with the given eye color.
	 * 
	 * @param eyeColor eye color to search for
	 * @param node node at which to start searching the tree
	 */
	private void containsEyeColor(String eyeColor,TreeNode node) {
		//Base case to check if there are any more nodes
		if (node == null)
			return;

		//Check if the current person has the given eye color
		if (node.getPerson().getEyeColor().equals(eyeColor))
			tempEyeColor.add(node.getPerson());

		//Recursively search through the tree
		for (int i = 0; i < node.getChildren().size(); i++)
			containsEyeColor(eyeColor,node.getChildren().get(i));
	}

	/**
	 * Get all the people whose weight is within the given weight range.
	 * 
	 * @param weightOne first weight to compare
	 * @param weightTwo second weight to compare
	 * @return list of people whose weight is within the given range
	 */
	public List<Person> getPeopleInWeightRange
							(double weightOne, double weightTwo) {

		//Reset the list of people within the weight range
		tempWeight = new ArrayList<Person>();

		//Check which given weight is larger
		if (weightTwo < weightOne)
			containsWeight(weightTwo,weightOne,root);
		else
			containsWeight(weightOne,weightTwo,root);

		//Check if at least one person is within the weight range
		if (tempWeight.size() == 0)
			return null;
		else 
			return tempWeight;
	}

	/**
	 * Recursively search the tree for people whose weight is within 
	 * the given weight range.
	 * 
	 * @param weightOne first weight to compare
	 * @param weightTwo second weight to compare
	 * @param node node at which to start searching the tree
	 */
	private void containsWeight(double weightOne, double weightTwo, 
														TreeNode node) {
		//Base case to check if there are any more nodes
		if (node == null)
			return;

		//Check if the current person's weight is within the range
		if (node.getPerson().getWeight() >= weightOne &&
				node.getPerson().getWeight() <= weightTwo)
			tempWeight.add(node.getPerson());

		//Recursively search through the tree
		for (int i = 0; i < node.getChildren().size(); i++)
			containsWeight(weightOne,weightTwo,node.getChildren().get(i));
	}

	/**
	 * Get the aunts of the given person.
	 * 
	 * @param name name of the person to get the aunt of
	 * @return list of siblings of the given person
	 */
	public List<Person> getAunts(String name) {
		if (name == null)
			throw new IllegalArgumentException();

		//Check if the person is in the tree
		if (!contains(name))
			return null;

		getTreeNode(name,root);

		//Store all the aunts
		List<Person> aunts = new ArrayList<Person>();
		//Tree node of the given person
		TreeNode person = tempNode;

		if (person == root || person.getParent() == root)
			return null;

		aunts = getSiblings(person.getParent().getPerson().getName());

		return aunts;
	}

	/**
	 * Get the siblings of the given person.
	 * 
	 * @param name name of the person to get the siblings of
	 * @return list of siblings of the given person
	 */
	public List<Person> getSiblings(String name) {
		if (name == null)
			throw new IllegalArgumentException();

		//Check if the person is in the tree
		if (!contains(name))
			return null;

		getTreeNode(name,root);

		//Store all the siblings
		List<Person> sibs = new ArrayList<Person>();
		//Tree node of the given person
		TreeNode person = tempNode;
		//Tree nodes of all the siblings
		List<TreeNode> sibNodes = new ArrayList<TreeNode>();

		if (person == root)
			return null;

		sibNodes = person.getParent().getChildren();

		//Check if the sibling is not the given person
		for (int i = 0; i < sibNodes.size(); i++)
			if (!sibNodes.get(i).getPerson().getName().equals(name))
				sibs.add(sibNodes.get(i).getPerson());

		//Check if the person has any siblings
		if (sibs.size() == 0)
			return null;
		else
			return sibs;
	}

	/**
	 * Get the list of people who are either siblings or cousins of 
	 * the given person.
	 * 
	 * @param name name of the person to get the siblings and cousins of
	 * @return list of siblings and cousins of the given person
	 */
	public List<Person> getAllSiblingsCousins(String name) {
		if (name == null)
			throw new IllegalArgumentException();

		//Check if the person is in the tree
		if (!contains(name))
			return null;

		getTreeNode(name,root);

		//Tree node of the given person
		TreeNode person = tempNode;
		//Depth at which the person is in the tree
		int depth = -1;

		if (person == root)
			return null;

		//Get the depth of the person
		while (person != root)
		{
			person = person.getParent();
			depth++;
		}

		//Get all the siblings and cousins
		tempCousins = new ArrayList<Person>();
		getPeopleAtDepth(root,depth);

		//Check if the person is in the list of siblings and cousins
		for (int i = 0; i < tempCousins.size(); i++)
			if (tempCousins.get(i).getName().equals(name))
				tempCousins.remove(i);
		
		//Check if the person has any siblings or cousins
		if (tempCousins.size() == 0)
			return null;
		else
			return tempCousins;
	}

	/**
	 * Get all the people at the given depth.
	 * 
	 * @param node node to start searching the tree for the depth
	 * @param depth depth to get the people from
	 */
	private void getPeopleAtDepth(TreeNode node, int depth) {
		//Base case to check if there are any more nodes
		if (node == null)
			return;
		
		//Check if the desired depth has been reached and 
		//add all the siblings and cousins to the list
		if (depth == 0)
		{
			for (int i = 0; i < node.getChildren().size(); i++)
				tempCousins.add(node.getChildren().get(i).getPerson());
			
			return;
		}
		
		//Recursively search through the tree
		for (int i = 0; i < node.getChildren().size(); i++)
			getPeopleAtDepth(node.getChildren().get(i), depth - 1);
	}
	
	/**
	 * Get the great aunts of the given person.
	 * 
	 * @param name name of the person to get the great aunts of
	 * @return list of great aunts of the given person
	 */
	public List<Person> getGreatAunts(String name) {
		if (name == null)
			throw new IllegalArgumentException();

		//Check if the person is in the tree
		if (!contains(name))
			return null;

		getTreeNode(name,root);

		//Store all the great aunts
		List<Person> greatAunts = new ArrayList<Person>();
		//Tree node of the given person
		TreeNode person = tempNode;

		if (person == root || person.getParent() == root || 
				person.getParent().getParent() == root)
			return null;

		greatAunts = getSiblings(person.getParent().getParent().getPerson().getName());

		return greatAunts;
	}

	/**
	 * Recursively search the tree for the tree node of the person with 
	 * the given name.
	 * 
	 * @param name name of the person to search for
	 * @param node node at which to start searching the tree
	 */
	private void getTreeNode(String name, TreeNode node) {
		//Base case to check if there are any more nodes
		if (node == null)
			return;

		//Check if the current person is the given person
		if (node.getPerson().getName().equals(name))
			tempNode = node;

		//Recursively search through the tree
		for (int i = 0; i < node.getChildren().size(); i++)
			getTreeNode(name,node.getChildren().get(i));
	}
}
