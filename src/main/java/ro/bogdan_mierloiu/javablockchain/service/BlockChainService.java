package ro.bogdan_mierloiu.javablockchain.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.bogdan_mierloiu.javablockchain.core.Block;
import ro.bogdan_mierloiu.javablockchain.core.BlockChain;
import ro.bogdan_mierloiu.javablockchain.core.Candidate;
import ro.bogdan_mierloiu.javablockchain.dto.BlockChainResponse;
import ro.bogdan_mierloiu.javablockchain.mapper.BlockChainMapper;
import ro.bogdan_mierloiu.javablockchain.repository.BlockChainRepository;
import ro.bogdan_mierloiu.javablockchain.repository.BlockRepository;
import ro.bogdan_mierloiu.javablockchain.util.Constant;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class BlockChainService {


    private final BlockChainRepository blockChainRepository;
    private final MutualService mutualService;
    private final ObjectMapper objectMapper;
    private final BlockRepository blockRepository;

    public BlockChain save(BlockChain blockChain) {
        if (nonNull(getBlockChain())) {
            return getBlockChain();
        }
        blockChain.setChain(new ArrayList<>());
        return blockChainRepository.save(blockChain);
    }

    public BlockChain getBlockChain() {
        return blockChainRepository.findAll().stream()
                .findFirst()
                .orElse(null);
    }

    public Optional<BlockChainResponse> getBlockChainResponse() {
        BlockChain blockChain = getBlockChain();
        if (nonNull(blockChain)) {
            return Optional.of(BlockChainMapper.entityDoDto(blockChain));
        }
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    public List<Candidate> getCandidatesFromChain() {
        BlockChain blockChain = getBlockChain();
        if (nonNull(blockChain) && (nonNull(blockChain.getChain()) && !blockChain.getChain().isEmpty())) {
            try {
                String data = getLastBlock(blockChain).getData();
                List<Candidate> candidates = objectMapper.readValue(data, new TypeReference<>() {
                });
                return candidates.stream()
                        .sorted((candidate1, candidate2) -> Math.toIntExact(candidate2.getVotes() - candidate1.getVotes()))
                        .toList();

            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to deserialize candidates from block data", e);
            } catch (NoSuchElementException e) {
                throw new IllegalStateException("No candidates found in the blockchain", e);
            }
        }
        return new ArrayList<>();
    }

    public Block getLastBlock(BlockChain blockChain) {
        return blockChain.getChain().get(blockChain.size() - 1);
    }

    @Transactional
    public Block buildGenesisBlock() {
        try {
            Block genesisBlock = Block.builder()
                    .timeStamp(LocalDateTime.now())
                    .previousHash(Constant.GENESIS_PREV_HASH)
                    .data(objectMapper.writeValueAsString(mutualService.getInitialCandidates()))
                    .description("Genesis Block")
                    .blockChain(getBlockChain())
                    .nonce(0)
                    .build();
            genesisBlock.generateHash();
            return blockRepository.save(genesisBlock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
