package com.agenda.agendaapi.controllers;

import com.agenda.agendaapi.dtos.ContatoDto;
import com.agenda.agendaapi.models.ContatoModel;
import com.agenda.agendaapi.services.ContatoService;
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
@RequestMapping("/contatos")
@Api(value = "API REST AGENDA")
public class ContatoController {

    @Autowired
    ContatoService contatoService;

    @PostMapping("/")
    @ApiOperation(value = "Adiciona um contato na agenda")
    public ResponseEntity<ContatoModel> novaAgenda(@RequestBody @Valid ContatoDto contatoDto){
        ContatoModel contatoModel = new ContatoModel();
        BeanUtils.copyProperties(contatoDto, contatoModel);
        contatoService.save(contatoModel);
        return new ResponseEntity<>(contatoModel, HttpStatus.CREATED);
    }

    @GetMapping("/")
    @ApiOperation(value = "Retorna uma lista de contatos")
    public ResponseEntity<Page<ContatoModel>> verAgendas(@PageableDefault(page = 0, size = 10, sort = "agendaId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna um contato pelo id")
    public ResponseEntity<Object> verUmaAgenda(@PathVariable(value = "id") Long id){
        Optional<ContatoModel> contatoModelOptional = contatoService.findById(id);
        if (!contatoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato na agenda não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contatoModelOptional.get());
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Deleta um contato passando o contato")
    public ResponseEntity<Object> deletarAgenda(@RequestBody ContatoModel contatoModel){
        contatoService.delete(contatoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Contato deletado com sucesso!");
    }

    @PutMapping("/")
    @ApiOperation(value = "Atualiza um contato")
    public ResponseEntity<Object> atualizarAgenda( @RequestBody @Valid ContatoModel contatoModel){
        var novoContatoModel = new ContatoModel();
        BeanUtils.copyProperties(contatoModel, novoContatoModel);
        novoContatoModel.setAgendaId((contatoModel.getAgendaId()));
        contatoService.save(novoContatoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Contato atualizado com sucesso na agenda!");
    }
}
