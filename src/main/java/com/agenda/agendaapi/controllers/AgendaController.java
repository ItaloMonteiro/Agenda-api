package com.agenda.agendaapi.controllers;

import com.agenda.agendaapi.dtos.ContatoDto;
import com.agenda.agendaapi.models.ContatoModel;
import com.agenda.agendaapi.services.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class AgendaController {

    @Autowired
    ContatoService contatoService;

    @PostMapping("/")
    public ResponseEntity<ContatoModel> novaAgenda(@RequestBody @Valid ContatoDto contatoDto){
        ContatoModel contatoModel = new ContatoModel();
        BeanUtils.copyProperties(contatoDto, contatoModel);
        contatoService.save(contatoModel);
        return new ResponseEntity<>(contatoModel, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Page<ContatoModel>> verAgendas(@PageableDefault(page = 0, size = 10, sort = "agendaId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> verUmaAgenda(@PathVariable(value = "id") UUID id){
        Optional<ContatoModel> contatoModelOptional = contatoService.findById(id);
        if (!contatoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agenda não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contatoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAgenda(@PathVariable(value = "id") UUID id){
        Optional<ContatoModel> contatoModelOptional = contatoService.findById(id);
        if (!contatoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agenda não encontrada");
        }
        contatoService.delete(contatoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Agenda deletada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarAgenda(@PathVariable(value = "id") UUID id, @RequestBody @Valid ContatoDto contatoDto){
        Optional<ContatoModel> contatoModelOptional = contatoService.findById(id);
        if (!contatoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agenda não encontrada.");
        }
        var contatoModel = new ContatoModel();
        BeanUtils.copyProperties(contatoDto, contatoModel);
        contatoModel.setAgendaId((contatoModelOptional.get().getAgendaId()));
        contatoService.save(contatoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Agenda atualizada com sucesso!");
    }
}
