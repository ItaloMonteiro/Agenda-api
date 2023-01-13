package com.agenda.agendaapi.services;

import com.agenda.agendaapi.models.ContatoModel;
import com.agenda.agendaapi.repositories.ContatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ContatoService {
    @Autowired
    ContatoRepository contatoRepository;

    @Transactional
    public ContatoModel save(ContatoModel contatoModel){
        return contatoRepository.save(contatoModel);
    }

    public Page<ContatoModel> findAll(Pageable pageable){
        return contatoRepository.findAll(pageable);
    }

    public Optional<ContatoModel> findById(UUID id){
        return contatoRepository.findById(id);
    }

    @Transactional
    public void delete(ContatoModel contatoModel) {
        contatoRepository.delete(contatoModel);
    }

}
