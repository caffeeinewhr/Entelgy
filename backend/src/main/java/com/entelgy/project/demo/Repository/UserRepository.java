package com.entelgy.project.demo.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.entelgy.project.demo.Entity.User;

@CrossOrigin(origins = "http://localhost:3000")
@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
