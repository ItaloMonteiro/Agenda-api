package com.agenda.agendaapi.repositories;

import com.agenda.agendaapi.models.ContatoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoModel, UUID> {
}
