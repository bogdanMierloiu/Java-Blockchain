package ro.bogdan_mierloiu.javablockchain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.bogdan_mierloiu.javablockchain.service.VotingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {

    private final VotingService votingService;

    @GetMapping("{candidateId}")
    public ResponseEntity<String> vote(@PathVariable("candidateId") Long candidateId) throws JsonProcessingException {
        votingService.vote(candidateId);
        return ResponseEntity.ok("Voted successfully");
    }


}
