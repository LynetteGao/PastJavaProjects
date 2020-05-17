import java.util.ArrayList;

public class Question3 {
  public int[] solution(int D, int[] A) {
    // write your code in Java SE 8
    ArrayList<Integer> parents = new ArrayList<Integer>();
    // find the tree or list the parents
    for (int i = 0; i < A.length; i++) {
      int num = A[i];
      if (num == -1) {
        parents.add(i);
      } else {
        if (parents != null) {
          for (int j = 0; j < parents.size(); j++) {
            if (parents.indexOf(j) == num) {
              parents.add(parents.indexOf(j) + 1, i);
            } else {
              parents.add(i);
            }
          }
        }
      }


    }
    // get the (n-D)th element of the array, which is the at distance from the node
    int[] list = new int[A.length];
    for (int i = 0; i < A.length; i++) {

    }
    return list;
  }
}
