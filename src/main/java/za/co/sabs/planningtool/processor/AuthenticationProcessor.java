package za.co.sabs.planningtool.processor;

import za.co.sabs.planningtool.utils.messages.request.AuthRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

public interface AuthenticationProcessor {
    ApiResponse<String> authenticate(AuthRequest authRequest);
}
