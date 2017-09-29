import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestForm extends JFrame
{
    private JLabel firstLabel, secondLabel, thirdLabel, fourthLabel, fifthLabel;

    private JTextField firstTxt, secondTxt, thirdTxt, fourthTxt, fifthTxt;

    private JPanel centerPanel;
    private JPanel southPanel;
    private JLabel comboLabel;
    private JComboBox percentageCombo;
    private JLabel endTargetLabel;
    private JLabel mustLoseLabel;

    private GridBagLayout mainLayout = new GridBagLayout();
    private GridBagConstraints mainCons = new GridBagConstraints();

      public TestForm()
      {
        //Declaring instance variables  
        firstLabel = new JLabel("First: ");
        secondLabel = new JLabel("Second: ");
        thirdLabel = new JLabel("Third: ");
        fourthLabel = new JLabel("Fourth: ");
        fifthLabel = new JLabel("Fifth: ");        
        comboLabel = new JLabel("Select System Performance: ");

        firstTxt = new JTextField(7);
        secondTxt = new JTextField(7);
        thirdTxt = new JTextField(7);
        fourthTxt = new JTextField(7);
        fifthTxt = new JTextField(7);

        endTargetLabel = new JLabel("Your End Target Performance is: ");
        mustLoseLabel = new JLabel("Sammple Performance You Must Lose: ");  

        percentageCombo = new JComboBox();
        percentageCombo.addItem("No Value is Set");
        percentageCombo.addItem("One item is Set");


       this.setLayout(mainLayout);
        mainCons.gridy = 1;
        mainCons.gridx = 1;
        mainCons.anchor = GridBagConstraints.NORTH;
        this.add(createNorthPanel(),mainCons);

        mainCons.anchor = GridBagConstraints.WEST;
        mainCons.gridy = 2;
        mainCons.gridx = 1;
        mainCons.anchor = GridBagConstraints.CENTER;
        mainCons.insets = new Insets(15,0,0,0);
        this.add(createCenterPanel(),mainCons);

        mainCons.anchor = GridBagConstraints.SOUTH;
        mainCons.gridy = 3;
        mainCons.gridx = 1;
        this.add(createSouthPanel(),mainCons);

        this.setTitle("The Test Form");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private JPanel createNorthPanel()
    {
        JPanel northPanel = new JPanel();

        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());

        JLabel logoLabel = new JLabel("This is a label");
//        logoLabel.setIcon(new ImageIcon(getClass().getResource("/images/TESTING-LOGO.gif")));

        northPanel.add(logoLabel);

        return northPanel;
    }


    private JPanel createCenterPanel()
    {
        centerPanel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        centerPanel.setLayout(gbl);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15,0,0,0);
        centerPanel.add(firstLabel,gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15,0,0,0);
        centerPanel.add(firstTxt,gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15,10,0,0);
        centerPanel.add(secondLabel,gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15,-10,0,0);
        centerPanel.add(secondTxt,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15,0,0,0);
        centerPanel.add(thirdLabel,gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15,0,0,0);
        centerPanel.add(thirdTxt,gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15,10,0,0);
        centerPanel.add(fourthLabel,gbc);

        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15,-10,0,0);
        centerPanel.add(fourthTxt,gbc);

        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15,7,0,0);
        centerPanel.add(fifthLabel,gbc);

        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15,5,0,0);
        centerPanel.add(fifthTxt,gbc);

        centerPanel.setBorder(BorderFactory.createTitledBorder("The Testing Form"));
        centerPanel.validate();

        return centerPanel;

    }


     private JPanel createSouthPanel()
    {
        southPanel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        southPanel.setLayout(gbl);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15,0,0,0);
        southPanel.add(comboLabel,gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15,5,0,0);
        southPanel.add(percentageCombo,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10,0,0,0);
        southPanel.add(endTargetLabel,gbc);


        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10,0,0,0);
        southPanel.add(mustLoseLabel,gbc);

        southPanel.setBorder(BorderFactory.createTitledBorder("See Your End Target "));

        return southPanel;
    }

    public static void main(String[]args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new TestForm();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}