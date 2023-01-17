package com.agenda.agendaapi.services;

import com.agenda.agendaapi.models.ContactModel;
import com.agenda.agendaapi.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    @Transactional
    public ContactModel save(ContactModel contactModel){
        return contactRepository.save(contactModel);
    }

    public Page<ContactModel> findAll(Pageable pageable){
        return contactRepository.findAll(pageable);
    }

    public Optional<ContactModel> findById(Long id){
        return contactRepository.findById(id);
    }

    @Transactional
    public void delete(ContactModel contactModel) {
        contactRepository.delete(contactModel);
    }

}
