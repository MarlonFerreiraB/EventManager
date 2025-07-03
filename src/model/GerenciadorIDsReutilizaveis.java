package model;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorIDsReutilizaveis {
    private static final Map<Integer,Usuario> IDsNulos = new HashMap<>();

    public Map<Integer,Usuario> pegarLista(){
        return IDsNulos;
    }

    public boolean addUser(Usuario user){
        if(IDsNulos == null){
            return false;
        }
        if(IDsNulos.isEmpty()){
            IDsNulos.put(user.getId(), user);
            return true;
        }
        IDsNulos.put(user.getId(), user);
        return true;
    }

    public Integer verificarIds(){
        Integer idVazio = null;
        for(Map.Entry<Integer, Usuario> pair: IDsNulos.entrySet()){
            Usuario verificador = pair.getValue();
            if(verificador == null){
                idVazio = pair.getKey();
            }
        }
        return idVazio;
    }




}
