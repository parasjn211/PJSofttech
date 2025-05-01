package com.votingapp.service;

import com.votingapp.model.Candidate;

import java.util.List;

public interface AdminService {
    // Method to add a new candidate
    Candidate addCandidate(String name);

    // Method to view all candidates and their vote counts
    List<Candidate> getAllCandidates();

    // Method to view the vote count of a specific candidate
    int getVoteCount(Long candidateId);
}
