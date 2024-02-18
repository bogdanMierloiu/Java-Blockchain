package ro.bogdan_mierloiu.javablockchain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record BlockChainResponse(
        String name,
        List<BlockResponse> chain

) {
}
