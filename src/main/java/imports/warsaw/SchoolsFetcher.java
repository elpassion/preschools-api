package imports.warsaw;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import models.School;

/**
 * Created by aserafin on 26/11/15.
 */
public class SchoolsFetcher {
    private String baseUrl;

    public SchoolsFetcher() {
        this.baseUrl = "https://api.um.warszawa.pl/api/action/datastore_search/";
    }

    public List<School> getSchools() {
        List<School> schools = new ArrayList<>();
        Integer offset = 0;
        ArrayList<LinkedHashMap<String, String>> data;

        do {
            data = this.getRawSchoolsData(offset);
            schools.addAll(data.stream().map(schoolData -> new SchoolFormatter(schoolData).format()).collect(Collectors.toList()));
            offset += 100;
        } while(data.size() > 0);

        return schools;
    }

    private ArrayList<LinkedHashMap<String,String>> getRawSchoolsData(Integer offset) {
        try {
            Client client = this.getApiClient();
            WebResource resource = client.resource(this.buildUrl(offset));
            ClientResponse response = resource.accept("application/json").get(ClientResponse.class);

            ObjectMapper mapper = new ObjectMapper();
            LinkedHashMap<String, Object> result = (LinkedHashMap<String,Object>) mapper.readValue(response.getEntity(String.class), HashMap.class).get("result");
            return  (ArrayList<LinkedHashMap<String,String>>) result.get("records");
        } catch (Exception ex) {
            System.out.println(String.format("Error while fetching schools from %s", this.buildUrl(offset)));
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

    private Client getApiClient() {
        Client client = Client.create();
        client.setConnectTimeout(5000);
        client.setReadTimeout(5000);
        return client;
    }

    private String buildUrl(Integer offset) {
        String resource_id = "1cae4865-bb17-4944-a222-0d0cdc377951";
        String filters = URLEncoder.encode("{\"Typ placówki\":\"Przedszkole\"}");
        return String.format("%s?resource_id=%s&filters=%s&offset=%s", this.baseUrl, resource_id, filters, offset);
    }
}
