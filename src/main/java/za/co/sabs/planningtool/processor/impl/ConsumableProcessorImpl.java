package za.co.sabs.planningtool.processor.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.ConsumableDto;
import za.co.sabs.planningtool.entity.Consumable;
import za.co.sabs.planningtool.entity.ContactPerson;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.mapper.ConsumableMapper;
import za.co.sabs.planningtool.processor.ConsumableProcessor;
import za.co.sabs.planningtool.service.ConsumableService;
import za.co.sabs.planningtool.service.ContactPersonService;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.ConsumableRequest;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class ConsumableProcessorImpl implements ConsumableProcessor {
    private final ConsumableService consumableService;
    private final ConsumableMapper consumableMapper;
    private final ContactPersonService contactPersonService;

    public ConsumableProcessorImpl(ConsumableService consumableService, ConsumableMapper consumableMapper, ContactPersonService contactPersonService) {
        this.consumableService = consumableService;
        this.consumableMapper = consumableMapper;
        this.contactPersonService = contactPersonService;
    }

    @Override
    public ApiResponse<ConsumableDto> findAll(Integer pageNo, Integer pageSize) {
        Page<@NonNull Consumable> consumablePage = consumableService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(consumablePage, consumableMapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<ConsumableDto> findById(Long id) {
        Optional<Consumable> optionalConsumable = consumableService.findById(id);
        if (optionalConsumable.isEmpty()) {
            throw new RecordNotFoundException("Consumable not found");
        }
        return HelperResponse.buildApiResponse(null, consumableMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, consumableMapper.apply(optionalConsumable.get()));
    }

    @Override
    public ApiResponse<ConsumableDto> save(ConsumableRequest consumableRequest) {
        Optional<ContactPerson> optionalContactPerson = contactPersonService.findById(consumableRequest.getSupplierId());
        if (optionalContactPerson.isEmpty()) {
            throw new RecordNotFoundException("Contact person not found");
        }
        ContactPerson contactPerson = optionalContactPerson.get();
        Consumable consumable = new Consumable();
        consumable.setName(consumableRequest.getName());
        consumable.setConsumableNumber(consumableRequest.getConsumableNumber());
        consumable.setQuantityOnHand(consumableRequest.getQuantityOnHand());
        consumable.setReplenLevel(consumableRequest.getReplenLevel());
        consumable.setSupplier(contactPerson);
        consumable.setType(consumableRequest.getType());
        consumable.setLocation(consumableRequest.getLocation());
        consumable.setDescription(consumableRequest.getDescription());
        consumable.setCost(consumableRequest.getCost());
        consumable.setActive(consumableRequest.getActive());
        consumable.setCreatedBy(consumableRequest.getCreatedBy());
        consumable = consumableService.save(consumable);
        return HelperResponse.buildApiResponse(null, consumableMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, consumableMapper.apply(consumable));
    }

    @Override
    public ApiResponse<ConsumableDto> update(Long id, ConsumableDto consumableDto) {
        Optional<Consumable> optionalConsumable = consumableService.findById(id);
        if (optionalConsumable.isEmpty()) {
            throw new RecordNotFoundException("Consumable not found");
        }

        Consumable consumable = optionalConsumable.get();
        consumable.setName(consumableDto.name());
        consumable.setConsumableNumber(consumableDto.consumableNumber());
        consumable.setQuantityOnHand(consumableDto.quantityOnHand());
        consumable.setReplenLevel(consumableDto.replenLevel());
        consumable.setSupplier(consumableDto.supplier());
        consumable.setType(consumableDto.type());
        consumable.setLocation(consumableDto.location());
        consumable.setDescription(consumableDto.description());
        consumable.setCost(consumableDto.cost());
        consumable.setActive(consumableDto.active());
        consumable.setCreatedBy(consumableDto.createdBy());
        consumable = consumableService.save(consumable);
        return HelperResponse.buildApiResponse(null, consumableMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, consumableMapper.apply(consumable));
    }

    @Override
    public ApiResponse<ConsumableDto> deleteById(Long id) {
        Optional<Consumable> optionalConsumable = consumableService.findById(id);
        if (optionalConsumable.isEmpty()) {
            throw new RecordNotFoundException("Consumable not found");
        }
        consumableService.deleteById(id);
        return HelperResponse.buildApiResponse(null, consumableMapper, false, 200, true, AppConstants.SUCCESS_MESSAGE, consumableMapper.apply(optionalConsumable.get()));
    }
}
