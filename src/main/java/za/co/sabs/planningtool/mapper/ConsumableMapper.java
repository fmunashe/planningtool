package za.co.sabs.planningtool.mapper;

import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.dto.ConsumableDto;
import za.co.sabs.planningtool.entity.Consumable;
import za.co.sabs.planningtool.entity.ContactPerson;

import java.util.function.Function;

@Service
public class ConsumableMapper implements Function<Consumable, ConsumableDto> {
    @Override
    public ConsumableDto apply(Consumable consumable) {
        return new ConsumableDto(
                consumable.getId(),
                consumable.getConsumableNumber(),
                consumable.getName(),
                consumable.getType(),
                consumable.getQuantityOnHand(),
                consumable.getReplenLevel(),
                consumable.getLocation(),
                consumable.getDescription(),
                consumable.getCost(),
                consumable.getCreatedBy(),
                consumable.getActive(),
                getContactPerson(consumable.getContactPersons().get(0)),
                consumable.getCreatedAt(),
                consumable.getUpdatedAt()
        );
    }

    private ContactPerson getContactPerson(ContactPerson supplier) {
        ContactPerson person = new ContactPerson();
        person.setId(supplier.getId());
        person.setName(supplier.getName());
        person.setEmail(supplier.getEmail());
        person.setTelephone(supplier.getTelephone());
        person.setAddress(supplier.getAddress());
        person.setActive(supplier.getActive());
        person.setContractStartDate(supplier.getContractStartDate());
        person.setContractEndDate(supplier.getContractEndDate());
        return person;
    }
}
