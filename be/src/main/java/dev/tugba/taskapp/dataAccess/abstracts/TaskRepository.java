package dev.tugba.taskapp.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.tugba.taskapp.entities.concretes.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findById(int id);
    Optional<List<Task>> findAllByUserIdOrderById(int userId);
}
