package com.agenda.agendaapi.services;

import com.agenda.agendaapi.models.ContactModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Test
    public void buscarPorIdTeste(){
        ContactModel contactModel = contactService.findById(1L).get();
        assertEquals("italo", contactModel.getName());
        assertEquals("italo@gmail.com", contactModel.getEmail());
        assertEquals("81981926641", contactModel.getPhone());

    }
    @Test
    public void salvarContatoTeste(){
        ContactModel contactModel = new ContactModel();
        contactModel.setName("oziel");
        contactModel.setEmail("oziel@gmail.com");
        contactModel.setPhone("81954682456");
        ContactModel salvarContato = contactService.save(contactModel);
        assertNotNull(salvarContato);

    }
}