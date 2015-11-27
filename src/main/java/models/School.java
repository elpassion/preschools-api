package models;

import com.fasterxml.jackson.annotation.JsonView;
import views.SchoolViews;

/**
 * Created by aserafin on 26/11/15.
 */

public class School {
    @JsonView(SchoolViews.Base.class)
    public Integer id;

    @JsonView(SchoolViews.Base.class)
    public String name;

    @JsonView(SchoolViews.Full.class)
    public String address;

    @JsonView(SchoolViews.Full.class)
    public String postCode;

    @JsonView(SchoolViews.Full.class)
    public String post;

    @JsonView(SchoolViews.Full.class)
    public String city;

    @JsonView(SchoolViews.Full.class)
    public String regon;

    @JsonView(SchoolViews.Full.class)
    public String schoolType;

    @JsonView(SchoolViews.Full.class)
    public String ownershipType;

    @JsonView(SchoolViews.Full.class)
    public String email;

    @JsonView(SchoolViews.Full.class)
    public String phone;

    @JsonView(SchoolViews.Full.class)
    public String rspo;

    @JsonView(SchoolViews.Location.class)
    public Double latitude;

    @JsonView(SchoolViews.Location.class)
    public Double longitude;

    public String addressForGeolocation() {
        return String.format("%s %s %s %s", name, address, postCode, city);
    }
}
