package com.votingapp.controller;

import com.votingapp.model.Candidate;
import com.votingapp.service.AdminService;
import com.votingapp.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final CandidateService candidateService;
    @Autowired
    public AdminController(AdminService adminService, CandidateService candidateService) {
        this.adminService = adminService;
        this.candidateService = candidateService;
    }

    // Add a new candidate
    @PostMapping("/addcandidate")
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return adminService.addCandidate(candidate.getName());
    }


    // Get all candidates
    @GetMapping("/candidates")
    public List<Candidate> getAllCandidates() {
        return adminService.getAllCandidates();
    }

    // Get vote count of a specific candidate
    @GetMapping("/candidate/votes")
    public int getVoteCount(@RequestParam Long id) {
        return adminService.getVoteCount(id);
    }
    // Get vote count of all candidates
    @GetMapping("/allvotes")
    public List<String> getAllCandidateVotes() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        return candidates.stream()
                .map(candidate -> "Candidate: " + candidate.getName() + ", Votes: " + candidate.getVoteCount())
                .toList();
    }
}
