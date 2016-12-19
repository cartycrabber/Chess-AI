package tree;

import java.util.ArrayList;

import gameBoard.Board;
import gameBoard.Move;

/**
 * A node for building a tree that stores the node data and the link leading to that data
 * @author carty
 *
 * @param <T> The type of the node data
 * @param <U> The type of the link leading to the node
 */
public class Node<T, U> {

	private T data;
	private U link;
	
	private Node<T, U> parent;
	private ArrayList<Node<T, U>> children;
	
	public Node(T data, U link, Node<T, U> parent) {
		this.data = data;
		this.link = link;
		this.parent = parent;
		children = new ArrayList<Node<T, U>>();
	}
	
	public Node(T data) {
		this(data, null, null);
	}
	
	public int depth() {
		int d = 0;
		Node<T, U> node = getParent();
		while(node != null) {
			d++;
			node = node.getParent();
		}
		
		return d;
	}
	
	public ArrayList<Node<T, U>> getLeaves() {
		ArrayList<Node<T, U>> leaves = new ArrayList<Node<T, U>>();
		if((children == null) || children.isEmpty()) {
			leaves.add(this);
			return leaves;
		}
		for(Node child : children) {
			leaves.addAll(child.getLeaves());
		}
		return leaves;
	}
	
	//Basic Getters and Setters
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public void setLink(U link) {
		this.link = link;
	}
	
	public U getLink() {
		return link;
	}
	
	public void setParent(Node<T, U> parent) {
		this.parent = parent;
	}
	
	public Node<T, U> getParent() {
		return parent;
	}
	
	public void setChildren(ArrayList<Node<T, U>> children) {
		this.children = children;
	}
	
	public void addChild(Node<T, U> child) {
		children.add(child);
	}
	
	public ArrayList<Node<T, U>> getChildren() {
		return children;
	}
	
	public String toString() {
		return link + "---" + data; 
	}
	
	//Testing
	public static void main(String[] args) {
		Node<Integer, String> tree = new Node<Integer, String>(1);
		Node<Integer, String> n1 = new Node<Integer, String>(2, "First", tree); 
		tree.addChild(n1);
		tree.addChild(new Node<Integer, String>(3, "First", tree));
		tree.addChild(new Node<Integer, String>(4, "First", tree));
		n1.addChild(new Node<Integer, String>(5, "Second", tree));
		System.out.println(tree.getLeaves());
	}
	
	/**
	 * Print out a graphical representation of a tree
	 * Warning: Don't use with large trees
	 * @param n
	 * @param depth
	 */
	public static void printTree(Node n, int depth) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(n);
		for(int y = 0; y < depth; y++) {
			for(int x = 0; x < nodes.size(); x++) {
				System.out.print(nodes.get(x) + "   ");
			}
			nodes = n.getChildren();
			System.out.println();
		}
	}
}
