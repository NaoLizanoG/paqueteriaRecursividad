import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
public class Paqueteria {
    public int tracking;
    public int peso;
    public String ciudadEntrega;
    public String ciudadRecepcion;
    public String cedulaReceptor;
    public String estado;


    public Paqueteria(int tracking, int peso, String ciudadEntrega, String ciudadRecepcion, String cedulaReceptor) {
        this.tracking = tracking;
        this.peso = peso;
        this.ciudadEntrega = ciudadEntrega;
        this.ciudadRecepcion = ciudadRecepcion;
        this.cedulaReceptor = cedulaReceptor;
    }
    
    public void cambiarEstado()throws Exception{

        if (estado.equals("Enviado")){
            estado="Entregado";
        } else if (estado.equals("Entregado")){
            estado="Recibido";
        } else if (estado.equals(null)){
            estado="Enviado";

        } else if (estado.equals("Recibido")) {
            throw new Exception("El paquete ya fue recibido");

        }


    }

    public int getTracking() {
        return tracking;
    }

    public void setTracking(int tracking) {
        this.tracking = tracking;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getCiudadEntrega() {
        return ciudadEntrega;
    }

    public void setCiudadEntrega(String ciudadEntrega) {
        this.ciudadEntrega = ciudadEntrega;
    }

    public String getCiudadRecepcion() {
        return ciudadRecepcion;
    }

    public void setCiudadRecepcion(String ciudadRecepcion) {
        this.ciudadRecepcion = ciudadRecepcion;
    }

    public String getCedulaReceptor() {
        return cedulaReceptor;
    }

    public void setCedulaReceptor(String cedulaReceptor) {
        this.cedulaReceptor = cedulaReceptor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Paqueteria{" +
                "tracking=" + tracking +
                ", peso=" + peso +
                ", ciudadEntrega=" + ciudadEntrega +
                ", ciudadRecepcion=" + ciudadRecepcion +
                ", cedulaReceptor=" + cedulaReceptor +
                ", estado=" + estado +
                '}';
    }
}
