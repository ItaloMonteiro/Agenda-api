package com.agenda.agendaapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "TB_AGENDA")
public class ContatoModel implements Serializable {
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID agendaId;
    @Column(nullable = false)
    @NotBlank
    private String nome;
    @Column(nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;
    @Column(nullable = false, unique = true)
    @NotBlank
    private String telefone;
}
