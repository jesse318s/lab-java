import java.util.ArrayList;

public class BinarySearchTree {
	Node root;

	public BinarySearchTree() {
		this.root = null;
	}

	public BinarySearchTree(Node p) {
		this.root = p;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean isValid(Node node, int min, int max) {
		if (node == null)
			return true;

		if (node.getElement() <= min || node.getElement() >= max)
			return false;

		return isValid(node.getLeft(), min, node.getElement()) &&
				isValid(node.getRight(), node.getElement(), max);
	}

	public boolean setRoot(Integer e) {
		if (this.root == null) {
			this.root = new Node(e);

			return true;
		}

		return false;
	}

	public int size(Node start) {
		if (start == null)
			return 0;

		return 1 + size(start.getLeft()) + size(start.getRight());
	}

	public int depth(Node target) {
		Node current = this.root;
		int depth = 0;

		while (current != null) {
			if (current == target) {
				return depth;
			} else if (current.getElement() > target.getElement()) {
				current = current.getLeft();
				depth++;
			} else if (current.getElement() < target.getElement()) {
				current = current.getRight();
				depth++;
			}
		}

		return -1;
	}

	public int height(Node start) {
		if (start == null)
			return -1;

		return 1 + Math.max(height(start.getLeft()), height(start.getRight()));
	}

	public Node parent(Node start, Node target, Node parent) {
		if (start == null || start == target)
			return parent;

		parent = start;

		Node left = parent(start.getLeft(), target, parent);
		Node right = parent(start.getRight(), target, parent);

		return (left != null) ? left : right;
	}

	public int numChildren(Node target) {
		int count = 0;

		if (target.getLeft() != null)
			count++;

		if (target.getRight() != null)
			count++;

		return count;
	}

	public boolean isInternal(Node target) {
		return target.getLeft() != null || target.getRight() != null;
	}

	public boolean isExternal(Node target) {
		return target.getLeft() == null && target.getRight() == null;
	}

	public Node findMin(Node start) {
		if (start == null)
			return null;

		Node current = start;

		while (current.getLeft() != null) {
			current = current.getLeft();
		}

		return current;
	}

	public Integer findMax(Node start) {
		if (start == null)
			return null;

		Node current = start;

		while (current.getRight() != null) {
			current = current.getRight();
		}

		return current.getElement();
	}

	public boolean insert(Integer newElement, Node start) {
		if (start == null) {
			this.root = new Node(newElement);

			return true;
		}

		if (newElement < start.getElement()) {
			if (start.getLeft() == null) {
				start.setLeft(new Node(newElement));

				return true;
			} else {
				return insert(newElement, start.getLeft());
			}
		} else if (newElement > start.getElement()) {
			if (start.getRight() == null) {
				start.setRight(new Node(newElement));

				return true;
			} else {
				return insert(newElement, start.getRight());
			}
		}

		return false;
	}

	public Node search(Node start, int target) {
		if (start == null || start.getElement() == target)
			return start;

		if (target < start.getElement()) {
			return search(start.getLeft(), target);
		} else {
			return search(start.getRight(), target);
		}
	}

	public void remove(int target) {
		root = removeRecursive(root, target);
	}

	private Node removeRecursive(Node root, int target) {
		if (root == null)
			return null;

		if (target < root.getElement()) {
			root.setLeft(removeRecursive(root.getLeft(), target));
		} else if (target > root.getElement()) {
			root.setRight(removeRecursive(root.getRight(), target));
		} else {
			if (root.getLeft() == null) {
				return root.getRight();
			} else if (root.getRight() == null) {
				return root.getLeft();
			}

			root.setElement(findMin(root.getRight()).getElement());
			root.setRight(removeRecursive(root.getRight(), root.getElement()));
		}

		return root;
	}

	public ArrayList<Integer> inOrder(Node start, ArrayList<Integer> list) {
		if (start != null) {
			inOrder(start.getLeft(), list);
			list.add(start.getElement());
			inOrder(start.getRight(), list);
		}

		return list;
	}

	public void cutBranch(Node start) {
		if (start != null) {
			cutBranch(start.getLeft());
			cutBranch(start.getRight());
			start.setLeft(null);
			start.setRight(null);
		}
	}

	public boolean isIdentical(Node xRoot, Node yRoot) {
		if (xRoot == null && yRoot == null)
			return true;

		if (xRoot != null && yRoot != null && xRoot.getElement().equals(yRoot.getElement()))
			return isIdentical(xRoot.getLeft(), yRoot.getLeft()) &&
					isIdentical(xRoot.getRight(), yRoot.getRight());

		return false;
	}

	public Node findLCA(Node p, Node q) {
		Node current = this.root;

		while (current != null) {
			if (current.getElement() > p.getElement() && current.getElement() > q.getElement()) {
				current = current.getLeft();
			} else if (current.getElement() < p.getElement() &&
					current.getElement() < q.getElement()) {
				current = current.getRight();
			} else {
				return current;
			}
		}

		return null;
	}
}