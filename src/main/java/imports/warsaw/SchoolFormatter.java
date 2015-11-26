package imports.warsaw;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.maps.model.LatLng;
import models.School;
import services.GeolocationService;

/**
 * Created by aserafin on 26/11/15.
 */
public class SchoolFormatter {
    LinkedHashMap<String, String> data;

    public SchoolFormatter(LinkedHashMap<String, String> data) {
        this.data = data;

    }

    public School format() {
        School school = new School();
        school.setName(data.get("Nazwa placówki"));
        school.setPostCode(data.get("Kod pocztowy"));
        school.setCity(getCityOrPost());
        school.setRegon(data.get("Regon"));
        school.setEmail(data.get("E-mail"));
        school.setPhone(data.get("Telefon"));
        school.setRspo(data.get("\uFEFFNr RSPO"));
        school.setPost(data.get("Poczta"));
        school.setAddress(getAddress());
        school.setSchoolType(getSchoolType());
        school.setOwnershipType(getOwnershipType());

        return school;
    }

    private String getAddress() {
        String[] parts = { getStreetPart(), getCityIfDifferentThanPost() };
        return String.join(", ", removeEmptyEntries(Arrays.asList(parts)));
    }

    private String getStreetPart() {
        String[] parts = { getStreetOrCity(), getPropertyNumbers() };
        return String.join(" ", parts);
    }

    private String getSchoolType() {
        return data.get("Typ placówki");
    }

    private String getOwnershipType() {
        return data.get("Publiczność");
    }

    private String getCityOrPost() {
        return data.get("Miejscowość").isEmpty() ? data.get("Poczta") : data.get("Miejscowość");
    }

    private String getStreetOrCity() {
        return data.get("Ulica").isEmpty() ? data.get("Miejscowość") : data.get("Ulica");
    }

    private String getCityIfDifferentThanPost() {
        return !data.get("Miejscowość").isEmpty() && !Objects.equals(data.get("Miejscowość"), data.get("Poczta")) ? data.get("Miejscowość") : null;
    }

    private String getPropertyNumbers() {
        String[] parts = { data.get("Nr domu"), data.get("Nr mieszkania") };
        return String.join("/", removeEmptyEntries(Arrays.asList(parts)));
    }

    private List<String> removeEmptyEntries(List<String> source) {
        return source.stream().filter(s -> s != null && !s.isEmpty()).collect(Collectors.toList());
    }
}

