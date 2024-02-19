package ro.bogdan_mierloiu.javablockchain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.bogdan_mierloiu.javablockchain.core.BlockChain;
import ro.bogdan_mierloiu.javablockchain.core.Candidate;
import ro.bogdan_mierloiu.javablockchain.dto.BlockChainResponse;
import ro.bogdan_mierloiu.javablockchain.service.BlockChainService;
import ro.bogdan_mierloiu.javablockchain.service.CandidateService;
import ro.bogdan_mierloiu.javablockchain.service.VotingService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final BlockChainService blockChainService;
    private final VotingService votingService;
    private final CandidateService candidateService;

    @GetMapping
    public String index(Model model) {
        Optional<BlockChainResponse> blockChain = blockChainService.getBlockChainResponse();
        model.addAttribute("blockChain", blockChain.orElse(null));
        model.addAttribute("candidates", blockChainService.getCandidatesFromChain());
        model.addAttribute("blockChainRequest", new BlockChain());
        return "index";
    }

    @PostMapping("web/blockchain")
    public String initializeBlockChain(@ModelAttribute BlockChain blockChainRequest) {
        blockChainService.save(blockChainService.save(blockChainRequest));
        return "redirect:/";
    }

    @PostMapping("web/blockchain/genesis")
    public String buildGenesis() {
        blockChainService.buildGenesisBlock();
        return "redirect:/";
    }

    @PostMapping("web/blockchain/vote/{candidateId}")
    public String vote(@PathVariable Long candidateId) throws JsonProcessingException {
        votingService.vote(candidateId);
        return "redirect:/";
    }

    @GetMapping("web/blockchain/candidate")
    public String addCandidateForm(Model model) {
        model.addAttribute("candidateRequest", new Candidate());
        return "candidate";
    }

    @PostMapping("web/blockchain/candidate")
    public String addCandidate(@ModelAttribute Candidate candidate) throws JsonProcessingException {
        candidateService.save(candidate);
        return "redirect:/";
    }

}
