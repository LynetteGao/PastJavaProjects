import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Filename: PackageManager.java 
 * Project: p4 
 * Authors:Lynette Gao and Roxin Liu
 * 
 * PackageManager is used to process json package dependency files and provide function that make
 * that information available to other users.
 * 
 * Each package that depends upon other packages has its own entry in the json file.
 * 
 * Package dependencies are important when building software, as you must install packages in an
 * order such that each package is installed after all of the packages that it depends on have been
 * installed.
 * 
 * For example: package A depends upon package B, then package B must be installed before package A.
 * 
 * This program will read package information and provide information about the packages that must
 * be installed before any given package can be installed. all of the packages in
 * 
 * You may add a main method, but we will test all methods with our own Test classes.
 */

/**
 * This class represents a package manager which user can make use of the package dependency
 * functions
 * 
 * @author lynette&roxin
 *
 */
public class PackageManager extends Graph {

  private Graph graph;// the internal structure of packageManager
  private ArrayList<Package> pack;// list that stores the packages in the PackageManager
  private ArrayList<String> visited;// list that stores the visited packages

  /**
   * Package Manager default no-argument constructor.
   */
  public PackageManager() {
    graph = new Graph();
    visited = new ArrayList<String>();
    pack = new ArrayList<Package>();
  }

  /**
   * Takes in a file path for a json file and builds the package dependency graph from it.
   * 
   * @param jsonFilepath the name of json data file with package dependency information
   * @throws FileNotFoundException if file path is incorrect
   * @throws IOException           if the give file cannot be read
   * @throws ParseException        if the given json cannot be parsed
   */
  public void constructGraph(String jsonFilepath)
      throws FileNotFoundException, IOException, ParseException {
    // set up the JSONParser
    Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
    JSONObject jo = (JSONObject) obj;
    JSONArray packages = (JSONArray) jo.get("packages");
    // read the json file
    for (int i = 0; i < packages.size(); i++) {
      JSONObject jsonPackage = (JSONObject) packages.get(i);
      String name = (String) jsonPackage.get("name");// get the name of the package
      JSONArray dependencies = (JSONArray) jsonPackage.get("dependencies");// get the dependency
                                                                           // arraylist
      String[] dependNames = new String[dependencies.size()];

      for (int j = 0; j < dependNames.length; j++) {
        dependNames[j] = (String) dependencies.get(j);
      }

      Package aPack = new Package(name, dependNames);
      pack.add(aPack);
      // build the graph by adding vertex and node
      graph.addVertex(aPack.getName());
      for (int j = 0; j < dependNames.length; j++) {
        graph.addEdge(name, dependNames[j]);
      }
    }
    // update the package list
    boolean missPack = true;
    for (int i = 0; i < packages.size(); i++) {
      String[] depCheck = pack.get(i).getDependencies();
      for (int j = 0; j < depCheck.length; j++) {
        for (int k = 0; k < packages.size(); k++) {
          if (depCheck[j].equals(pack.get(k).getName())) {
            missPack = false;
            break;
          }
        }
        if (missPack == true) {
          Package aPack = new Package();
          aPack.setName(depCheck[j]);
          String emptyDep[] = new String[0];
          aPack.setDependencies(emptyDep);
          pack.add(aPack);
        } else
          missPack = true;
      }
    }
  }



  /**
   * Helper method to get all packages in the graph.
   * 
   * @return Set<String> of all the packages
   */
  public Set<String> getAllPackages() {
    return graph.getAllVertices();

  }

  /**
   * Given a package name, returns a list of packages in a valid installation order.
   * 
   * Valid installation order means that each package is listed before any packages that depend upon
   * that package.
   * 
   * @return List<String>, order in which the packages have to be installed
   * 
   * @throws CycleException           if you encounter a cycle in the graph while finding the
   *                                  installation order for a particular package. Tip: Cycles in
   *                                  some other part of the graph that do not affect the
   *                                  installation order for the specified package, should not throw
   *                                  this exception.
   * 
   * @throws PackageNotFoundException if the package passed does not exist in the dependency graph.
   */
  public List<String> getInstallationOrder(String pkg)
      throws CycleException, PackageNotFoundException {
    if (!getAllPackages().contains(pkg)) {
      throw new PackageNotFoundException();
    }
    List<String> order = new ArrayList<String>();
    Stack<String> stk = new Stack<String>();
    stk.push(pkg);
    visited.add(pkg);
    while (!stk.isEmpty()) {
      String current = stk.peek();// peek the node on the top
      boolean visited = false;
      for (int i = 0; i < getPackage(current).getDependencies().length; i++) {

        if (stk.contains(getPackage(current).getDependencies()[i])) {
          throw new CycleException();// check the cycle : if the node is already in the
                                     // stack,throw cycle exception
        }
        // if the node has not been visited
        if (checkIsUnvisited(getPackage(current).getDependencies()[i])) {
          this.visited.add(getPackage(current).getDependencies()[i]);
          // push the successor to the stack
          stk.push(getPackage(current).getDependencies()[i]);
          // System.out.print(graph.getNode(current).getNeighbors().get(i).getData());
          visited = true;
          break;


        }

      }
      // if all successors of the current node are visited
      if (visited == false) {
        order.add(stk.pop());// pop out the node

      }
    }
    this.visited.clear();// update the visited list

    return order;

  }

