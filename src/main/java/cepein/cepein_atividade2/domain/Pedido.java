package cepein.cepein_atividade2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.cglib.core.Local;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    @Column(nullable = false)
    private Atendente atendente;

    @Column(nullable = false)
    private Boolean aberta;

    private LocalDate dataFechamentoPedido;

    public Pedido(LocalDate dataPedido, Atendente atendente)
    {
        this.dataAberturaPedido = dataPedido;
        this.atendente = atendente;
        this.aberta = true;
        this.dataFechamentoPedido = null;
    }

    public void fecharPedido()
    {
        setAberta(false);
        this.dataFechamentoPedido = LocalDate.now();
    }
}
