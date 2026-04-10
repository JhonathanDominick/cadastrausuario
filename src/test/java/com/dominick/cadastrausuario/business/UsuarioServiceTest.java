package com.dominick.cadastrausuario.business;

import com.dominick.cadastrausuario.business.converter.UsuarioConverter;
import com.dominick.cadastrausuario.business.dto.UsuarioDTO;
import com.dominick.cadastrausuario.infrastructure.entity.Usuario;
import com.dominick.cadastrausuario.infrastructure.exceptions.ConflictException;
import com.dominick.cadastrausuario.infrastructure.exceptions.ResourceNotFoundException;
import com.dominick.cadastrausuario.infrastructure.repository.EnderecoRepository;
import com.dominick.cadastrausuario.infrastructure.repository.TelefoneRepository;
import com.dominick.cadastrausuario.infrastructure.repository.UsuarioRepository;
import com.dominick.cadastrausuario.infrastructure.security.JwtUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioConverter usuarioConverter;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private TelefoneRepository telefoneRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveSalvarUsuarioComSucesso() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setEmail("teste@email.com");
        dto.setSenha("123");

        Usuario usuario = new Usuario();

        when(usuarioRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode("123")).thenReturn("senhaCriptografada");
        when(usuarioConverter.paraUsuario(dto)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioConverter.paraUsuarioDTO(usuario)).thenReturn(dto);

        UsuarioDTO resultado = usuarioService.salvaUsuario(dto);

        assertNotNull(resultado);

        verify(usuarioRepository).existsByEmail(dto.getEmail());
        verify(passwordEncoder).encode("123");
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setEmail("teste@email.com");

        when(usuarioRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        assertThrows(ConflictException.class, () -> usuarioService.salvaUsuario(dto));

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deveBuscarUsuarioPorEmailComSucesso() {
        String email = "teste@email.com";
        Usuario usuario = new Usuario();
        UsuarioDTO dto = new UsuarioDTO();

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));
        when(usuarioConverter.paraUsuarioDTO(usuario)).thenReturn(dto);

        UsuarioDTO resultado = usuarioService.buscarUsuarioPorEmail(email);

        assertNotNull(resultado);

        verify(usuarioRepository).findByEmail(email);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        String email = "teste@email.com";

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> usuarioService.buscarUsuarioPorEmail(email));
    }

    @Test
    void deveAtualizarDadosUsuarioComSucesso() {
        String token = "Bearer token123";
        String email = "teste@email.com";

        UsuarioDTO dto = new UsuarioDTO();
        dto.setSenha("123");

        Usuario usuario = new Usuario();

        when(jwtUtil.extrairEmailToken("token123")).thenReturn(email);
        when(passwordEncoder.encode("123")).thenReturn("senhaCriptografada");
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));
        when(usuarioConverter.updateUsuario(dto, usuario)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioConverter.paraUsuarioDTO(usuario)).thenReturn(dto);

        UsuarioDTO resultado = usuarioService.atualizaDadosUsuario(token, dto);

        assertNotNull(resultado);

        verify(jwtUtil).extrairEmailToken("token123");
        verify(passwordEncoder).encode("123");
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExisteAoAtualizar() {
        String token = "Bearer token123";

        when(jwtUtil.extrairEmailToken("token123")).thenReturn("email@invalido.com");
        when(usuarioRepository.findByEmail(any())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> usuarioService.atualizaDadosUsuario(token, new UsuarioDTO()));
    }

    @Test
    void naoDeveCriptografarSenhaQuandoForNull() {
        String token = "Bearer token123";
        String email = "teste@email.com";

        UsuarioDTO dto = new UsuarioDTO();
        dto.setSenha(null);

        Usuario usuario = new Usuario();

        when(jwtUtil.extrairEmailToken("token123")).thenReturn(email);
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));
        when(usuarioConverter.updateUsuario(dto, usuario)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioConverter.paraUsuarioDTO(usuario)).thenReturn(dto);

        usuarioService.atualizaDadosUsuario(token, dto);

        verify(passwordEncoder, never()).encode(any());
    }
}