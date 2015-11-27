package transformers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import spark.ResponseTransformer;

/**
 * Created by aserafin on 27/11/15.
 */
public class JsonTransformer implements ResponseTransformer {
    @Override
    public String render(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        return mapper.writeValueAsString(object);
    }
}
