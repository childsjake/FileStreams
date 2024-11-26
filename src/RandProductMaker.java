import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandProductMaker extends JFrame
{
    JPanel mainPnl;

    JPanel textPnl;
    JTextField prodNameTF;
    JTextField prodCostTF;
    JTextField prodDescTF;
    JTextField prodIDTF;
    JTextField recordTF;
    JLabel prodNameLbl;
    JLabel prodCostLbl;
    JLabel prodDescLbl;
    JLabel prodIDLbl;
    JLabel recordLbl;

    JPanel controlsPnl;
    JButton addBtn;
    JButton quitBtn;

    private int recordCount = 0;

    public RandProductMaker ()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        CreateTextPanel();
        mainPnl.add(textPnl, BorderLayout.CENTER);

        CreateControlsPanel();
        mainPnl.add(controlsPnl, BorderLayout.SOUTH);

        add(mainPnl);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        setSize(screenWidth * 2 / 4, screenHeight * 5 / 8);
        setLocation(screenWidth / 2, screenHeight / 8);
        setTitle("Random Product Maker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void CreateTextPanel()
    {
        textPnl = new JPanel();
        textPnl.setLayout(new GridLayout(5, 2));
        prodNameTF = new JTextField(35);
        prodCostTF = new JTextField(8);
        prodDescTF = new JTextField(75);
        prodIDTF = new JTextField(6);
        recordTF = new JTextField(5);
        prodNameLbl = new JLabel("Product Name: ");
        prodCostLbl = new JLabel("Product Cost: ");
        prodDescLbl = new JLabel("Product Description: ");
        prodIDLbl = new JLabel("Product ID: ");
        recordLbl = new JLabel("Record Count: ");
        recordTF.setEditable(false);
        recordTF.setText(String.valueOf(recordCount));

        textPnl.add(prodNameLbl);
        textPnl.add(prodNameTF);
        textPnl.add(prodDescLbl);
        textPnl.add(prodDescTF);
        textPnl.add(prodIDLbl);
        textPnl.add(prodIDTF);
        textPnl.add(prodCostLbl);
        textPnl.add(prodCostTF);
        textPnl.add(recordLbl);
        textPnl.add(recordTF);


    }

    private void CreateControlsPanel()
    {
        controlsPnl = new JPanel();
        controlsPnl.setLayout(new GridLayout(1, 2));

        addBtn = new JButton("Add");
        addBtn.addActionListener((ActionEvent ae) ->
        {
            AddProduct();
        });

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) ->
        {
            int selectedOption = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        });

        controlsPnl.add(addBtn);
        controlsPnl.add(quitBtn);
    }

    private void AddProduct()
    {
        String name = prodNameTF.getText().trim();
        String description = prodDescTF.getText().trim();
        String ID = prodIDTF.getText().trim();
        double cost;

        try {
            cost = Double.parseDouble(prodCostTF.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (name.isEmpty() || description.isEmpty() || ID.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Product product = new Product(ID, name, description, cost);

        try (RandomAccessFile file = new RandomAccessFile("productTestData.dat", "rw")){
            file.seek(file.length());
            file.writeBytes(product.toRandomAccess() + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
        prodNameTF.setText("");
        prodDescTF.setText("");
        prodIDTF.setText("");
        prodCostTF.setText("");
        recordCount++;
        recordTF.setText(String.valueOf(recordCount));
    }
}
