package com.agenda.agendaapi.controllers;

import com.agenda.agendaapi.dtos.ContactDto;
import com.agenda.agendaapi.models.ContactModel;
import com.agenda.agendaapi.services.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/")
@Api(value = "API REST AGENDA")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("/")
    @ApiOperation(value = "Adiciona um contato na agenda")
    public ResponseEntity<ContactModel> newContact(@RequestBody @Valid ContactDto contactDto){
        return contactService.save(contactDto);
    }

    @GetMapping("/")
    @ApiOperation(value = "Retorna uma lista de contatos")
    public ResponseEntity<Page<ContactModel>> getAllContacts(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return contactService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna um contato pelo id")
    public ResponseEntity<Object> getOneContact(@PathVariable(value = "id") Long id){
        return contactService.findById(id);
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Deleta um contato passando o contato")
    public ResponseEntity<Object> deleteContact(@RequestBody ContactModel contactModel){
        return contactService.delete(contactModel);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um contato pelo id")
    public ResponseEntity<Object> deleteContactById(@PathVariable(value = "id") Long id){
        return contactService.deleteById(id);
    }


    @PutMapping("/")
    @ApiOperation(value = "Atualiza um contato")
    public ResponseEntity<Object> updateContact( @RequestBody @Valid ContactModel contactModel){
        return contactService.update(contactModel);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um contato pelo id")
    public ResponseEntity<Object> updateContactById(@PathVariable(value = "id") Long id, @RequestBody @Valid ContactDto contactDto){
        return contactService.updateById(id, contactDto);
    }
}
