package ro.bogdan_mierloiu.javablockchain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.bogdan_mierloiu.javablockchain.core.Block;
import ro.bogdan_mierloiu.javablockchain.core.BlockChain;
import ro.bogdan_mierloiu.javablockchain.core.Candidate;
import ro.bogdan_mierloiu.javablockchain.repository.BlockRepository;
import ro.bogdan_mierloiu.javablockchain.util.Miner;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class VotingService {

    private final BlockChainService blockChainService;
    private final CandidateService candidateService;
    private final Miner miner;
    private final ObjectMapper objectMapper;
    private final BlockRepository blockRepository;

    @Transactional
    public void vote(Long candidateId) throws JsonProcessingException {
        BlockChain blockChain = blockChainService.getBlockChain();
        Block lastBlock = blockChainService.getLastBlock(blockChain);
        List<Candidate> candidates = objectMapper.readValue(lastBlock.getData(), new TypeReference<>() {
        });

        candidates.stream()
                .filter(candidate -> candidate.getId().equals(candidateId))
                .findFirst()
                .ifPresent(candidate -> candidate.setVotes(candidate.getVotes() + 1));

        Block voteBlock = Block.builder()
                .timeStamp(LocalDateTime.now())
                .previousHash(lastBlock.getHash())
                .data(objectMapper.writeValueAsString(candidates))
                .description("Vote Block")
                .blockChain(blockChain)
                .nonce(0)
                .build();
        voteBlock.generateHash();
        miner.mine(voteBlock, blockChainService.getBlockChain());
    }
}
