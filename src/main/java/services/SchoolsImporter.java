package services;

import models.School;
import java.util.List;

/**
 * Created by aserafin on 26/11/15.
 */
public class SchoolsImporter {
    private List<School> schools;

    public SchoolsImporter(List<School> schools) {
        this.schools = schools;
    }

    public void perform() {
        schools.stream().forEach(school -> importSchool(school));
    }

    private void importSchool(School school) {
        School existingSchool = SchoolsRepository.findByRspo(school.rspo);

        if(existingSchool == null) {
            SchoolsRepository.insertSchool(school);
        } else {
            school.id = existingSchool.id;
            SchoolsRepository.updateSchool(school);
        }
    }
}
