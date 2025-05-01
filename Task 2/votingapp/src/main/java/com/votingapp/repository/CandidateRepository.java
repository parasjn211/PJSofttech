package com.votingapp.repository;

import com.votingapp.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    boolean existsByName(String name);
}
