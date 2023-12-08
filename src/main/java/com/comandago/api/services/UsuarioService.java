package com.comandago.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.comandago.api.dtos.AtualizarUsuarioDTO;
import com.comandago.api.dtos.UsuarioDTO;
import com.comandago.api.models.Usuario;
import com.comandago.api.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    final UsuarioRepository usuarioRepository;

    UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> users =  usuarioRepository.findAll();
        List<UsuarioDTO> usuarios = new ArrayList<>();
        for(Usuario u : users){
            UsuarioDTO dto = new UsuarioDTO(u);
            usuarios.add(dto);
        }
        return usuarios;
    }

    public UsuarioDTO obterUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        UsuarioDTO dto;
        if(usuarioOptional.isPresent()){
            dto = new UsuarioDTO(usuarioOptional.get());
            return dto;
        } 
        else return null;
        //return usuarioOptional.orElse(null);
    }

    public Usuario criarUsuario(Usuario usuario) {
        if(usuario != null){
            String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.getSenha());
            usuario.setSenha(encryptedPassword);
            return usuarioRepository.save(usuario);
        }
        return null;
    }


    public boolean atualizarUsuario(Long id, AtualizarUsuarioDTO usuarioAtualizado){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        System.out.println(usuarioAtualizado.toString());
        if(usuarioOptional.isPresent()){
            boolean atualizado = false;
            Usuario usuario = usuarioOptional.get();

            if(!usuario.getNome().equals(usuarioAtualizado.getNome())){
                System.out.println("if nome");
                atualizado = true;
                usuario.setNome(usuarioAtualizado.getNome());
            }

            if(!usuario.getLogin().equals(usuarioAtualizado.getLogin())){
                System.out.println("if login");
                atualizado = true;
                usuario.setLogin(usuarioAtualizado.getLogin());
            }

            if(usuarioAtualizado.getSenha() != null){
                String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioAtualizado.getSenha());
                if(!usuario.getSenha().equals(encryptedPassword)){
                    System.out.println("if senha");
                    System.out.println("Usuario: ".concat(usuario.getNome()).concat(", senha: ").concat(usuario.getSenha()));
                    System.out.println("Usuario: ".concat(usuarioAtualizado.getNome()).concat(", senha: ").concat(encryptedPassword));
                    atualizado = true;
                    usuario.setSenha(encryptedPassword);
                }
            }

            if(!usuario.getAtribuicao().equals(usuarioAtualizado.getAtribuicao())){
                System.out.println("if atribuição");
                atualizado = true;
                usuario.setAtribuicao(usuarioAtualizado.getAtribuicao());
            }

            if(usuario.isEstaAtivo() != usuarioAtualizado.isEstaAtivo()){
                System.out.println("if está ativo");
                atualizado = true;
                usuario.setEstaAtivo(usuarioAtualizado.isEstaAtivo());
            }

            if(atualizado)
                usuarioRepository.save(usuario);

            return true;
        }
        return false;
    }

    public boolean excluirUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
