import models.Comment;
import models.Rate;
import models.School;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import services.CommentsRepository;
import services.CommentsService;
import services.RatesService;
import services.SchoolsRepository;
import transformers.JsonTransformer;
import transformers.SchoolsFullViewJsonTransformer;
import transformers.SchoolsLocationViewJsonTransformer;

import java.util.List;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        enableCORS("*", "*", "*");

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

        get("/schools/:id/comments", (request, response) -> {
            response.type("application/json; charset=utf-8");

            Integer schoolId = Integer.parseInt(request.params("id"));
            Integer offset = request.params("offset") == null ? 0 : Integer.parseInt(request.params("offset"));
            Integer limit = request.params("limit") == null ? 10 : Integer.parseInt(request.params("limit"));

            List<Comment> comments = CommentsRepository.findForSchoolId(schoolId, offset, limit);
            return comments;
        }, new JsonTransformer());

        post("/schools/:id/comments", "x-www-form-urlencoded", (request, response) -> {
            response.type("application/json; charset=utf-8");

            Integer schoolId = Integer.parseInt(request.params(":id"));
            MultiMap<String> params = parseRequestBody(request.body());
            Comment comment = CommentsService.createComment(schoolId, params.getString("nick"), params.getString("body"));
            return comment;
        }, new JsonTransformer());

        post("/schools/:id/rates", "x-www-form-urlencoded", (request, response) -> {
            response.type("application/json; charset=utf-8");

            Integer schoolId = Integer.parseInt(request.params(":id"));
            MultiMap<String> params = parseRequestBody(request.body());
            Integer stars = params.getString("stars") == null ? 0 : Integer.parseInt(params.getString("stars")) % 11;

            RatesService.createRate(schoolId, stars, request.ip());
            School school = SchoolsRepository.findById(schoolId);

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

    static MultiMap<String> parseRequestBody(String body) {
        MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8", -1);
        return params;
    }

    static void enableCORS(final String origin, final String methods, final String headers) {
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
        });
    }
}