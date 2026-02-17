package com.dominick.cadastrausuario.business;

import com.dominick.cadastrausuario.business.converter.UsuarioConverter;
import com.dominick.cadastrausuario.business.dto.UsuarioDTO;
import com.dominick.cadastrausuario.infrastructure.entity.Usuario;
import com.dominick.cadastrausuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }
}
