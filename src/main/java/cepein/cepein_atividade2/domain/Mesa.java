package cepein.cepein_atividade2.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
public class Mesa
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @Column(nullable = true)
    private Atendente atendente;

    private Boolean aberta;

    public Mesa()
    {
        this.atendente = null;
        this.aberta = false;
    }

    public void abrirMesa(Atendente atendente)
    {
        this.atendente = atendente;
        this.aberta = true;
    }
}
