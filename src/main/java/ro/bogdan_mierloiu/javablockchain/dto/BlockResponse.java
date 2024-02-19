package ro.bogdan_mierloiu.javablockchain.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BlockResponse(
        Long id,
        LocalDateTime timestamp,
        String previousHash,
        String hash,
        String description
) {
}
