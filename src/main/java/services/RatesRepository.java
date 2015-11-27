package services;

import dao.Database;
import models.Rate;
import org.sql2o.Connection;

/**
 * Created by aserafin on 27/11/15.
 */
public class RatesRepository {
    public static void insertRate(Rate rate) {
        String sql = "insert into rates(schoolid, stars, ip, createdat) values(:schoolId, :stars, :ip, :createdAt)";
        try(Connection connection = Database.getConnection()) {
            rate.id = (Integer)connection.createQuery(sql).bind(rate).executeUpdate().getKey();
        }
    }
}
