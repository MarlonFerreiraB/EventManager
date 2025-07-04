package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Evento {
    private String name;
    private String ID;
    private LocalDate localDate;
    private int capacidadeMax;
    Set<Usuario> participantes;

    public Evento(String name, LocalDate localDate, int capacidadeMax) {
        this.name = name;
        this.localDate = localDate;
        this.capacidadeMax = capacidadeMax;
        this.participantes = new HashSet<>();
        this.ID = UUID.randomUUID().toString();
    }

    public boolean addParticipante(Usuario user){
        if(participantes == null){
            return false;
        }
        participantes.add(user);
        return true;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public int getCapacidadeMax() {
        return capacidadeMax;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public Set<Usuario> getParticipantes() {
        return participantes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Evento{");
        sb.append("name='").append(name).append('\'');
        sb.append(", localDate=").append(localDate);
        sb.append(", capacidadeMax=").append(capacidadeMax);
        sb.append('}');
        return sb.toString();
    }
}
