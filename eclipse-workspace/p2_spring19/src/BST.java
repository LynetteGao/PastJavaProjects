import java.util.ArrayList; // allowed for creating traversal lists
import java.util.List; // required for returning List<K>

// TODO: Implement a Binary Search Tree class here
public class BST<K extends Comparable<K>, V> implements BSTADT<K, V> {

  // Tip: Use protected fields so that they may be inherited by AVL
  protected BSTNode<K, V> root;
  protected int numKeys; // number of keys in BST

  // Must have a public, default no-arg constructor
  public BST() {


  }

  private List<K> preOrderHelper(BSTNode<K, V> root, List<K> arr) {
    if (root == null) {
      return arr;
    }
    arr.add(root.key);
    preOrderHelper(root.left, arr);
    preOrderHelper(root.right, arr);

    return arr;

  }

  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getPreOrderTraversal()
   */
  @Override
  public List<K> getPreOrderTraversal() {
    List<K> list = new ArrayList<K>();
    preOrderHelper(this.root, list);
    System.out.println(list.size());
    return list;

  }

  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getPostOrderTraversal()
   */
  @Override
  public List<K> getPostOrderTraversal() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getLevelOrderTraversal()
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getInOrderTraversal()
   */
  @Override
  public List<K> getInOrderTraversal() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * // Add the key,value pair to the data structure and increase the number of keys. // If key is
   * null, throw IllegalNullKeyException; // If key is already in data structure, throw
   * DuplicateKeyException();
   * 
   * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    // TODO Auto-generated method stub
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (contains(key) == true) {
      throw new DuplicateKeyException();
    }

    this.root = insertHelper(root, key, value);
    this.numKeys++;
  }

  private BSTNode<K, V> insertHelper(BSTNode<K, V> root, K key, V value) {
    /* If the tree is empty, return a new node */
    if (root == null) {
      root = new BSTNode<K, V>(key, value);
      return root;
    }

    /* Otherwise, recur down the tree */
    if (key.compareTo(root.key) < 0)
      root.left = insertHelper(root.left, key, value);
    else if (key.compareTo(root.key) > 0)
      root.right = insertHelper(root.right, key, value);

    /* return the (unchanged) node pointer */
    return root;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#remove(java.lang.Comparable)
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#get(java.lang.Comparable)
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#contains(java.lang.Comparable)
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#numKeys()
   */
  @Override
  public int numKeys() {
    // TODO Auto-generated method stub
    return this.numKeys;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyAtRoot()
   */
  @Override
  public K getKeyAtRoot() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyOfLeftChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyOfRightChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getHeight()
   */
  @Override
  public int getHeight() {
    // TODO Auto-generated method stub
    return 0;
  }



}
