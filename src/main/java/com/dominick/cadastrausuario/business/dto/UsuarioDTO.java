package com.dominick.cadastrausuario.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
        private String nome;
        private String email;
        private String senha;
        private List<EnderecoDTO> enderecoDTOS;
        private List<TelefoneDTO> telefoneDTOS;
}
