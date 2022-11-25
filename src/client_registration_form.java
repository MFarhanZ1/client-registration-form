import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class client_registration_form {
    private JPanel rootPanel;
    private JTextField textNomorKartu;
    private JComboBox comboBoxJenisATM;
    private JComboBox comboBoxNamaBank;
    private JTextField textNamaPemilik;
    private JButton saveButton;
    private JTextArea textAreaOutputData;
    private JButton browseFileButton;
    private JPanel attachedImagePanel;
    private JLabel displayingImageField;
    public  String imageFileName;

    public void insertingDataToFile(String inputtedData) throws IOException {
        FileWriter fw = new FileWriter("src/database/StoredTextData.txt",true);
        fw.write(inputtedData);
        fw.close();
    }

    public client_registration_form() {
        browseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();

                int choosenAct = jfc.showSaveDialog(null);
                if (choosenAct == JFileChooser.APPROVE_OPTION){
                    imageFileName = jfc.getSelectedFile().getName();

                    // displaying an image
                    displayingImageField.setBounds(3,20, 200, 200);
                    displayingImageField.setIcon(new ImageIcon(jfc.getSelectedFile().getAbsolutePath()));
                    displayingImageField.setText("");

                    // saving an image
                    String pathSrc = jfc.getSelectedFile().getAbsolutePath();
                    String destSrc = System.getProperty("user.dir") + "/src/database/StoredImage/" + jfc.getSelectedFile().getName();

                    File src = new File(pathSrc);
                    File dest = new File(destSrc);
                    try {
                        Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (imageFileName ==  null){
                    JOptionPane.showMessageDialog(null, "Masukan gambar terlebih dahulu sebelum melanjutkan proses penyimpanan data!");
                    return;
                }

                textAreaOutputData.setText("");

                String getNomorKartu = textNomorKartu.getText();
                String getJenisATM = (String) comboBoxJenisATM.getSelectedItem();
                String getNamaBank = (String) comboBoxNamaBank.getSelectedItem();
                String getNamaPemilik = textNamaPemilik.getText();

                String result = String.format("Nomor Kartu : %s\nJenis ATM : %s\nNama Bank : %s\nNama Pemilik : %s\nFile Gambar : %s",getNomorKartu, getJenisATM, getNamaBank, getNamaPemilik, imageFileName);

                JOptionPane.showMessageDialog(null, "Your Registration Has Been Succesfully Procceded!");
                textAreaOutputData.append(result);

                try {
                    insertingDataToFile(result + "\n\n");
                    imageFileName = null;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
