package service;

import model.Evento;
import model.Usuario;

import java.util.*;

public class GerenciadorDeEventos {
    private static final Map<String, Evento> eventosDisponiveis = new HashMap<>();
    private static final Map<Integer, Usuario> usuarioList = new HashMap<>();
    public GerenciadorDeEventos(){}

    //Gestao do User

    public boolean cadastrarUser(Usuario usuario){
        if(usuarioList == null){
            return false;
        }

        if(usuarioList.isEmpty()){
            usuarioList.put(usuario.getId(), usuario);
            return true;
        }
        usuarioList.put(usuario.getId(), usuario);
        return true;
    }

    public Usuario buscarUsuarioPorId(int id){
        for(Map.Entry<Integer,Usuario> pair: usuarioList.entrySet()){
            if(pair.getValue().getId() == id){
                return pair.getValue();
            }
        }
        return null;
    }

    public void listarTodosOsUsers(){
        usuarioList.forEach((id, user) -> System.out.println(user.toString()));
    }

    public void listarUsuariosPorName(String name){
        usuarioList.values().stream().filter(u -> u.getName().equals(name)).forEach(System.out::println);
    }

    public void ordenarUsuariosPorName(){
        usuarioList.values().stream().sorted(Comparator.comparing(Usuario::getName)).forEach(System.out::println);
    }

    public boolean removerUser(int id){
        if(usuarioList.containsKey(id)){
            usuarioList.remove(id);
            return true;
        }
        return false;
    }





    //Gestao do evento
    public boolean criarEvento(String name,Evento event){
        if(event == null){
            return false;
        }

        if(verificarSeExisteEvento(event) == true){
            return false;
        }

       eventosDisponiveis.put(name,event);
        return true;
    }

    public boolean verificarSeExisteEvento(Evento event){
        return eventosDisponiveis.values().stream().anyMatch(e -> e.getName().equals(event.getName()));
    }

    public Evento buscarevento(String name){
        for(Map.Entry<String, Evento> pair: eventosDisponiveis.entrySet()){
            if(pair.getValue().getName().equals(name)){
                return pair.getValue();
            }
        }
        return null;
    }

    public void listarTodosOsEventos(){
        eventosDisponiveis.forEach((s1,s2) -> System.out.println(s2.toString()));
    }

    public void listarEventosComVagas(){
        eventosDisponiveis.values().stream().filter(e -> e.getParticipantes().size() <  e.getCapacidadeMax()).forEach(System.out::println);
    }

    public void  listarEventosOrdenadosPorCapacidade(){
       eventosDisponiveis.values().stream().sorted(Comparator.comparing(Evento::getCapacidadeMax));
    }

    //Gestao da inscricao

    public boolean inscreverUsarioEmEvento(int id, String name){
        Evento buscaEvent = buscarevento(name);
        Usuario buscaUser = buscarUsuarioPorId(id);

        if(buscaEvent == null || buscaUser == null){
            return false;
        }

        buscaEvent.getParticipantes().add(buscaUser);
        return true;
    }

    public boolean cancelarInscricao(int id, String name){
        Evento buscaEvent = buscarevento(name);
        Usuario buscaUser = buscarUsuarioPorId(id);

        if(buscaEvent == null || buscaUser == null){
            return false;
        }

        buscaEvent.getParticipantes().remove(id);
        return true;
    }

    public void listarParticipantesDoEvento(String name){
        Evento buscaEvent = buscarevento(name);

        buscaEvent.getParticipantes().stream().forEach(System.out::println);
    }

}
