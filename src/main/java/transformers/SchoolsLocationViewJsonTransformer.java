package transformers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ResponseTransformer;
import views.SchoolViews;

/**
 * Created by aserafin on 27/11/15.
 */
public class SchoolsLocationViewJsonTransformer implements ResponseTransformer {
    @Override
    public String render(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        return mapper.writerWithView(SchoolViews.Location.class).writeValueAsString(object);
    }
}
