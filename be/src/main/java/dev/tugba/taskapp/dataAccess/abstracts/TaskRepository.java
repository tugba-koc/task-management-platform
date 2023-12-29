package dev.tugba.taskapp.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.tugba.taskapp.entities.concretes.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findById(int id);
    List<Task> findAllByUserId(int userId);
}
