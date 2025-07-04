package app;

import model.Evento;
import model.Usuario;

import java.util.*;

public class GerenciadorDeEventos {
    private static final Map<String, Evento> eventosDisponiveis = new HashMap<>();

    public GerenciadorDeEventos(){}

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

}
