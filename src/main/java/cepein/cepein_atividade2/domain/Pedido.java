package cepein.cepein_atividade2.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Pedido
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @NonNull
    private Date dataPedido;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Mesa mesa;
}
