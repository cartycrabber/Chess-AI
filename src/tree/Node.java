package tree;

import java.util.ArrayList;

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
	
	//Testing
	public static void main(String[] args) {
		Node<Integer, String> tree = new Node<Integer, String>(1);
		Node<Integer, String> child = new Node<Integer, String>(5, "First", tree);
		System.out.println(tree.depth());
		System.out.println(child.depth());
	}
}
