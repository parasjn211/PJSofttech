package com.votingapp.service;

import com.votingapp.model.Candidate;

import java.util.List;

public interface CandidateService {
    // Method to get all candidates
    List<Candidate> getAllCandidates();

    // Method to get candidate by ID
    Candidate getCandidateById(Long candidateId);

    // Method to update vote count for a candidate
    void updateVoteCount(Long candidateId);

}
