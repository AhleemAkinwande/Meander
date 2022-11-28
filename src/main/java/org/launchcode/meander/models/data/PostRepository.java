package org.launchcode.meander.models.data;

import org.launchcode.meander.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    Post postImage(Post post);
}
