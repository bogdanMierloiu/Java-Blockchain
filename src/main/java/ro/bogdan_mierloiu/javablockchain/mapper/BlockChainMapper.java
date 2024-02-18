package ro.bogdan_mierloiu.javablockchain.mapper;

import ro.bogdan_mierloiu.javablockchain.core.BlockChain;
import ro.bogdan_mierloiu.javablockchain.dto.BlockChainResponse;

public class BlockChainMapper {

    private BlockChainMapper() {
    }

    public static BlockChainResponse entityDoDto(BlockChain blockChain) {
        return BlockChainResponse.builder()
                .name(blockChain.getName())
                .chain(blockChain.getChain().stream()
                        .map(BlockMapper::entityToDto)
                        .toList())
                .build();
    }
}
