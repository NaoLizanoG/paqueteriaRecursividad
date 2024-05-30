import java.util.ArrayList;
import java.util.List;

public class Lista {
    List<Paqueteria> serviEntrega;

    public List<Paqueteria> getServiEntrega() {
        return serviEntrega;
    }

    public void setServiEntrega(List<Paqueteria> serviEntrega) {
        this.serviEntrega = serviEntrega;
    }

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
    public void cambiar(Paqueteria paquete,int peso, String ciudadEntrega, String ciudadRecepcion, String cedulaReceptor){
       paquete.setPeso(peso);
       paquete.setCiudadEntrega(ciudadEntrega);
       paquete.setCiudadRecepcion(ciudadRecepcion);
       paquete.setCedulaReceptor(cedulaReceptor);
    }

    public Paqueteria buscarPaqueteria(int tracking)throws Exception {
        for (Paqueteria pa : serviEntrega) {
            if (pa.getTracking() == tracking) {
                return pa;
            }

        }
        throw new Exception("No se encontro el paquete");
    }

    public List<Paqueteria> listarPaquetes() {
        List<Paqueteria> lista = new ArrayList<>();
        for (Paqueteria pa : serviEntrega) {
            lista.add(pa);
        }
        return lista;
    }

    public List<Paqueteria> listarCedulaEstado(String cedula, String estado) {
        List<Paqueteria> lista = new ArrayList<>();
        for (Paqueteria pa : serviEntrega) {
            if(pa.getCedulaReceptor().equals(cedula) && pa.getEstado().equals(estado)){
            lista.add(pa);
        }}
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


    public double sumarTotalPesoEstado(String estado){
        return totalPesoEstado(0, estado);
    }

    private  double totalPesoEstado(int indice, String estado){
        if(serviEntrega.size()==indice){
            return 0;
        } else if (serviEntrega.get(indice).getEstado().equals(estado)){
            return serviEntrega.get(indice).getPeso()+totalPesoEstado(indice+1, estado);

        }else{
            return totalPesoEstado(indice+1, estado);
        }

    }

    public  int sumarTotalEstado(String estado){
        return totalEstado(0, estado);
    }

    private int totalEstado(int indice, String estado){
        if(serviEntrega.size()==indice){
            return 0;
        }else if (serviEntrega.get(indice).getEstado().equals(estado)){
            return 1+totalEstado(indice+1, estado);

        }else{
            return totalEstado(indice+1, estado);

        }
    }







}