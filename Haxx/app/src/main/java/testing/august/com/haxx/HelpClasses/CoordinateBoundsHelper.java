package testing.august.com.haxx.HelpClasses;

/**
 * Created by Marcus Jacobsson on 2015-03-17.
 */
public class CoordinateBoundsHelper {
      /*
        Latitude (x) = 52.50 <= x <= 70.75
        Longitude (y) = 2.25 <= y <= 38.00
        */

    //South latitude limit
    private static final double ALATS = 52.50;
    //North longitude limit
    private static final double ALONW = 2.25;
    //North latitude limit
    private static final double ALATN = 70.75;
    //East longitude limit
    private static final double ALONE = 38.00;

    public static boolean isCoordinatesWithinBounds(double longitude, double latitude) {
        return ALATS <= latitude && latitude <= ALATN && ALONW <= longitude && longitude <= ALONE;
    }
}
