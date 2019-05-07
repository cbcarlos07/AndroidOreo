package br.com.brito.firebase;

public class Pessoa {
     String nome;
     String sobrenome;
     String cidade;
     String cargo;

     Pessoa(){
         //é necessário para firebase ler os dados
     }

    public Pessoa(String nome, String sobrenome, String cidade, String cargo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cidade = cidade;
        this.cargo = cargo;
    }
}
