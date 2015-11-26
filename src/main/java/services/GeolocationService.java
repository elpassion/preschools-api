package services;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

/**
 * Created by aserafin on 26/11/15.
 */
public class GeolocationService {
    private String address;

    public GeolocationService(String address) {
        this.address = address;
    }

    public LatLng getCoordinates() {
        try {
            GeoApiContext context = new GeoApiContext().setApiKey(System.getenv("GOOGLE_MAPS_KEY"));
            GeocodingResult[] results = GeocodingApi.geocode(context, this.address).await();
            return results.length > 0 ? results[0].geometry.location : null;
        } catch (Exception ex) {
            System.out.println(String.format("Error while fetching coordinates for address %s", this.address));
            ex.printStackTrace();
        }

        return null;
    }
}
