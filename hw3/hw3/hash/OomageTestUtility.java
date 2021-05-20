package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        SimpleOomage o;
        int bucketNum;
        int[] count = new int[M];
        int N = oomages.size();
        for (int i = 0; i < N; i++) {
            bucketNum = (oomages.get(i).hashCode() & 0x7FFFFFFF) % M;
            count[bucketNum]++;
        }
        for (int i = 0; i < M; i++) {
            if ((count[i] < (N / 50)) || (count[i] > (N / 2.5))) {
                return false;
            }
        }
        return true;
    }
}
