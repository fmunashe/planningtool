package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.QualificationDto;
import za.co.sabs.planningtool.entity.Qualification;
import za.co.sabs.planningtool.entity.User;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.QualificationsMapper;
import za.co.sabs.planningtool.mapper.UserMapper;
import za.co.sabs.planningtool.processor.QualificationsProcessor;
import za.co.sabs.planningtool.service.QualificationService;
import za.co.sabs.planningtool.service.UserService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.QualificationRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

@Service
public class QualificationProcessorImpl implements QualificationsProcessor {
    private final QualificationService service;
    private final QualificationsMapper mapper;
    private final UserService userService;
    private final UserMapper userMapper;

    public QualificationProcessorImpl(QualificationService service, QualificationsMapper mapper, UserService userService, UserMapper userMapper) {
        this.service = service;
        this.mapper = mapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public ApiResponse<QualificationDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull Qualification> qualificationPage = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(qualificationPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<QualificationDto> findById(Long id) {
        Qualification qualification = service.findById(id).orElseThrow(() -> new RecordNotFoundException("Qualification not found"));
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(qualification));
    }

    @Override
    public ApiResponse<QualificationDto> save(QualificationRequest qualificationRequest) {
        User user = userService.findById(qualificationRequest.getUserId()).orElseThrow(() -> new RecordNotFoundException("User with these qualifications not found"));
        Qualification qualification = new Qualification();
        qualification.setQualificationNumber(qualificationRequest.getQualificationNumber());
        qualification.setProgram(qualificationRequest.getProgram());
        qualification.setSchool(qualification.getSchool());
        qualification.setLocation(qualificationRequest.getLocation());
        qualification.setDescription(qualificationRequest.getDescription());
        qualification.setGrade(qualificationRequest.getGrade());
        qualification.setStartDate(qualificationRequest.getStartDate());
        qualification.setEndDate(qualificationRequest.getEndDate());
        qualification.setCreatedBy(qualificationRequest.getCreatedBy());
        qualification.setActive(qualificationRequest.getActive());
        qualification.setUser(user);
        qualification = service.save(qualification);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(qualification));
    }

    @Override
    public ApiResponse<QualificationDto> update(Long id, QualificationDto qualificationDto) {
        Qualification qualification = service.findById(id).orElseThrow(() -> new RecordNotFoundException("Qualification not found"));
        qualification.setQualificationNumber(qualificationDto.qualificationNumber());
        qualification.setProgram(qualificationDto.program());
        qualification.setSchool(qualificationDto.school());
        qualification.setLocation(qualificationDto.location());
        qualification.setDescription(qualificationDto.description());
        qualification.setGrade(qualificationDto.grade());
        qualification.setStartDate(qualificationDto.startDate());
        qualification.setEndDate(qualificationDto.endDate());
        qualification.setCreatedBy(qualificationDto.createdBy());
        qualification.setActive(qualificationDto.active());
        qualification.setUser(userMapper.toEntity(qualificationDto.user()));
        qualification = service.save(qualification);
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(qualification));
    }

    @Override
    public ApiResponse<QualificationDto> deleteById(Long id) {
        Qualification qualification = service.findById(id).orElseThrow(() -> new RecordNotFoundException("Qualification not found"));
        return HelperResponse.buildApiResponse(null, mapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(qualification));
    }
}
