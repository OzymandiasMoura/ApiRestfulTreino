package cepein.atividade2.domain.ids;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaId implements Serializable
{
    private int idProduto;
    private int idPedido;
}
