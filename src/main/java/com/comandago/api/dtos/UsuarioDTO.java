package com.comandago.api.dtos;

import com.comandago.api.enums.AtribuicaoUsuarioEnum;
import com.comandago.api.models.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String login;
    private AtribuicaoUsuarioEnum atribuicao;
    private boolean estaAtivo;

    public UsuarioDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.atribuicao = usuario.getAtribuicao();
        this.estaAtivo = usuario.isEnabled();
    }

    public void atribuirValoresUsuario(Usuario usuario){
        usuario.setNome(this.nome);
        usuario.setLogin(this.login);
        //usuario.setSenha(this.senha);
        usuario.setAtribuicao(this.atribuicao);
        usuario.setEstaAtivo(this.estaAtivo);
    }
}
