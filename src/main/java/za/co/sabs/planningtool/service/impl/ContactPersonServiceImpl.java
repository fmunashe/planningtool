package za.co.sabs.planningtool.service.impl;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.ContactPerson;
import za.co.sabs.planningtool.repository.ContactPersonRepository;
import za.co.sabs.planningtool.service.ContactPersonService;
import za.co.sabs.planningtool.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class ContactPersonServiceImpl implements ContactPersonService {
    private final ContactPersonRepository contactPersonRepository;

    public ContactPersonServiceImpl(ContactPersonRepository contactPersonRepository) {
        this.contactPersonRepository = contactPersonRepository;
    }

    @Override
    public List<ContactPerson> findAll() {
        return contactPersonRepository.findAll();
    }

    @Override
    public Page<@NonNull ContactPerson> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return contactPersonRepository.findAll(pageable);
    }

    @Override
    public Optional<ContactPerson> findById(Long id) {
        return contactPersonRepository.findById(id);
    }

    @Override
    public ContactPerson save(ContactPerson contactPerson) {
        return contactPersonRepository.save(contactPerson);
    }

    @Override
    public void deleteById(Long id) {
        contactPersonRepository.deleteById(id);
    }
}
