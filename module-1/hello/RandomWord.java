/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int index = 1;
        String champion = "";

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();

            boolean accept = StdRandom.bernoulli((double) 1 / index);
            if (accept) {
                champion = item;
            }

            index++;
        }

        StdOut.println(champion);
    }
}
