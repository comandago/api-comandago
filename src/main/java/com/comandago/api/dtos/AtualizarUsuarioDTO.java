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
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarUsuarioDTO {
    private String nome;
    private String login;
    private String senha;
    private AtribuicaoUsuarioEnum atribuicao;
    private boolean estaAtivo;

    public AtualizarUsuarioDTO(Usuario usuario){
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.atribuicao = usuario.getAtribuicao();
        this.estaAtivo = usuario.isEnabled();
    }
}
