package model;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import java.util.concurrent.atomic.AtomicInteger;


public class Usuario {
    private static final AtomicInteger contadorIds = new AtomicInteger(0);
    private GerenciadorIDsReutilizaveis gerenciadorIDsReutilizaveis = new GerenciadorIDsReutilizaveis();
    private Integer id;
    private String name;
    private String email;

    public Usuario(String name, String email){
        if(gerenciadorIDsReutilizaveis.verificarIds() == null){
            this.id = contadorIds.getAndIncrement();
        }else{
            this.id = gerenciadorIDsReutilizaveis.verificarIds();
            for(Map.Entry<Integer, Usuario> pair: gerenciadorIDsReutilizaveis.pegarLista().entrySet()){
                if(pair.getKey() == gerenciadorIDsReutilizaveis.verificarIds()){
                    pair.setValue(this);
                }
            }
        }
        this.name = name;
        this.email = email;
        gerenciadorIDsReutilizaveis.addUser(this);
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(gerenciadorIDsReutilizaveis, usuario.gerenciadorIDsReutilizaveis) && Objects.equals(id, usuario.id) && Objects.equals(name, usuario.name) && Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gerenciadorIDsReutilizaveis, id, name, email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
