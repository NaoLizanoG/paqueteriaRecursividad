import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Ventana {
    private JPanel Ventana;
    private JTabbedPane tabbedPane1;
    private JSpinner spinner1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textFieldCedula;
    private JButton button1;
    private JTextField textFieldPeso;
    private JButton totaplPaqueteButton;
    private JButton totalPesoButton;
    private JComboBox comboBoxCiudad;
    private JButton totalPesoPorCiudadButton;
    private JList list1;
    private JButton modificarButton;
    private JSpinner spinner2;
    private JButton modificarButton1;
    private JTextPane textPane1;
    private JButton ordenarButton;
    private JList listNormal;
    private JList listOrdenada;
    private JButton ordenarPorIncersiónPesoButton;
    private JButton verEstadoButton;
    private JButton pesoEstadoButton;
    private JComboBox comboBoxEstado;
    private JTextArea textAreaModificada;
    private JList list2;
    private JTextPane textPane2;
    private JSpinner spinner3;
    private JButton búsquedaNoLinealButton;
    private JButton totalPorEstadoButton;
    private JTextField textFieldCedulaB;
    private JComboBox comboBox3;
    private JTextPane textPane3;
    private JButton buscar2Button;
    private JTextArea textAreaNormal;

    Lista paquetes = new Lista();



    public Ventana() {
        quemarDatos();
        llenarJList();

        totaplPaqueteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "El total de paquetes es: " + paquetes.sumarTotalPaquetes());
            }
        });
        totalPesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, paquetes.sumarTotalPeso());
            }
        });
        totalPesoPorCiudadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "El total de peso por ciudad es: " + paquetes.sumarTotalPesoCiudad(comboBoxCiudad.getSelectedItem().toString()));
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    try {
                        paquetes.adicionarElemento(new Paqueteria(Integer.parseInt( spinner1.getValue().toString()) , Integer.parseInt(textFieldPeso.getText().toString()), comboBox1.getSelectedItem().toString(), comboBox2.getSelectedItem().toString(), textFieldCedula.getText().toString()));

                        System.out.println(paquetes.listarPaquetes());
                        limpiar();
                        llenarJList();
                        quemarDatos();

                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }}
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list1.getSelectedIndex()!=-1){
                    int indice = list1.getSelectedIndex();
                    Paqueteria pa = paquetes.getServiEntrega().get(indice);
                    spinner1.setValue(pa.getTracking());
                    textFieldPeso.setText(String.valueOf(pa.getPeso()));
                    comboBox1.setSelectedItem(pa.getCiudadEntrega());
                    comboBox2.setSelectedItem(pa.getCiudadRecepcion());
                    textFieldCedula.setText(pa.getCedulaReceptor());
                }
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paqueteria paquete1= null;
                try {
                    paquete1 = paquetes.buscarPaqueteria(Integer.parseInt(spinner1.getValue().toString()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                paquetes.cambiar(paquete1, Integer.parseInt(textFieldPeso.getText().toString()), comboBox1.getSelectedItem().toString(), comboBox2.getSelectedItem().toString(), textFieldCedula.getText().toString());
            llenarJList();
            }
        });
        modificarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paqueteria paquete1 = null;
                try {
                    paquete1 = paquetes.buscarPaqueteria(Integer.parseInt(spinner2.getValue().toString()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                textPane1.setText("Tracking="+paquete1.getTracking()+"\nEstado="+paquete1.getEstado());
                try {
                    paquete1.cambiarEstado();
                    textPane1.setText("Tracking="+paquete1.getTracking()+"\nEstado="+paquete1.getEstado());
                    JOptionPane.showMessageDialog(null, "Se ha cambiado el estado del paquete");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lista paqueteria2 = new Lista();
                paqueteria2.serviEntrega=(paquetes.listarPaquetes());
                Lista listaburbuja = ordenarBurbuja(paqueteria2);
                textAreaModificada.setText(listaburbuja.listarPaquetes().toString());
                llenarJList2();
            }
        });
        verEstadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paqueteria paquete1 = null;
                try {
                    paquete1 = paquetes.buscarPaqueteria(Integer.parseInt(spinner2.getValue().toString()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                textPane1.setText("Tracking="+paquete1.getTracking()+"\nEstado="+paquete1.getEstado());
            }
        });
        pesoEstadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "El total de peso por estado es: " + paquetes.sumarTotalPesoEstado(comboBoxEstado.getSelectedItem().toString()));
            }
        });
        ordenarPorIncersiónPesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lista paqueteria2 = new Lista();
                paqueteria2.serviEntrega=(paquetes.listarPaquetes());
                Lista listaInsercion = ordenarInsercion(paqueteria2);
                textAreaModificada.setText(listaInsercion.listarPaquetes().toString());
                llenarJList2();
            }
        });
        búsquedaNoLinealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Lista paqueteria2 = new Lista();
                    paqueteria2.serviEntrega=(paquetes.listarPaquetes());
                    Lista listaInsercion = ordenarBurbuja(paqueteria2);
                    Paqueteria paquete1 = buscarBinario(listaInsercion);
                    textPane2.setText(paquete1.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        totalPorEstadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "El total de paquetes por estado es: " + paquetes.sumarTotalEstado(comboBoxEstado.getSelectedItem().toString()));
            }
        });
        buscar2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   Lista  lista = new Lista();
                   lista.serviEntrega =   paquetes.listarCedulaEstado(textFieldCedulaB.getText().toString(), comboBox3.getSelectedItem().toString());
                   textPane3.setText(lista.listarPaquetes().toString());
            }
        });
    }

    public static void main(String[] args) {


        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(300,400);

    }
    public void quemarDatos(){
        try{
        paquetes.adicionarElemento(new Paqueteria(1, 10, "Quito", "Guayaquil", "123"));
        paquetes.adicionarElemento(new Paqueteria(3, 5, "Guayaquil", "Cuenca", "12345"));
        paquetes.adicionarElemento(new Paqueteria(2, 35, "Cuenca", "Quito", "1234"));


        }catch(Exception ex){}}

    public void llenarJList(){
        DefaultListModel dlm = new DefaultListModel();
        dlm.removeAllElements();
        for(Paqueteria pa:paquetes.listarPaquetes()){
            dlm.addElement(pa);
        }
        list1.setModel(dlm);

    }

    public void llenarJList2(){
        DefaultListModel dlm2 = new DefaultListModel();
        dlm2.removeAllElements();
        for(Paqueteria pa:paquetes.listarPaquetes()){
            dlm2.addElement(pa);
        }
        list2.setModel(dlm2);

    }

    public Lista ordenarBurbuja(Lista paquetes){
for(int i=0; i<paquetes.getServiEntrega().size(); i++){
    boolean swapped = false;
            for(int j=0; j<paquetes.getServiEntrega().size()-i-1; j++){
                Paqueteria p1= (Paqueteria) paquetes.getServiEntrega().get(j);
                Paqueteria p2 = (Paqueteria) paquetes.getServiEntrega().get(j+1);

                if(p1.getTracking()> p2.getTracking()){
                    Paqueteria aux = (Paqueteria) paquetes.getServiEntrega().get(j);
                    paquetes.getServiEntrega().set(j, p2);
                    paquetes.getServiEntrega().set(j+1, p1);
                    swapped = true;
                }
            }
            if (swapped==false){
                break;
            }
        }
    return  paquetes;

    }
    public Lista ordenarInsercion(Lista paquetes) {
        int n = paquetes.serviEntrega.size();
        for (int i = 1; i < n; ++i) {
            Paqueteria p = paquetes.serviEntrega.get(i);
            int j = i - 1;


            while (j >= 0 && paquetes.serviEntrega.get(j).getPeso() > p.getPeso()) {
                paquetes.serviEntrega.set(j + 1, paquetes.serviEntrega.get(j));
                j = j - 1;
            }
            paquetes.serviEntrega.set(j + 1, p);
        }
return paquetes;

    }

    public Paqueteria buscarBinario(Lista paquetes)throws Exception{
        int inicio =0;
        int fin = paquetes.serviEntrega.size()-1;
        int medio;
        int resultado=-1;
        while(inicio<=fin){
            medio=(inicio+fin)/2;
            if(paquetes.serviEntrega.get(medio).getTracking()==Integer.parseInt(spinner3.getValue().toString())){
                resultado=medio;
                fin=inicio-1;
                return paquetes.serviEntrega.get(resultado);
        } else if (paquetes.serviEntrega.get(medio).getTracking()<Integer.parseInt(spinner3.getValue().toString())){
                inicio=medio+1;

        }else {
                fin=medio-1;
            }
        }
        throw new Exception("No se encontro el paquete");
            }

    public void limpiar(){
        textFieldCedula.setText("");
        textFieldPeso.setText("");
        spinner1.setValue(0);
        comboBox1.setSelectedIndex(0);
        comboBox2.setSelectedIndex(0);
    }
}
