import java.util.ArrayList;
import java.util.List;

public class Lista {
    List<Paqueteria> serviEntrega;

    public Lista(){
        serviEntrega = new ArrayList<Paqueteria>();
    }

    public void adicionarElemento(Paqueteria p)throws Exception{
        if(serviEntrega.isEmpty()){
            serviEntrega.add(p);
        }else {
            for(Paqueteria pa:serviEntrega){
                if(pa.getTracking()==p.getTracking()){
                    throw new Exception("El tracking ya esta registrado");
                }

            }
        }

    }


    public String buscarPaqueteria(int tracking){
        for(Paqueteria pa:serviEntrega){
            if(pa.getTracking()==tracking){
                return pa.getEstado();
            }
        }
        return null;
    }

    public List<Paqueteria> listarPaquetes() {
        List<Paqueteria> lista = new ArrayList<>();
        for (Paqueteria pa : serviEntrega) {
            lista.add(pa);
        }
        return lista;
    }


    public  int sumarTotalPaquetes(){
        return totalPaquetes(0);
    }

    private int totalPaquetes(int indice){
                 if(serviEntrega.size()==indice){
                    return 0;
                 }else{
                     return 1+totalPaquetes(indice+1);

                 }
    }


   public int  sumarTotalPeso(String ciudad){
        return totalPeso(0);
    }


    private int totalPeso(int indice{
        if(serviEntrega.size()==indice){
            return 0;
        }else{
            return serviEntrega.get(indice).getPeso()+totalPaquetes(indice+1);

        }
    }

    private double sumarTotalPesoCiudad(String ciudad){

    }

    public  double totalPesoCiudad(int indice, String ciudad){

    }


}
