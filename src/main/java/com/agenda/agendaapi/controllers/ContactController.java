package com.agenda.agendaapi.controllers;

import com.agenda.agendaapi.dtos.ContactDto;
import com.agenda.agendaapi.models.ContactModel;
import com.agenda.agendaapi.services.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
        ContactModel contactModel = new ContactModel();
        BeanUtils.copyProperties(contactDto, contactModel);
        contactService.save(contactModel);
        return new ResponseEntity<>(contactModel, HttpStatus.CREATED);
    }

    @GetMapping("/")
    @ApiOperation(value = "Retorna uma lista de contatos")
    public ResponseEntity<Page<ContactModel>> getAllContacts(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna um contato pelo id")
    public ResponseEntity<Object> getOneContact(@PathVariable(value = "id") Long id){
        Optional<ContactModel> contactModelOptional = contactService.findById(id);
        if (!contactModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado na agenda.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contactModelOptional.get());
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Deleta um contato passando o contato")
    public ResponseEntity<Object> deleteContact(@RequestBody ContactModel contactModel){
        contactService.delete(contactModel);
        return ResponseEntity.status(HttpStatus.OK).body("Contato deletado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um contato pelo id")
    public ResponseEntity<Object> deleteContactById(@PathVariable(value = "id") Long id){
        Optional<ContactModel> contactModelOptional = contactService.findById(id);
        if (!contactModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado na agenda.");
        }
        contactService.delete(contactModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Contato deletado com sucesso!");
    }


    @PutMapping("/")
    @ApiOperation(value = "Atualiza um contato")
    public ResponseEntity<Object> updateContact( @RequestBody @Valid ContactModel contactModel){
        var novoContatoModel = new ContactModel();
        BeanUtils.copyProperties(contactModel, novoContatoModel);
        novoContatoModel.setId((contactModel.getId()));
        contactService.save(novoContatoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Contato atualizado com sucesso na agenda!");
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um contato pelo id")
    public ResponseEntity<Object> updateContactById(@PathVariable(value = "id") Long id, @RequestBody @Valid ContactDto contactDto){ //precisamos passar novamente o @RequestBody e @Valid pois é como se criasse outro no lugar do existente
        Optional<ContactModel> contactModelOptional = contactService.findById(id);
        if (!contactModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        var contatoModel = new ContactModel();
        BeanUtils.copyProperties(contactDto, contatoModel);
        contatoModel.setId(contactModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(contactService.save(contatoModel));
    }
}
