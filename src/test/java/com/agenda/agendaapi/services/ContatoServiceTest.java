package com.agenda.agendaapi.services;

import com.agenda.agendaapi.models.ContatoModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class ContatoServiceTest {

    @Autowired
    private ContatoService contatoService;

    @Test
    public void buscarPorIdTeste(){
        ContatoModel contatoModel = contatoService.findById(1L).get();
        assertEquals("italo", contatoModel.getNome());
    }
}