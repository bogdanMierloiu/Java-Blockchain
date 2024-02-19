package ro.bogdan_mierloiu.javablockchain.mapper;

import ro.bogdan_mierloiu.javablockchain.core.Block;
import ro.bogdan_mierloiu.javablockchain.core.BlockChain;
import ro.bogdan_mierloiu.javablockchain.dto.BlockChainResponse;
import ro.bogdan_mierloiu.javablockchain.dto.BlockResponse;

public class BlockMapper {

    private BlockMapper() {
    }

    public static BlockResponse entityToDto(Block block) {
        return BlockResponse.builder()
                .id(block.getId())
                .timestamp(block.getTimeStamp())
                .previousHash(block.getPreviousHash())
                .hash(block.getHash())
                .description(block.getDescription())
                .build();
    }
}
