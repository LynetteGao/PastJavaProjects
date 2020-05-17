import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
// learn how Scanner instances that are connected to the keyboard work.
public class ScannerPractice {
    // A single instance of a Scanner connected to default input (keyboard)
//A single instance of a Scanner connected to default input (keyboard)
  private static final Scanner STDIN = new Scanner(System.in);

  // args - unused
  public static void main(String [] args) {

      // try different methods of the Scanner STDIN
      while ( STDIN.hasNextLine() ) {
          // put each result on a new output line and within brackets
          System.out.println("yes");
      }
  }
}
