package cepein.cepein_atividade2.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mesa
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numMesa;

    @JoinColumn(nullable = false)
    @OneToOne
    private Atendente atendente;

    public Mesa (Atendente atendente)
    {
        this.atendente = atendente;
    }

}
