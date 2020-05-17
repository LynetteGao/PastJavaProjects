import java.util.ArrayList;

public class question2 {
  public String solution(String S, int K) {
    // write your code in Java SE 8
    ArrayList<Character> license = new ArrayList<Character>();
    for (int i = 0; i < S.length(); i++) {
      if(S.charAt(i)!='-')
      license.add(S.charAt(i));// create a arraylist with all the characters
    }
    String newS = new String();
    for (int i = license.size() - 1; i > (license.size() % K - 1); i = i - 4) {
      for (int j = 0; j <= K - 1; j++) {
        Character append = license.get(i - j);
        newS = append + newS;
      }
      newS = '-' + newS;
    }
    for (int k = license.size() % K - 1; k >= 0; k--) {
      Character append = license.get(k);
      newS = append + newS;
    }
    return newS;
  }
}
