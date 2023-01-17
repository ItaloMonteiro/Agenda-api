package com.agenda.agendaapi.services;

import com.agenda.agendaapi.dtos.ContactDto;
import com.agenda.agendaapi.models.ContactModel;
import com.agenda.agendaapi.repositories.ContactRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    @Transactional
    public ResponseEntity<ContactModel> save(ContactDto contactDto){
        ContactModel contactModel = new ContactModel();
        BeanUtils.copyProperties(contactDto, contactModel);
        contactRepository.save(contactModel);

        return new ResponseEntity<>(contactModel, HttpStatus.CREATED);
    }

    public ResponseEntity<Page<ContactModel>> findAll(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(contactRepository.findAll(pageable));
    }

    public ResponseEntity<Object> findById(Long id){
        Optional<ContactModel> contactModelOptional = contactRepository.findById(id);
        if (!contactModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado na agenda.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contactModelOptional.get());
    }

    @Transactional
    public ResponseEntity<Object> delete(ContactModel contactModel) {
        contactRepository.delete(contactModel);
        return ResponseEntity.status(HttpStatus.OK).body("Contato deletado com sucesso!");
    }

    @Transactional
    public ResponseEntity<Object> deleteById(Long id) {
        Optional<ContactModel> contactModelOptional = contactRepository.findById(id);
        if (!contactModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado na agenda.");
        }
        contactRepository.delete(contactModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Contato deletado com sucesso!");
    }

    public ResponseEntity<Object> update(ContactModel contactModel){
        var newContactModel = new ContactModel();
        BeanUtils.copyProperties(contactModel, newContactModel);
        newContactModel.setId((contactModel.getId()));
        contactRepository.save(newContactModel);
        return ResponseEntity.status(HttpStatus.OK).body("Contato atualizado com sucesso na agenda!");
    }

    public ResponseEntity<Object> updateById(Long id, ContactDto contactDto){
        Optional<ContactModel> contactModelOptional = contactRepository.findById(id);
        if (!contactModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado na agenda.");
        }
        var contactModel = new ContactModel();
        BeanUtils.copyProperties(contactDto, contactModel);
        contactModel.setId(contactModelOptional.get().getId());
        contactRepository.save(contactModel);
        return ResponseEntity.status(HttpStatus.OK).body("Contato atualizado com sucesso na agenda!");
    }

}
