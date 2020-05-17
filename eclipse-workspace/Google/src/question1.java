import java.util.ArrayList;
import java.util.Arrays;

public class question1 {
  public int[] solution(int[] stores, int[] houses) {
    Arrays.sort(stores);
    int[] list = new int[houses.length];
    for (int x = 0; x < houses.length; x++) {
      int n = stores.length;

      // Corner cases
      if (houses[x] <= stores[0])
        list[x] = stores[0];
      if (houses[x] >= stores[n - 1])
        list[list.length - 1] = stores[n - 1];

      // Doing binary search
      int i = 0, j = n, mid = 0;
      while (i < j) {
        mid = (i + j) / 2;

        if (stores[mid] == houses[x])
          list[x] = stores[mid];

        /*
         * If target is less than array element, then search in left
         */
        if (houses[x] < stores[mid]) {

          // If target is greater than previous
          // to mid, return closest of two
          if (mid > 0 && houses[x] >= stores[mid - 1]) {
            if (Math.abs(houses[x] - stores[mid - 1]) >= Math.abs(houses[x] - stores[mid]))
              list[x] = stores[mid];
            else
              list[x] = stores[mid - 1];
          }
          /* Repeat for left half */
          j = mid;
        }

        // If target is greater than mid
        else {
          if (mid < n - 1 && houses[x] <= stores[mid + 1])

            if (Math.abs(houses[x] - stores[mid + 1]) >= Math.abs(houses[x] - stores[mid]))
              list[x] = stores[mid];
            else
              list[x] = stores[mid + 1];
        }
        i = mid + 1; // update i
      }
    }

    return list;
  }
}


