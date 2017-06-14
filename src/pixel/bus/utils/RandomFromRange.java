package pixel.bus.utils;

import java.util.Random;

/**
 * Created by vanley on 07/06/2017.
 */
public class RandomFromRange {
    public static int get(int min, int max){
        Random random = new Random();
        if (min > max) {
            throw new IllegalArgumentException("minimum cannot exceed maximum.");
        }
        long range = (long)max - (long)min + 1;
        long fraction = (long)(range * random.nextDouble());
        return (int)(fraction + min);
    }
}
