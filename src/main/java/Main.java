import models.School;
import services.SchoolsRepository;
import transformers.SchoolsFullViewJsonTransformer;
import transformers.SchoolsLocationViewJsonTransformer;

import java.util.List;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/schools/locations", (request, response) -> {
            response.type("application/json; charset=utf-8");
            List<School> schools = SchoolsRepository.findAllForLocation();
            return schools;
        }, new SchoolsLocationViewJsonTransformer());

        get("/schools/:id", (request, ressponse) -> {
            ressponse.type("application/json; charset=utf-8");
            School school = SchoolsRepository.findById(Integer.parseInt(request.params(":id")));
            return school;
        }, new SchoolsFullViewJsonTransformer());

        exception(NumberFormatException.class, (e, request, response) -> {
            response.status(400);
            response.body(String.format("Integer.parseInt error for %s", e.getMessage()));
        });
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567;
    }
}