package org.launchcode.meander.models.data;

import org.launchcode.meander.models.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
    @Query("SELECT t FROM Location t WHERE t.city = ?1 AND t.state = ?2 AND t.country = ?3")
    Location findLocationByCityStateCountry(String city, String state, String country);
}
