package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.QualificationDto;
import za.co.sabs.planningtool.entity.Qualification;

import java.util.function.Function;

@Service
public class QualificationsMapper implements Function<Qualification, QualificationDto> {
    private final UserMapper userMapper;

    public QualificationsMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public QualificationDto apply(Qualification qualification) {
        return new QualificationDto(
                qualification.getId(),
                qualification.getQualificationNumber(),
                qualification.getProgram(),
                qualification.getSchool(),
                qualification.getLocation(),
                qualification.getDescription(),
                qualification.getGrade(),
                qualification.getStartDate(),
                qualification.getEndDate(),
                qualification.getCreatedBy(),
                qualification.getActive(),
                userMapper.apply(qualification.getUser()),
                qualification.getCreatedAt(),
                qualification.getUpdatedAt()
        );
    }
}