  /**
   * Helper method that return whether the specific package has been visited when traversal
   * 
   * @param p the name of the package
   * @return true if the package has been visited
   */
  private boolean checkIsUnvisited(String p) {
    if (!this.visited.contains(p)) {
      return true;
    }
    return false;
  }

  /**
   * Getter for the package based on the name
   * 
   * @param name of the package
   * @return the Package of with the given name
   */
  private Package getPackage(String name) {
    for (int i = 0; i < pack.size(); i++) {
      if (pack.get(i).getName().equals(name)) {
        return pack.get(i);
      }
    }
    return null;
  }


  /**
   * Given two packages - one to be installed and the other installed, return a List of the packages
   * that need to be newly installed.
   * 
   * For example, refer to shared_dependecies.json - toInstall("A","B") If package A needs to be
   * installed and packageB is already installed, return the list ["A", "C"] since D will have been
   * installed when B was previously installed.
   * 
   * @return List<String>, packages that need to be newly installed.
   * 
   * @throws CycleException           if you encounter a cycle in the graph while finding the
   *                                  dependencies of the given packages. If there is a cycle in
   *                                  some other part of the graph that doesn't affect the parsing
   *                                  of these dependencies, cycle exception should not be thrown.
   * 
   * @throws PackageNotFoundException if any of the packages passed do not exist in the dependency
   *                                  graph.
   */
  public List<String> toInstall(String newPkg, String installedPkg)
      throws CycleException, PackageNotFoundException {
    List<String> newPkgOrder = getInstallationOrder(newPkg);// get installation order for the new
                                                            // package
    List<String> installedPkgOrder = getInstallationOrder(installedPkg);// get installation order
                                                                        // for the installed package
    // loop through the installed packages to see what is needed to install for the new package
    for (int j = 0; j < installedPkgOrder.size(); j++) {
      if (newPkgOrder.contains(installedPkgOrder.get(j)))
        newPkgOrder.remove(installedPkgOrder.get(j));
    }

    return newPkgOrder;


  }

  /**
   * Return a valid global installation order of all the packages in the dependency graph.
   * 
   * assumes: no package has been installed and you are required to install all the packages
   * 
   * returns a valid installation order that will not violate any dependencies
   * 
   * @return List<String>, order in which all the packages have to be installed
   * @throws CycleException if you encounter a cycle in the graph
   */

  public List<String> getInstallationOrderForAllPackages() throws CycleException {

    Stack<Package> stk = new Stack<Package>();
    List<String> order = new ArrayList<String>();

    String maxPack = getPackageWithMaxDependencies();
    try {
      order = getInstallationOrder(maxPack);// find the installation order for the package has
                                            // greatest dependencies
    } catch (PackageNotFoundException e1) {
      e1.printStackTrace();
    }

    for (Package aPack : pack) {
      if (!aPack.getName().equals(maxPack))
        stk.push(aPack);
    }
    // loop through all the package to see what is missing from the installed package
    for (int i = 0; i < stk.size(); i++) {

      try {
        List<String> result;
        result = toInstall(stk.get(i).getName(), maxPack);// get the to install order for the
                                                          // current package
        for (String aPackName : result) {
          order.add(aPackName);
          System.out.print(aPackName + " ");
        }
      } catch (PackageNotFoundException e) {
        e.printStackTrace();
      }
      // remove the duplicate package in the installation order
      for (int k = 0; k < order.size(); k++) {
        for (int j = k + 1; j < order.size(); j++) {
          if (order.get(k).equals(order.get(j)))
            order.remove(j);
        }
      }

    }

    return order;
  }



  /**
   * Find and return the name of the package with the maximum number of dependencies.
   * 
   * Tip: it's not just the number of dependencies given in the json file. The number of
   * dependencies includes the dependencies of its dependencies. But, if a package is listed in
   * multiple places, it is only counted once.
   * 
   * Example: if A depends on B and C, and B depends on C, and C depends on D. Then, A has 3
   * dependencies - B,C and D.
   * 
   * @return String, name of the package with most dependencies.
   * @throws CycleException if you encounter a cycle in the graph
   */
  public String getPackageWithMaxDependencies() throws CycleException {
    ArrayList<String> allPack = new ArrayList<String>();
    allPack.addAll(getAllPackages());
    int max = 0;
    int size = 0;
    String maxDepend = null;
    // get the installation order for all packages and compare the size of their dependencies
    for (String aPack : allPack) {
      try {
        size = getInstallationOrder(aPack).size();
      } catch (PackageNotFoundException e) {
        e.printStackTrace();
      }
      // update the max dependencies when the current package has greatest dependencies
      if (size > max) {
        max = size;
        maxDepend = aPack;
      }
    }

    return maxDepend;

  }


  public static void main(String[] args) {
    System.out.println("PackageManager.main()");



  }

}

