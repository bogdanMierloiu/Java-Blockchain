package ro.bogdan_mierloiu.javablockchain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.bogdan_mierloiu.javablockchain.core.Block;
import ro.bogdan_mierloiu.javablockchain.core.BlockChain;
import ro.bogdan_mierloiu.javablockchain.service.BlockChainService;
import ro.bogdan_mierloiu.javablockchain.service.VotingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blockchain")
public class BlockChainController {

    private final BlockChainService blockChainService;

    @PostMapping
    public ResponseEntity<BlockChain> initializeBlockChain(
            @RequestBody BlockChain blockChain) {
        return ResponseEntity.ok(blockChainService.save(blockChain));
    }

    @PostMapping("/genesis")
    public ResponseEntity<Block> buildGenesis() {
        return ResponseEntity.ok(blockChainService.buildGenesisBlock());
    }


}
