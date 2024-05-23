import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel Ventana;
    private JTabbedPane tabbedPane1;
    private JSpinner spinner1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JButton button1;
    private JTextField textField2;
    private JButton totaplPaqueteButton;
    private JButton totalPesoButton;
    private JComboBox comboBox3;
    private JButton totalPesoPorCiudadButton;

    Lista paquetes = new Lista();
    public Ventana() {

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
                        paquetes.adicionarElemento(new Paqueteria(Integer.parseInt( spinner1.getValue().toString()) , Integer.parseInt(textField1.getText()), comboBox1.getSelectedItem().toString(), comboBox2.getSelectedItem().toString(), textField2.getText().toString()));

                        System.out.println(paquetes.listarPaquetes());
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }}
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
}
