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
            serviEntrega.add(p);
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


   public int  sumarTotalPeso(){
        return totalPeso(0);
    }


    private int totalPeso(int indice){
        if(serviEntrega.size()==indice){
            return 0;
        }else{
            return serviEntrega.get(indice).getPeso()+totalPeso(indice+1);

        }
    }

    public double sumarTotalPesoCiudad(String ciudad){
        return totalPesoCiudad(0, ciudad);
    }

    private  double totalPesoCiudad(int indice, String ciudad){
        if(serviEntrega.size()==indice){
            return 0;
        } else if (serviEntrega.get(indice).getCiudadEntrega().equals(ciudad)){
            return serviEntrega.get(indice).getPeso()+totalPesoCiudad(indice+1, ciudad);

        }else{
            return totalPesoCiudad(indice+1, ciudad);
        }

        }
    }



