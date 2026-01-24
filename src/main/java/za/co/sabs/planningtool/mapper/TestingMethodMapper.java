package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Component;
import za.co.sabs.planningtool.dto.TestingMethodDto;
import za.co.sabs.planningtool.entity.TestingMethod;

import java.util.function.Function;

@Component
public class TestingMethodMapper implements Function<TestingMethod, TestingMethodDto> {
    @Override
    public TestingMethodDto apply(TestingMethod testingMethod) {
        if (testingMethod == null) {
            return null;
        }
        return new TestingMethodDto(
                testingMethod.getId(),
                testingMethod.getTestMethodName(),
                testingMethod.getTestMethodNo(),
                testingMethod.getPhase(),
                testingMethod.getDescription(),
                testingMethod.getIsoSansCode(),
                testingMethod.getTurnAroundTime(),
                testingMethod.getCreatedBy(),
                testingMethod.getActive(),
                testingMethod.getCreatedAt(),
                testingMethod.getUpdatedAt()
        );
    }
}
