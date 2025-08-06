package cepein.cepein_atividade2.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Atendente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGarcom;

    @NonNull
    @NotBlank
    private String nomeGarcom;

    @NonNull
    @NotBlank
    @Column(unique = true)
    private String cpfGarcom;

}
