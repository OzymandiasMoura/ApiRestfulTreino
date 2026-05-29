package cepein.atividade2.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Atendente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAtendente;

    @NonNull
    @NotBlank
    private String nome;

    @NonNull
    @NotBlank
    @Column(unique = true)
    private String cpf;

    @NonNull
    private Boolean ativo;

    @Transient
    @OneToMany(mappedBy = "atendente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public Atendente(String nome, String cpf)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.ativo = true;
    }

    public List<Pedido> addPedido(Pedido pedido)
    {
        if(!(pedidos instanceof ArrayList)){
            pedidos = new ArrayList<>(pedidos);
        }
        this.pedidos.add(pedido);
        return pedidos;
    }

    public List<Pedido> removePedido(Pedido pedido)
    {
        this.pedidos.remove(pedido);
        return pedidos;
    }
}
