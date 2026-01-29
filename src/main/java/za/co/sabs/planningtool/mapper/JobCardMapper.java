package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.JobCardDto;
import za.co.sabs.planningtool.entity.JobCard;

import java.util.function.Function;

@Service
public class JobCardMapper implements Function<JobCard, JobCardDto> {
    @Override
    public JobCardDto apply(JobCard jobCard) {
        return null;
    }
}
