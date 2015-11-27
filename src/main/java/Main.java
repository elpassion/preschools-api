import models.School;
import services.SchoolsRepository;
import transformers.SchoolsLocationViewJsonTransformer;

import java.util.List;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/schools/locations", (req, res) -> {
            res.type("application/json; charset=utf-8");
            List<School> schools = SchoolsRepository.findAllForLocation();
            return schools;
        }, new SchoolsLocationViewJsonTransformer());

        get("/hello", (req, res) -> "Hello World");
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567;
    }
}