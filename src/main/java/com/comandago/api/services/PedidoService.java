package com.comandago.api.services;

import com.comandago.api.dtos.ConsultarPedidosComandaDTO;
import com.comandago.api.dtos.EstadoPedidoDTO;
import com.comandago.api.dtos.ItemPedidoDTO;
import com.comandago.api.dtos.PedidoDTO;
import com.comandago.api.enums.EstadoPedidoEnum;
import com.comandago.api.models.Cardapio;
import com.comandago.api.models.Comanda;
import com.comandago.api.models.Mesa;
import com.comandago.api.models.Pedido;
import com.comandago.api.models.PedidosCardapio;
import com.comandago.api.models.Usuario;
import com.comandago.api.repositories.CardapioRepository;
import com.comandago.api.repositories.ComandaRepository;
import com.comandago.api.repositories.MesaRepository;
import com.comandago.api.repositories.PedidoRepository;
import com.comandago.api.repositories.PedidosCardapioRepository;
import com.comandago.api.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private PedidosCardapioRepository pedidosCardapioRepository;


    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido obterPedidoPorId(Long id) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        return optionalPedido.orElse(null);
    }

    public Long criarPedido(Long idComanda, PedidoDTO pedidoDTO) {
        Optional<Comanda> comandaOptional = comandaRepository.findById(idComanda);
        Optional<Usuario> usuarioOpitional = usuarioRepository.findById(pedidoDTO.getIdUsuario());
        Optional<Mesa> mesaOptional = mesaRepository.findById(pedidoDTO.getIdMesa());
        if(comandaOptional.isPresent() && usuarioOpitional.isPresent() && mesaOptional.isPresent()){
            Comanda comanda = comandaOptional.get();
            var pedido = new Pedido();
            pedido.setUsuario(usuarioOpitional.get().getNome());
            pedido.setMesa(mesaOptional.get());
            pedidoRepository.save(pedido);
            comanda.addPedido(pedido);
            comandaRepository.save(comanda);
            return pedido.getId();
        }
        
        return null;
    }

    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            Pedido pedidoExistente = optionalPedido.get();
            pedidoExistente.setDataHora(pedidoAtualizado.getDataHora());
            pedidoExistente.setUsuario(pedidoAtualizado.getUsuario());
            pedidoExistente.setMesa(pedidoAtualizado.getMesa());
            pedidoExistente.setEstado(pedidoAtualizado.getEstado());
            pedidoExistente.setItens(pedidoAtualizado.getItens());
            return pedidoRepository.save(pedidoAtualizado);
        } else {
            return null;
        }
    }

    public void excluirPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public boolean adicionarItens(Long idPedido, List<ItemPedidoDTO> itens){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(idPedido);
        Pedido pedido = pedidoOptional.get();
        List<PedidosCardapio> itensPedido = new ArrayList<>();
        if(pedido != null){
            for(ItemPedidoDTO item : itens){
                Optional<Cardapio> cardapio = cardapioRepository.findById(item.cardapioId());
                if(cardapio.isPresent()){
                    var itemPedido = new PedidosCardapio();
                    itemPedido.setPedido(pedido);
                    itemPedido.setCardapio(cardapio.get());
                    itemPedido.setQuantidade(item.quantidade());
                    itemPedido.setObservacoes(item.observacoes());
                    pedidosCardapioRepository.save(itemPedido);
                    itensPedido.add(itemPedido);
                }
            }
            pedido.addItens(itensPedido);
            pedidoRepository.save(pedido);
            return true;
        }

        return false;
    }

    public void adicionarItem(Pedido pedido, ItemPedidoDTO item){
        Optional<Cardapio> cardapioOptional = cardapioRepository.findById(item.cardapioId());
        if(cardapioOptional.isPresent()){
            var pedidoCardapio = new PedidosCardapio();
            pedidoCardapio.setPedido(pedido);
            pedidoCardapio.setCardapio(cardapioOptional.get());
            pedidoCardapio.setQuantidade(item.quantidade());
            pedidoCardapio.setObservacoes(item.observacoes());
            pedido.addItem(pedidoCardapio);
            pedidoRepository.save(pedido);
        }
    }

    public List<ConsultarPedidosComandaDTO> listarPedidosComanda(Long idComanda) {
        Optional<Comanda> comandaOptional = comandaRepository.findById(idComanda);
        if(comandaOptional.isPresent()){
            List<ConsultarPedidosComandaDTO> retorno = new ArrayList<>();

            for(Pedido pedido : comandaOptional.get().getPedidos()){
                var dto = new ConsultarPedidosComandaDTO(pedido);
                retorno.add(dto);
            }

            return retorno;
        }

        return null;
    }

    public Long alterarEstadoPedido(Long id, EstadoPedidoDTO estado) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if(pedidoOptional.isPresent()){
            Pedido pedido = pedidoOptional.get();
            if(!pedido.getEstado().equals(estado.estadoPedido())){
                if(estado.estadoPedido().equals(EstadoPedidoEnum.CANCELADO) && pedido.getItens() != null){
                    Double valor = pedido.getValor();

                    for(PedidosCardapio p : pedido.getItens()){
                        valor -= p.getQuantidade()*p.getCardapio().getValor();
                    }
                    
                    pedido.setValor(valor);
                } 
                else if (pedido.getEstado().equals(EstadoPedidoEnum.CANCELADO) && pedido.getItens() != null){
                    Double valor = pedido.getValor();

                    for(PedidosCardapio p : pedido.getItens()){
                        valor += p.getQuantidade()*p.getCardapio().getValor();
                    }

                    pedido.setValor(valor);
                }
                pedido.setEstado(estado.estadoPedido());
                pedidoRepository.save(pedido);
                return pedido.getId();
            }
            return (long) 0;
        }

        return null;
    }

    public List<Pedido> buscarPedidosPorEstado(EstadoPedidoDTO estadoPedido) {
        List<Pedido> pedidos = pedidoRepository.findByEstado(estadoPedido.estadoPedido());
        return pedidos;
    }
}
