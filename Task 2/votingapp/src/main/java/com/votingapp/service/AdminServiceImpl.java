package com.votingapp.service;

import com.votingapp.model.Candidate;
import com.votingapp.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final CandidateRepository candidateRepository;

    public AdminServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate addCandidate(String name) {
        if (candidateRepository.existsByName(name)) {
            throw new RuntimeException("Candidate with this name already exists");
        }

        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setVoteCount(0); // Initialize vote count to 0
        return candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public int getVoteCount(Long candidateId) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        if (candidate.isPresent()) {
            return candidate.get().getVoteCount();
        } else {
            throw new RuntimeException("Candidate not found");
        }
    }
}
