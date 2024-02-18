package ro.bogdan_mierloiu.javablockchain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.bogdan_mierloiu.javablockchain.core.Candidate;
import ro.bogdan_mierloiu.javablockchain.service.BlockChainService;

import java.util.List;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {

    private final BlockChainService blockChainService;

    @GetMapping
    public ResponseEntity<List<Candidate>> getCandidates() {
        return ResponseEntity.ok(blockChainService.getCandidates());
    }

}
