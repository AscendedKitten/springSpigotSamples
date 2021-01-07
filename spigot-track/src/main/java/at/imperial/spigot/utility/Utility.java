package at.imperial.spigot.utility;

import at.imperial.spring.domain.Territory;

public class Utility {
    public static boolean isWithin(int x, int z, Territory t) {
        return x >= t.getStartX() && x <= t.getEndX()
                && z >= t.getStartZ() && z <= t.getEndZ();
    }
}
