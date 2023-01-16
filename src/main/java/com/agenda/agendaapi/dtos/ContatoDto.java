package com.agenda.agendaapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ContatoDto {

    @NotBlank
    private String nome;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String telefone;
}
