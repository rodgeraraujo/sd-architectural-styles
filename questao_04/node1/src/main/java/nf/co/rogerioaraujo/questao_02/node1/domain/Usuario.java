package nf.co.rogerioaraujo.questao_02.node1.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Usuario implements Serializable {

    private int codigo;
    private String nome;

}
