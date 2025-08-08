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

    public Atendente(String nome, String cpf)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.ativo = true;
    }

    public void desativarAtendente()
    {
        setAtivo(false);
    }
}
