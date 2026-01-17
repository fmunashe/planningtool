package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.UserDto;
import za.co.sabs.planningtool.entity.User;

import java.util.function.Function;

@Service
public class UserMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.isEnabled(), user.isAccountNonExpired(), user.isAccountNonLocked(), user.isCredentialsNonExpired(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
