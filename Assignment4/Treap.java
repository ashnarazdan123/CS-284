//Ashna Razdan
//CS-284
package Assignment4;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {

	private Random priorityGenerator;
	protected Node<E> root;

	static class Node<E> {
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;

		/**
		 * constructor for node
		 * 
		 */
		public Node(E data, int priority) {
			this.data = data;
			this.priority = priority;
			left = null;
			right = null;
			if (data == null) {
				throw new NullPointerException("data cannot be null");
			}

		}

		/**
		 * helper function which rotates each node to the right
		 * 
		 * @return returns left node
		 */
		Node<E> rotateRight() {
			Node<E> L = this.left;
			Node<E> LR = this.left.right;

			L.right = this;
			this.left = LR;

			return L;
		}

		/**
		 * helper function which rotates each node to the left
		 * 
		 * @return returns right node
		 */
		Node<E> rotateLeft() {
			Node<E> R = this.right;
			Node<E> RL = this.right.left;

			R.left = this;
			this.right = RL;

			return R;
		}

		/**
		 * toString
		 * 
		 * @return returns the data of node as string
		 */
		public String toString() {
			return data.toString();
		}

	}

	/**
	 * constructor: creates an empty treap
	 */

	public Treap() {
	};

	/**
	 * constructor: creates an empty treap and initializes priority generator
	 */
	public Treap(long seed) {
		this.priorityGenerator = new Random(seed);
	};

	/**
	 * add a new node to the treap
	 * 
	 * @param key, the key of the node
	 * @return true when the node is successfully added
	 */
	boolean add(E key) {
		Node<E> n = new Node<E>(key, priorityGenerator.nextInt());
		return add(key, n.priority);
	}

	/**
	 * add a new node to the treap and sorts where it should be in the stack then
	 * calls helper function reheap
	 * 
	 * @param key,      the key of the node
	 * @param priority, the value of the node
	 * @return true when the node is successfully added
	 */
	boolean add(E key, int priority) {
		Node<E> n = new Node<E>(key, priority);
		if (this.root == null) {
			root = n;
			return true;
		}
		Node<E> current = root;
		Stack<Node<E>> myStack = new Stack<Node<E>>();
		int i = key.compareTo(current.data);
		while (current != null) {
			myStack.push(current);
			if (i < 0) {
				if (current.left == null) {
					current.left = n;
					break;
				}
				current = current.left;
			} else if (i > 0) {
				if (current.right == null) {
					current.right = n;
					break;
				}
				current = current.right;
			} else {
				return false;
			}
		}
		reheap(myStack, n);
		return true;
	}

	/**
	 * helper function for add, bubbles up the node into its proper place
	 * 
	 * @param myStack, a stack to sort the nodes
	 * @param current, the current node
	 */
	void reheap(Stack<Node<E>> myStack, Node<E> current) {
		if (myStack.isEmpty()) {
			return;
		}

		while (!myStack.isEmpty() && current.priority > myStack.peek().priority) {
			Node<E> parent = myStack.peek();
			myStack.pop();
			Node<E> LChild = parent.left;
			Node<E> RChild = parent.right;
			if (root == parent) {
				if (current == LChild) {
					root = parent.rotateRight();
					break;
				} else if (current == RChild) {
					root = parent.rotateLeft();
					break;
				} else {
					break;
				}
			}
			if (LChild == current) {
				parent.rotateRight();
				if (myStack.peek().left == parent) {
					myStack.peek().left = current;
				} else {
					myStack.peek().right = current;
				}
			} else { // when parent.right = current
				parent.rotateLeft();
				if (myStack.peek().left == parent) {
					myStack.peek().left = current;
				} else {
					myStack.peek().right = current;
				}
			}
		}
	}

	/**
	 * deletes a node off the tree, calls unheap to send the node down
	 * 
	 * @param key, the key of the node
	 * @return true when the node is successfully deleted
	 */
	boolean delete(E key) {
		if (find(key) == false) {
			return false;
		}
		unheap(key, root);
		return true;
	}

	/**
	 * helper function for delete that sends the node down depending on its position
	 * in the treap
	 * 
	 * @param key,  the key of the root
	 * @param root, the root the treap
	 * @return the root which needs to be deleted
	 */

	public Node<E> unheap(E key, Node<E> root) {
		if (root == null) {
			return null;
		}
		int i = key.compareTo(root.data);
		if (i < 0) { // left child
			root.left = unheap(key, root.left);
		} else if (i > 0) { // right child
			root.right = unheap(key, root.right);
		} else {
			if (root.left != null && root.right != null) { // both are not null
				if (root.left.priority < root.right.priority) {
					root = root.rotateRight();
					root.right = unheap(key, root.right);
				} else {
					root = root.rotateLeft();
					root.left = unheap(key, root.left);
				}
			} else {
				if (root.left != null) {
					root = root.left;
				} else { // go to the right
					root = root.right;
				}
			}
		}
		return root;
	}

	/**
	 * find method
	 * 
	 * @return The true if the node is found
	 */
	public boolean find(E key) {
		return find(root, key);
	};

	/**
	 * Recursive find method. Similar to BST find method.
	 * 
	 * @param root, the root of the treap
	 * @param key,  the key of the node
	 * @return The true if the node is found
	 */
	private boolean find(Node<E> root, E key) {
		if (root == null) {
			return false;
		}
		int cmpResult = key.compareTo(root.data);
		if (cmpResult == 0) {
			return true;
		}
		if (cmpResult > 0) {
			return find(root.right, key);
		} else {
			return find(root.left, key);
		}
	}

	/**
	 * toString
	 * 
	 * @return returns the string of the root
	 */
	public String toString() {
		return toString(root, 0);
	}

	/**
	 * toString
	 * 
	 * @return returns the string of the tree
	 */
	private String toString(Node<E> current, int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append(" ");
		}
		if (current == null) {
			sb.append("null\n");
		} else {
			sb.append("(" + "key=" + current.data.toString() + "," + " priority=" + current.priority + ")" + "\n");
			sb.append(toString(current.left, depth + 1));
			sb.append(toString(current.right, depth + 1));
		}
		return sb.toString();
	}

	public static void main(String args[]) {
		Treap<Integer> testTree = new Treap<Integer>();

		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		// testTree.find(26);

		System.out.println(testTree.toString());
		testTree.delete(5);
		System.out.print("delete" + testTree);
		// System.out.println(testTree.find(8));

	}

}
