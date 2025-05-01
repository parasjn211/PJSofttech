package com.votingapp.service;

import com.votingapp.model.Candidate;
import com.votingapp.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService  {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate getCandidateById(Long candidateId) {
        return candidateRepository.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    @Override
    public void updateVoteCount(Long candidateId) {
        Candidate candidate = getCandidateById(candidateId);
        candidate.setVoteCount(candidate.getVoteCount() + 1); // Increment vote count
        candidateRepository.save(candidate);
    }
}
