import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JComboBox comboBox3;
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

    Lista paquetes = new Lista();


    public Ventana() {
        quemarDatos();

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
                JOptionPane.showMessageDialog(null, "El total de peso por ciudad es: " + paquetes.sumarTotalPesoCiudad(comboBox3.getSelectedItem().toString()));
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
                }
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paqueteria paquete1= paquetes.buscarPaqueteria(Integer.parseInt(spinner1.getValue().toString()));
                paquetes.cambiar(paquete1, Integer.parseInt(textFieldPeso.toString()), comboBox1.getSelectedItem().toString(), comboBox2.getSelectedItem().toString(), textFieldCedula.getText().toString());
            llenarJList();
            }
        });
        modificarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Paqueteria paquete1 =  paquetes.buscarPaqueteria(Integer.parseInt(spinner2.getValue().toString()));
                textPane1.setText(paquete1.estado);
                try {
                    paquete1.cambiarEstado();
                    textPane1.setText(paquete1.estado);
                    JOptionPane.showMessageDialog(null, "Se ha cambiado el estado del paquete");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lista paqueteria2;
                paqueteria2=paquetes;
                Lista listaburbuja = ordenarBurbuja(paqueteria2);
                llenarJList2(listaburbuja);
                llenarJList3();
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
        paquetes.adicionarElemento(new Paqueteria(2, 35, "Cuenca", "Quito", "1234"));
        paquetes.adicionarElemento(new Paqueteria(3, 5, "Guayaquil", "Cuenca", "1234"));

        }catch(Exception ex){}}

    public void llenarJList(){
        DefaultListModel dlm = new DefaultListModel();
        dlm.removeAllElements();
        for(Paqueteria pa:paquetes.listarPaquetes()){
            dlm.addElement(pa);
        }
        list1.setModel(dlm);

    }
    public void llenarJList2(Lista listarB ){
        DefaultListModel dlm2 = new DefaultListModel();
        dlm2.removeAllElements();
        for(Paqueteria pa:listarB.listarPaquetes()){
            dlm2.addElement(pa);
        }
        listOrdenada.setModel(dlm2);

    }
    public void llenarJList3(){
        DefaultListModel dlm3 = new DefaultListModel();
        dlm3.removeAllElements();
        for(Paqueteria pa:paquetes.listarPaquetes()){
            dlm3.addElement(pa);
        }
        listNormal.setModel(dlm3);

    }

    public Lista ordenarBurbuja(Lista paquetes){
        List<Paqueteria> paquetes2 = paquetes.listarPaquetes();
for(int i=0; i<paquetes2.size(); i++){
    boolean swapped = false;
            for(int j=0; j<paquetes2.size()-i-1; j++){
                Paqueteria p1= (Paqueteria) paquetes2.get(j);
                Paqueteria p2 = (Paqueteria) paquetes2.get(j+1);

                if(p1.getTracking()> p2.getTracking()){
                    Paqueteria aux = (Paqueteria) paquetes2.get(j);
                    paquetes2.set(j, p2);
                    paquetes2.set(j+1, p1);
                    swapped = true;
                }
            }
            if (swapped==false){
                break;
            }
        }
    return  (Lista) paquetes2;

    }

    public void limpiar(){
        textFieldCedula.setText("");
        textFieldPeso.setText("");
        spinner1.setValue(0);
        comboBox1.setSelectedIndex(0);
        comboBox2.setSelectedIndex(0);
    }
}
