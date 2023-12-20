package dev.tugba.taskapp.dataAccess.abstracts;

import org.springframework.stereotype.Repository;

import dev.tugba.taskapp.entities.concretes.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByTurkishId(String turkishId);
}
