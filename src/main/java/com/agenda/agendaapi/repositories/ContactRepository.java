package com.agenda.agendaapi.repositories;

import com.agenda.agendaapi.models.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<ContactModel, Long> {
}
