package com.nonso.trackerApp.repository;

import com.nonso.trackerApp.model.Status;
import com.nonso.trackerApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
   // List<Task> findAllByCategory_Id(Long id);
    @Query(value = "SELECT * FROM task WHERE status=?1", nativeQuery = true)
    List<Task> findTasksByStatus(Status status);
}
