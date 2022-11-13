package org.launchcode.meander.meander.models;

import org.junit.jupiter.api.Test;
import org.launchcode.meander.models.User;
import org.launchcode.meander.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    private void clearTestDB() {
        User user = repo.findByEmail("default@email.com");
        if (user != null) {
            repo.delete(user);
        }

        user = repo.findByEmail("jdoe1@email.com");
        if (user != null) {
            repo.delete(user);
        }
    }

    @Test
    public void testCreateUser() {
        clearTestDB();

        User user = new User();
        user.setEmail("default@email.com");
        user.setPassword("password123");
        user.setFirstName("John");
        user.setLastName("Doe");

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }

    @Test
    public void testFindByEmail() {
        clearTestDB();

        String email = "jdoe1@email.com";
        User user = new User();
        user.setEmail(email);
        user.setPassword("password123");
        user.setFirstName("John");
        user.setLastName("Doe");
        repo.save(user);

        User foundUser = repo.findByEmail(email);
        assertThat(foundUser.getEmail()).isEqualTo(email);
    }
}
