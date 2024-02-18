package ro.bogdan_mierloiu.javablockchain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.bogdan_mierloiu.javablockchain.core.Candidate;
import ro.bogdan_mierloiu.javablockchain.service.BlockChainService;
import ro.bogdan_mierloiu.javablockchain.service.CandidateService;

import java.util.List;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {

    private final BlockChainService blockChainService;
    private final CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<Candidate>> getCandidates() {
        return ResponseEntity.ok(blockChainService.getCandidatesFromChain());
    }

    @PostMapping
    public ResponseEntity<Candidate> addCandidate(
            @RequestBody Candidate candidate) throws JsonProcessingException {
        return ResponseEntity.ok(candidateService.save(candidate));
    }

}
