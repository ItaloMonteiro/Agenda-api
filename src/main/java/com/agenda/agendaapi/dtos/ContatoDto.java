package com.agenda.agendaapi.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ContatoDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String telefone;
}
