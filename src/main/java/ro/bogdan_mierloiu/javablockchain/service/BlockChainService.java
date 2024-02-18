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

    public BlockChainResponse getBlockChainResponse() {
        return BlockChainMapper.entityDoDto(getBlockChain());
    }

    @Transactional(readOnly = true)
    public List<Candidate> getCandidatesFromChain() {
        BlockChain blockChain = getBlockChain();
        try {
            String data = getLastBlock(blockChain).getData();
            return objectMapper.readValue(data, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to deserialize candidates from block data", e);
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("No candidates found in the blockchain", e);
        }
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
