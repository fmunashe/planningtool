package za.co.sabs.planningtool.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.co.sabs.planningtool.entity.Role;
import za.co.sabs.planningtool.repository.RoleRepository;

import java.util.List;

@Component
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void ensureRoles() {
        List<String> roles = List.of("ROLE_ADMIN", "ROLE_TEAM_LEAD", "ROLE_TEST_OFFICER");
        for (String r : roles) {
            roleRepository.findByName(r).orElseGet(() -> {
                log.info("=== Creating role === {}", r);
                return roleRepository.save(new Role(r));
            });
        }
    }
}
