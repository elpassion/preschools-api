package imports;

import imports.warsaw.SchoolsFetcher;
import models.School;
import services.SchoolsImporter;
import services.SchoolsService;

import java.util.List;

/**
 * Created by aserafin on 26/11/15.
 */
public class Warsaw {
    public static void main(String[] args) throws Exception {
        SchoolsFetcher fetcher = new SchoolsFetcher();

        List<School> schools = fetcher.getSchools();
        new SchoolsImporter(schools).perform();

        SchoolsService.updateGeolocationData();
    }
}
