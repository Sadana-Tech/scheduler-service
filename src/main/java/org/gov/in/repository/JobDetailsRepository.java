package org.gov.in.repository;

import java.util.List;
import org.gov.in.entity.JobDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDetailsRepository extends JpaRepository<JobDetails, Integer> {

  JobDetails findByJobName(String jobName);

  List<JobDetails> findByStatus(String status);

}
