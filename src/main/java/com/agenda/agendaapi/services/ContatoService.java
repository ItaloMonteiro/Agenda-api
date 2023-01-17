package com.agenda.agendaapi.services;

import com.agenda.agendaapi.models.ContatoModel;
import com.agenda.agendaapi.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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

    public Optional<ContatoModel> findById(Long id){
        return contatoRepository.findById(id);
    }

    @Transactional
    public void delete(ContatoModel contatoModel) {
        contatoRepository.delete(contatoModel);
    }

}
