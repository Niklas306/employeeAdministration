package view;

import control.MainController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.BeanDescriptor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

public class PanelHandler {

    private JPanel panel;
    private JLabel lbTtl;
    private JTextField tfName;
    private JLabel lblName;
    private JButton btnSave;
    private JTextField tfBirth;
    private JTextField tfCity;
    private JLabel lblFinished;
    private JButton btnDel;
    private JTextField tfSalary;
    private JLabel lblSalary;
    private JLabel lblBirth;
    private JLabel lblCity;
    private JLabel lblEmployee;
    private JButton btnName;
    private MainController mainController;

    public PanelHandler(MainController mainController) throws IOException {
        this.mainController = mainController;
        createButtons();
    }

    private void createButtons() throws IOException {

        //Variablen werden definiert
        final String[] employeeName = new String[1];
        final String[] employeeBirth = new String[1];
        final String[] employeeCity = new String[1];
        final String[] employeeSalary = new String[1];

        //ActionListener für btnSave
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //Variablen werden gesetzt
                employeeName[0] = tfName.getText();
                employeeBirth[0] = tfBirth.getText();
                employeeCity[0] = tfCity.getText();
                employeeSalary[0] = tfSalary.getText();

                //Ausgabe in Console
                //System.out.println("Name: " + employeeName[0]);
                //System.out.println("Geburtsjahr: " + employeeBirth[0]);
                //System.out.println("Herrkunft: " + employeeCity[0]);
                //System.out.println("Gehalt: " + employeeSalary[0]);

                //Ausgabe in lblFinished
                lblFinished.setText("Fertig");

                //Werte werden in Datei gespeichert
                FileWriter fw = null;

                try {
                    fw = new FileWriter(java.util.Arrays.toString(employeeName) +  ".txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BufferedWriter bw  = new BufferedWriter(fw);

                try {
                    bw.write("Name: " + java.util.Arrays.toString(employeeName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bw.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bw.write("Geburtsjahr: " + java.util.Arrays.toString(employeeBirth));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bw.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bw.write("Herrkunft: " + java.util.Arrays.toString(employeeCity));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bw.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bw.write("Gehalt: " + java.util.Arrays.toString(employeeSalary));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Datei auslesen
                FileReader fr = null;
                try {
                    fr = new FileReader(java.util.Arrays.toString(employeeName) + ".txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                BufferedReader br = new BufferedReader(fr);

                String zeile = "";

                while(true)
                {
                    try {
                        if (!((zeile = br.readLine()) != null)) break;
                    } catch (IOException e) {
                    }

                    //Ausgabe der letzten Zeile in lblEmployee
                    lblEmployee.setText(zeile + "\n");

                    //Ausgabe der letzten Zeile in Console
                    System.out.println(zeile);
                }
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //ActionListener für btnDel
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //openFileDialog wird erstellt
                FileNameExtensionFilter filter = new FileNameExtensionFilter("txt","txt");

                final JFileChooser openFileDialog = new JFileChooser("Datei zum Löschen auswählen");
                openFileDialog.setDialogType(JFileChooser.OPEN_DIALOG);
                openFileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
                openFileDialog.setDialogTitle("Datei zum löschen auswählen");
                openFileDialog.setFileFilter(filter);

                //Standard
                final File file = new File("/home");

                //Alternativ
                //String working_dir = System.getProperty("user.dir");
                //final File file = new File("/home");

                openFileDialog.setCurrentDirectory(file);

                openFileDialog.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        if (e.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) || e.getPropertyName().equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
                            final File f = (File) e.getNewValue();
                        }
                    }
                });
                openFileDialog.setVisible(true);
                final int result = openFileDialog.showOpenDialog(null);

                //ausgewählte Datei wird gelöscht
                    File fileA = new File(String.valueOf(openFileDialog.getSelectedFile()));
                    fileA.delete();
            }
        });
    }

    private void updateGUI() {

    }

    public JPanel getPanel() {
        return panel;
    }
}
