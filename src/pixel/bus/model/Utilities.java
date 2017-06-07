package pixel.bus.model;

import java.util.Random;

/**
 * Created by vanley on 07/06/2017.
 */
public class Utilities {
    public static int getRandomIntegerFromRange(int min, int max){
        Random random = new Random();
        if (min > max) {
            throw new IllegalArgumentException("minimum cannot exceed maximum.");
        }
        long range = (long)max - (long)min + 1;
        long fraction = (long)(range * random.nextDouble());
        return (int)(fraction + min);
    }
}
