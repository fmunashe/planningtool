package za.co.sabs.planningtool.dto;


public record CustomerDto(
        String customerId,
        String name,
        String email,
        String phoneNumber,
        String accountNumber
) {
}
