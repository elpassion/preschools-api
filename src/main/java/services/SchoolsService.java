package services;

import com.google.maps.model.LatLng;
import models.School;

import java.util.List;

/**
 * Created by aserafin on 26/11/15.
 */
public class SchoolsService {
    public static void updateGeolocationData() {
        List<School> schools = SchoolsRepository.findAll();

        schools.forEach(school -> {
            LatLng location = new GeolocationService(school.addressForGeolocation()).getCoordinates();
            if (location != null) {
                school.longitude = location.lng;
                school.latitude = location.lat;

                SchoolsRepository.updateSchool(school);
            }
        });
    }
}
