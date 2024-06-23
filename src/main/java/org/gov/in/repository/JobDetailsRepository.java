package org.gov.in.repository;

import org.gov.in.entity.JobDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobDetailsRepository extends JpaRepository<JobDetails, Integer> {

    JobDetails findByJobName(String jobName);

    List<JobDetails> findByStatus(String status);

}
