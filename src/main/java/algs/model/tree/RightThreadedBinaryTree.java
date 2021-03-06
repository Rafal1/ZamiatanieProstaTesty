package algs.model.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Unbalanced right-threaded binary tree.
 * 
 * Duplicates are allowed. The right child of a node in the tree is guaranteed to 
 * have its value be greater than or equal to its parent.
 * 
 * There is an artificial 'root' node whose left child is the real root of the tree.
 * The reason for this artificial node is to simplify the algs and to enable the
 * right-most node of the tree to have something to point to!
 * 
 * @param <T>     the base type of the values stored by the BinaryTree. Must be
 *                Comparable.
 * @author George Heineman
 * @version 1.0, 6/15/08
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public class RightThreadedBinaryTree<T extends Comparable> implements Iterable<T> {

	/** Artificial root of the tree (can't change once created in constructor). */
	final RightThreadedBinaryNode<T> artificialRoot;
	
	/** real root of the tree. */
	RightThreadedBinaryNode<T> root;
	
	/** Value. */
	T value;
	
	/** Empty iterator object, as needed. */
	private static EmptyIterator empty;
	
	/** Provide empty iterator for simplicity of implementation. */
	static final class EmptyIterator implements Iterator {
		public boolean hasNext() { return false; }
		public Object next() { throw new java.util.NoSuchElementException("Empty Iterator"); }
		public void remove() { throw new UnsupportedOperationException("Empty Iterator can't be modified"); }
	}
	
	/**
	 * Singleton instance of the empty iterator, available for use.
	 */
	private static EmptyIterator empty() {
		if (empty == null) {
			empty = new EmptyIterator();
		}
		
		return empty;
	}
	
	/** Default BinaryTree constructor. */
	public RightThreadedBinaryTree() {
		artificialRoot = constructArtificialRoot();
		setRoot(null);
	}
	
	/**
	 * Helper method to construct appropriately typed BinaryNode for this value.
	 * 
	 * Specialized Trees may override this behavior, as needed.
	 */
	RightThreadedBinaryNode<T> construct(T value) {
		return new RightThreadedBinaryNode<T>(value);
	}
	
	 /**
	  * Helper method to construct the artificial root node for the Right-Threaded Binary Tree.
	  */
	 RightThreadedBinaryNode<T> constructArtificialRoot() {
		 return new RightThreadedBinaryNode<T>(new Comparable<String>(){
				/** Never allow proper comparison. */
				public int compareTo(String o) {
					return -1;
				}}){
			 
			 // here for debugging ONLY
			 public String getValue() { return "sentinel"; }
			 
			 // This is a sentinel node.
			 public String toString () { return "sentinel"; }
		 };
	 }
	 
	 /**
	  * Helper method to properly set the root for the tree.
	  * 
	  * Note that setting the root properly links the node into the artificial root node. 
	  * 
	  * @param newRoot
	  */
	@SuppressWarnings("unchecked")
	protected void setRoot (RightThreadedBinaryNode<T> newRoot) {
		artificialRoot.left = newRoot;
		root = newRoot;
	}
	
	/** 
	 * Return the root of the tree.
	 */
	public RightThreadedBinaryNode<T> getRoot() {
		return root;
	}
	 
	/**
	 * Determine if the given value occurs in the tree.
	 * 
	 * Identical to BinaryTree::member(T) since searching in a ThreadedTree is identical.
	 * 
	 * @param value  non-null desired value to search for
	 * @return       true if the value is stored in the Binary Tree
	 * @exception    IllegalArgumentException if value is null
     * @exception    ClassCastException if the specified object's type prevents it
     *               from being compared to this object.
	 */
	@SuppressWarnings("unchecked")
	public boolean member (T value) {
		if (value == null) {
			throw new IllegalArgumentException ("BinaryTree cannot store 'null' values.");
		}
		
		// empty tree? Not found.
		if (root == null) {
			return false;
		}
		
		// compare and locate in proper location
		BinaryNode<T> node = root;
		while (node != null) {
			int c = value.compareTo(node.value);
		
			if (c == 0) {
				return true;
			}
			
			if (c < 0) {
				// Search to the left
				node = node.getLeftSon();
			} else {
				node = node.getRightSon();
			}
		}
		
		// not found.
		return false;
	}
	
	/**
	 * Remove the value from the tree.
	 * 
	 * The key is to find the predecessor-value (pv) to the target-value (tv). You can 
	 * set the value of 'tv' to be 'pv' and then delete the original 'pv' node by 
	 * considering the following two cases (note that the original pv node cannot have 
	 * a right child because it is the predecessor to tv). The two cases are:
	 * 
	 *   1. If pv has no children just delete it
	 *   2. If pv has a left child, then have pv's parent's right child become the
	 *      left child of pv.
	 * 
	 * Must properly manage deletions
	 * 
	 * @param value  non-null value to be removed
	 * @return true  if the value existed and was removed; otherwise return false
	 * @exception    IllegalArgumentException if value is null
     * @exception    ClassCastException if the specified object's type prevents it
     *               from being compared to this object.
	 */
	@SuppressWarnings("unchecked")
	public boolean remove (T value) {
		if (value == null) {
			throw new IllegalArgumentException ("BinaryTree cannot store 'null' values.");
		}
		
		// empty tree? 
		if (root == null) {
			return false;
		}

		// compare and place in proper location
		RightThreadedBinaryNode<T> node = root;
		RightThreadedBinaryNode<T> parent = null;
		RightThreadedBinaryNode<T> n;
		do {
			int c = value.compareTo(node.value);
		
			if (c < 0) {
				// removal to the left: If no child then we are done, otherwise recurse
				if ((n = node.getLeftSon()) == null) {
					break;
				} else {
					parent = node;
					node = n;  // iterate down to this one.
				}
			} else if (c > 0) {
				// removal to the right: If no child then we are done, otherwise recurse
				if ((n = node.getRightSon()) == null) {
					break;
				} else {
					parent = node;
					node = n;  // iterate down to this one.
				}				
			} else {
				// Multiple cases to consider:
				removeHelper (node, parent);
				return true;
			}
		} while (n != null);
		
		// nothing to do.
		return false;
	}
	

	
	/**
	 * Helper method to properly handle the multiple subcases when removing a node
	 * from the tree.
	 * 
	 * @param target    Node to be removed
	 * @param parent    parent of target node (or null if target is the root).
	 */
	@SuppressWarnings("unchecked")
	void removeHelper (RightThreadedBinaryNode<T> target, RightThreadedBinaryNode<T> parent) {
		RightThreadedBinaryNode<T> lnode = target.getLeftSon();
		RightThreadedBinaryNode<T> rnode = target.getRightSon();

		// 0. No children (i.e., a leaf node). Just pull it out
		// ------------------------------------------------------
		if (lnode == null && rnode == null) {
			// Deleting root
			if (parent == null) {
				root = null;
				return;
			}
			
			// Simply extract from parent.
			if (parent.getLeftSon() == target) {
				parent.left = null;
			} else {
				parent.right = null;
			}
			
			return;
		}
		
		// 1. target has only a left child
		// --------------------------------------------------
		if (lnode != null && rnode == null) {
			if (parent == null) {
				setRoot(lnode);
				return;
			}
			
			// Simply relink with parent.
			if (parent.getLeftSon() == target) {
				parent.left = lnode;
			} else {
				parent.right = lnode;
			}
			
			return;
		}
		
		// 2. target has only a right child
		// --------------------------------------------------
		if (rnode != null && lnode == null) {
			if (parent == null) {
				root = rnode;
				return;
			}
			
			// Simply relink with parent.
			if (parent.getLeftSon() == target) {
				parent.left = rnode;
			} else {
				parent.right = rnode;
			}
			
			return;
		}
		
		// 3. Tough Case. What if TWO children? Find the minimum value in the right
		//    sub-tree and remove it. Use that value as the replacement value for
		//    this target.
		RightThreadedBinaryNode<T> minNode = rnode;
		RightThreadedBinaryNode<T> rparent = null;
		while (minNode.getLeftSon() != null) {
			rparent = minNode;
			minNode = minNode.getLeftSon();
		}
		
		// if the right child has no left son, hence it is the next one. Since minNode has
		// no left child, we can just splice ourselves in. Take care about root!
		if (rparent == null) {
			target.value = minNode.value;
			target.right = minNode.right;
			return;
		} 
		
		// when we get here, rparent is parent of the new min node in the right sub-tree
		// once done, minNode is guaranteed to have no left child; this means it has either
		// ZERO or ONE children, so we recursively call removeHelper().
		T minValue = (T) minNode.getValue();
		removeHelper (minNode, rparent);
		
		// That's it!
		target.value = minValue;
	}
	
	/**
	 * Insert the value into its proper location in the Binary tree.
	 * 
	 * No balancing is performed.
	 * 
	 * @param value   non-null value to be added into the tree.
	 * @exception    IllegalArgumentException if value is null
     * @exception    ClassCastException if the specified object's type prevents it
     *               from being compared to this object.
	 */
	@SuppressWarnings("unchecked")
	public void insert (T value) {
		if (value == null) {
			throw new IllegalArgumentException ("BinaryTree cannot store 'null' values.");
		}
		
		RightThreadedBinaryNode<T> newNode = construct(value);
		
		// empty tree? This becomes the root. Since it is the right-most node in the tree (in fact it is
		// the only one) we must properly link in thread
		if (root == null) {
			setRoot(newNode);
			root.thread = artificialRoot;
			return;
		}
		
		// compare and place in proper location
		RightThreadedBinaryNode<T> node = root;
		RightThreadedBinaryNode<T> n;
		while (true) {
			int c = value.compareTo(node.value);
			if (c < 0) {
				// insert to the left: If no child then set, otherwise recurse
				if ((n = node.getLeftSon()) == null) {
					node.left = newNode;
					newNode.thread = node;    // inserting left-child updates thread in simple manner
					return;
				} else {
					node = n;  // iterate down to this one.
				}
			} else if (c >= 0) {
				// insert to the right: If no child then set, otherwise recurse
				if ((n = node.getRightSon()) == null) {
					node.right = newNode;
					newNode.thread = node.thread;   // simply hook up to where it was
					node.thread = null;             // blow it away
					return;
				} else {
					node = n;  // iterate down to this one.
				}				
			}
		}		
	}

	/**
	 * Create string representation of the Tree.  
	 * 
	 * Really only useful for debugging and testCase validation.
	 */
	public String toString() {
		if (root == null) { return "()"; }
		
		return formatNode(root);
	}

	/**
	 * Format the node, recursively.
	 * 
	 * @param node    desired node to be expressed as a String.
	 * @return
	 */
	private String formatNode(RightThreadedBinaryNode<T> node) {
		RightThreadedBinaryNode<T> n;
		StringBuilder response = new StringBuilder ("(");
		if ((n = node.getLeftSon()) != null) { response.append(formatNode(n)); }
		response.append (node.toString());
		if ((n = node.getRightSon()) != null) { response.append(formatNode(n)); }
		response.append (")");
		
		// flatten.
		return response.toString();
	}
	
	/**
	 * Accept a visitor for a inorder traversal.
	 */
	public void accept (IVisitor visitor) {
		if (root == null) return;
		
		accept (null, root, visitor);
	}
	
	private void accept (RightThreadedBinaryNode parent, RightThreadedBinaryNode node, IVisitor visitor) {
		
		// go left
		if (node.getLeftSon() != null) {
			accept (node, node.getLeftSon(), visitor);
		}
		
		// self
		visitor.visit(parent, node);
		
		// go right
		if (node.getRightSon() != null) {
			accept (node, node.getRightSon(), visitor);
		}
	}
	
	/**
	 * Use in-order traversal over the tree.
	 */
	@SuppressWarnings("unchecked")
	public Iterator<T> inorder() {
		
		// Take special use of right-threaded-ness. Find left most one and traverse
		// until artificialRoot is hit.
		// For those times when an iterator is invoked on empty tree, create
		// the null-iterator object (as static private member) and reuse.
		if (root == null) {
			return empty();
		}

		// Find left-most one.
		RightThreadedBinaryNode node = root;
		RightThreadedBinaryNode n;
		while ((n = node.getLeftSon()) != null) {
			node = n;
		}

		// so we have a Tree. Do the in-order traversal. HOWEVER these traversals do 
		// not enable for the removal of nodes. Note that node is guaranteed not
		// to be null here.
		return new ThreadUntilEnd(node);
	}
	
	/**
	 * Use pre-order traversal over the tree.
	 */
	@SuppressWarnings("unchecked")
	public Iterator<T> preorder() {
		// For those times when an iterator is invoked on empty tree, create
		// the null-iterator object (as static private member) and reuse.
		if (root == null) {
			return empty();
		}

		// so we have a Tree. Do the in-order traversal. HOWEVER these traversals do 
		// not enable for the removal of nodes.
		return new ValueExtractor (new  PreorderTraversal(root));
	}
	
	/**
	 * Use post-order traversal over the tree.
	 */
	@SuppressWarnings("unchecked")
	public Iterator<T> postorder() {
		// For those times when an iterator is invoked on empty tree, create
		// the null-iterator object (as static private member) and reuse.
		if (root == null) {
			return empty();
		}

		// so we have a Tree. Do the in-order traversal. HOWEVER these traversals do 
		// not enable for the removal of nodes.
		return new ValueExtractor (new  PostorderTraversal(root));
	}

	/**
	 * Provide useful in-order iteration over the values of the Binary Tree.
	 */
	public Iterator<T> iterator() {
		return inorder();
	}
	
	/**
	 * Perform an in-order traversal by following all 'thread' links, until we get to
	 * the artificial root.
	 * 
	 * To advance a threaded-node, simply follow the thread. Otherwise, if you get to
	 * a node that is not threaded, you go to its right child, then find its left-most
	 * descendant, to continue the thread.
	 *
	 * @author George Heineman
	 */
	class ThreadUntilEnd implements Iterator<T> {
		
		/** Place in the iteration. */
		RightThreadedBinaryNode<T> cur = null;
		
		/**
		 * Pick as the starting node the left-most node in the tree.
		 * 
		 * @param start
		 */
		ThreadUntilEnd (RightThreadedBinaryNode<T> start) {
			cur = start;
		}

		/** As long as we haven't hit the sentinel. */
		public boolean hasNext() {
			return (cur != artificialRoot);
		}

		/** Return next in the chain. */
		@SuppressWarnings("unchecked")
		public T next() {
			T val = (T) cur.getValue();
			
			// move on to the next one. If no thread, then find right child and get its left
			// most descendant.
			if (cur.isThread()) {
				cur = cur.thread;
			} else {
				cur = cur.getRightSon();
				RightThreadedBinaryNode<T> n;

				if (cur == null) {
					throw new NoSuchElementException ("next() invoked after all elements have been returned.");
				}
				
				// If cur.leftSon != last then we need to pursue further.
				while ((n = cur.getLeftSon()) != null) {
					cur = n;
				}
			}
			
			return val;
		}

		/** Deny attempts to remove. */
		public void remove() {
			throw new UnsupportedOperationException("ThreadUntilEnd is not mutable through the Iterator.");
		}
	}
	
	/** Placed here for the purpose of testing. */
	@SuppressWarnings("unchecked")
	public boolean validateArtificialRoot() {
		return (artificialRoot.value.compareTo(null) == -1);
	}	
}
