package za.co.sabs.planningtool.utils.messages.response.basic;

import lombok.Builder;
import lombok.Data;
import za.co.sabs.planningtool.utils.enums.DeliveryState;


@Data
@Builder
public class SmsResponseDto {
    private String reference;
    private DeliveryState state;
    private String summary;
}
