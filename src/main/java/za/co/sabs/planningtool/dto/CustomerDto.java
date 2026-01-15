package za.co.sabs.planningtool.dto;


import java.time.LocalDateTime;

public record CustomerDto(
        Long id,
        String customerId,
        String name,
        String email,
        String phoneNumber,
        String accountNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
