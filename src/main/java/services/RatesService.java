package services;

import models.Rate;
import org.joda.time.DateTime;

/**
 * Created by aserafin on 27/11/15.
 */
public class RatesService {
    public static Rate createRate(Integer schoolId, Integer stars, String ip) {
        Rate rate = new Rate();
        rate.schoolId = schoolId;
        rate.stars = stars;
        rate.ip = ip;
        rate.createdAt = DateTime.now();

        RatesRepository.insertRate(rate);
        return rate;
    }
}
