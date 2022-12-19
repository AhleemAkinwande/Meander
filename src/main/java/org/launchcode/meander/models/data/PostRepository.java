package org.launchcode.meander.models.data;

import org.launchcode.meander.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    public List<Post> findByLocationId(Integer locationId);

    @Query(value = "select * from post a join post_activity_type b on a.id=b.post_id where b.activity_type = ?1",
             nativeQuery = true)
    List<Post> findPostsByActivity(String activity);
}
