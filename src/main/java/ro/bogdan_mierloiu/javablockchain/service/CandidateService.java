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
import ro.bogdan_mierloiu.javablockchain.repository.CandidateRepository;
import ro.bogdan_mierloiu.javablockchain.util.Miner;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final BlockChainService blockChainService;
    private final ObjectMapper objectMapper;
    private final Miner miner;

    @Transactional
    public Candidate save(Candidate candidate) throws JsonProcessingException {
        candidate.setVotes(0L);
        Candidate candidateSaved = candidateRepository.save(candidate);

        BlockChain blockChain = blockChainService.getBlockChain();
        Block lastBlock = blockChainService.getLastBlock(blockChain);
        List<Candidate> candidates = objectMapper.readValue(lastBlock.getData(), new TypeReference<>() {
        });
        candidates.add(candidateSaved);
        Block addCandidateBlock = Block.builder()
                .timeStamp(LocalDateTime.now())
                .previousHash(lastBlock.getHash())
                .data(objectMapper.writeValueAsString(candidates))
                .description("Add Candidate Block")
                .blockChain(blockChain)
                .nonce(0)
                .build();
        addCandidateBlock.generateHash();
        miner.mine(addCandidateBlock, blockChain);
        return candidateRepository.save(candidate);
    }

}
