///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  FamilyTreeMain.java
// File:             TreeNode.java
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

/* The TreeNode class represents a single node in the family tree. A node has 
 * the infomation of a person (as a Person) and also the infomation about its 
 * parent node (as a TreeNode) and children nodes (as a List of TreeNodes).
 */
public class TreeNode {
	private Person person;
	private TreeNode parentNode;
	private List<TreeNode> children;
	
	/** Constructs a TreeNode with person and parentNode. */
	public TreeNode (Person person, TreeNode parentNode) {
		this.person = person;
		this.parentNode = parentNode;
		children = new ArrayList<TreeNode>();
	}
	
	/** Return the person in this node */
	public Person getPerson() {
		return person;
	}
	
	/** Return the parent for the person in this node */
	public TreeNode getParent()	{
		return parentNode;
	}
	
	/** Return the children for the person in this node */
	public List<TreeNode> getChildren() {
		return children;
	}
	
	/** Add childNode to this node */
	public void addChild(TreeNode childNode) {
		children.add(childNode);
	}
}
