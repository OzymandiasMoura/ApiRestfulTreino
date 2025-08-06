package cepein.cepein_atividade2.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Produto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Produto;

    @Column(length = 100)
    @NonNull
    @NotBlank
    private String nome;

    @NonNull
    @NotBlank
    private Double preco;
}
