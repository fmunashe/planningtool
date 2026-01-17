package za.co.sabs.planningtool.processor;

import za.co.sabs.planningtool.common.BaseProcessor;
import za.co.sabs.planningtool.dto.UserDto;
import za.co.sabs.planningtool.utils.messages.request.UserRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

public interface UserProcessor extends BaseProcessor<UserDto, UserRequest> {
    ApiResponse<UserDto> assignRoles(UserRequest userRequest);
}
