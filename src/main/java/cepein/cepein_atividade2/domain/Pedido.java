//package cepein.cepein_atividade2.domain;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//
//import java.util.Date;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Pedido
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer idPedido;
//
//    @NonNull
//    private Date dataPedido;
//
//    @NonNull
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Mesa mesa;
//
//    @NonNull
//    @NotNull
//    @ManyToOne
//    private Atendente atendente;
//
//    @NotNull
//    private Boolean aberta;
//
//    public Pedido(Date dataPedido, Mesa mesa, Atendente atendente)
//    {
//        this.dataPedido = dataPedido;
//        this.mesa = mesa;
//        this.atendente = atendente;
//        this.mesa.setAberta(true);
//    }
//
//    public void fecharPedido()
//    {
//        setAberta(false);
//        this.mesa.setAberta(false);
//    }
//}
