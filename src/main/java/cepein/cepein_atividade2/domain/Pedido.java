package cepein.cepein_atividade2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @NonNull
    @Column(nullable = false)
    private LocalDate dataAberturaPedido;

    @NonNull
    @ManyToOne
    private Atendente atendente;

    @Column(nullable = false)
    private Boolean aberta;

    private LocalDate dataFechamentoPedido;

    public Pedido(LocalDate dataPedido, Atendente atendente)
    {
        this.dataAberturaPedido = dataPedido;
        this.aberta = true;
        this.dataFechamentoPedido = null;
        setAtendente(atendente);
    }
//
//    public void setAtendente(Atendente atendente)
//    {
//        this.atendente = atendente;
//        atendente.addPedido(this);
//    }
}
