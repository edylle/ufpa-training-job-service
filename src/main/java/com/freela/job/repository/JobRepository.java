package com.freela.job.repository;

import com.freela.job.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, String> {

    List<JobEntity> findByNameIgnoreCaseContaining(String name);

}
