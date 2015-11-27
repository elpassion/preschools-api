package services;

import models.School;
import org.sql2o.Connection;
import dao.Database;

import java.util.List;

/**
 * Created by aserafin on 26/11/15.
 */
public class SchoolsRepository {
    public static School findByRspo(String rspo) {
        String sql = "select * from schools where rspo = :rspo";
        try(Connection connection = Database.getConnection()) {
            return connection.createQuery(sql, false).addParameter("rspo", rspo).executeAndFetchFirst(School.class);
        }
    }

    public static School findById(Integer id) {
        String sql = "select * from schools where id = :id";
        try(Connection connection = Database.getConnection()) {
            return connection.createQuery(sql, false).addParameter("id", id).executeAndFetchFirst(School.class);
        }
    }

    public static List<School> findAll(String query) {
        String sql = "select * from schools where name ilike :query or address ilike :query or city ilike :query or postCode ilike :query or post ilike :query";
        try(Connection connection = Database.getConnection()) {
            return connection.createQuery(sql, false).addParameter("query", "%" + query + "%").executeAndFetch(School.class);
        }
    }

    public static List<School> findAll() {
        String sql = "select * from schools";
        try(Connection connection = Database.getConnection()) {
            return connection.createQuery(sql, false).executeAndFetch(School.class);
        }
    }

    public static List<School> findAllForRanking(Integer offset, Integer limit) {
        String sql = "select * from schools order by stars desc, id desc offset :offset limit :limit";
        try(Connection connection = Database.getConnection()) {
            return connection.createQuery(sql, false).addParameter("offset", offset).addParameter("limit", limit)
                    .executeAndFetch(School.class);
        }
    }

    public static List<School> findAllForLocation() {
        String sql = "select id, name, latitude, longitude from schools where latitude is not null and longitude is not null";
        try(Connection connection = Database.getConnection()) {
            return connection.createQuery(sql, false).executeAndFetch(School.class);
        }
    }

    public static void insertSchool(School school) {
        String sql = "insert into schools(name, address, postCode, post, city, regon, schooltype, ownershiptype, email, " +
                     "phone, rspo, latitude, longitude)" +
                     "values(:name, :address, :postCode, :post, :city, :regon, :schoolType, :ownershipType, :email, " +
                     ":phone, :rspo, :latitude, :longitude)";

        try(Connection connection = Database.getConnection()) {
            school.id = (Integer)connection.createQuery(sql).bind(school).executeUpdate().getKey();
        }
    }

    public static void updateSchool(School school) {
        String sql = "update schools set name = :name, address = :address, postcode = :postCode, post = :post, " +
                     "city = :city, regon = :regon, schooltype = :schoolType, ownershiptype = :ownershipType," +
                     "email = :email, phone = :phone, latitude = coalesce(:latitude, latitude), " +
                     "longitude = coalesce(:longitude, longitude) where id = :id";

        try(Connection connection = Database.getConnection()) {
            connection.createQuery(sql).bind(school).executeUpdate();
        }
    }
}
