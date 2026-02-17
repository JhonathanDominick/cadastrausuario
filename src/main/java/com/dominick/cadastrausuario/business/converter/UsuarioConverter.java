package com.dominick.cadastrausuario.business.converter;

import com.dominick.cadastrausuario.business.dto.EnderecoDTO;
import com.dominick.cadastrausuario.business.dto.TelefoneDTO;
import com.dominick.cadastrausuario.business.dto.UsuarioDTO;
import com.dominick.cadastrausuario.infrastructure.entity.Endereco;
import com.dominick.cadastrausuario.infrastructure.entity.Telefone;
import com.dominick.cadastrausuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    // ============================================
    // DTO → ENTITY
    // ============================================

    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecoDTOS()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefoneDTOS()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS) {
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDTO enderecoDTO : enderecoDTOS) {
            enderecos.add(paraEndereco(enderecoDTO));
        }
        return enderecos;
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS) {
        return telefoneDTOS.stream()
                .map(this::paraTelefone)
                .toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .telefone(telefoneDTO.getTelefone())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    // ============================================
    // ENTITY → DTO (Conversão Contrária)
    // ============================================

    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecoDTOS(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefoneDTOS(paraListaTelefoneDTO(usuario.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecos) {
        List<EnderecoDTO> enderecoDTOS = new ArrayList<>();
        for (Endereco endereco : enderecos) {
            enderecoDTOS.add(paraEnderecoDTO(endereco));
        }
        return enderecoDTOS;
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefones) {
        return telefones.stream()
                .map(this::paraTelefoneDTO)
                .toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .telefone(telefone.getId())
                .ddd(telefone.getDdd())
                .build();
    }
}