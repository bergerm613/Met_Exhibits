package berger.met;

import javax.swing.*;
import java.awt.*;

public class MetFrame extends JFrame
{
    private final MetService service;
    private final MetController controller;

    private final JLabel searchLabel;
    private final JButton searchButton;
    private final JComboBox<DepartmentsFeed.Department> deptCombo;

    private final JLabel titleLabel;
    private final JLabel cultureLabel;
    private final JLabel mediumLabel;
    private final JLabel artistLabel;
    private final JLabel classificationLabel;
    private final JLabel pictureLabel;

    private final JButton backButton;
    private final JButton nextButton;

    private final JPanel centerPanel;
    private final JPanel inputPanel;
    private final JPanel arrowsPanel;
    private final JPanel infoPanel;

    public MetFrame()
    {
        setSize(800,600);
        setTitle("Met Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        inputPanel = new JPanel();
        infoPanel = new JPanel();
        centerPanel = new JPanel();
        arrowsPanel = new JPanel();

        searchLabel = new JLabel("Choose a Department:");
        deptCombo = new JComboBox<>();
        searchButton = new JButton("SEARCH");
        searchButton.addActionListener(actionEvent -> search());

        titleLabel = new JLabel();
        cultureLabel = new JLabel();
        mediumLabel = new JLabel();
        artistLabel = new JLabel();
        classificationLabel = new JLabel();
        pictureLabel = new JLabel();

        backButton = new JButton("<<");
        backButton.addActionListener(actionEvent -> back());
        nextButton = new JButton(">>");
        nextButton.addActionListener(actionEvent -> next());

        inputPanel.setLayout(new GridLayout(2,1));
        inputPanel.add(searchLabel);
        inputPanel.add(deptCombo);
        inputPanel.add(searchButton);

        infoPanel.setLayout(new GridLayout(3,2));
        infoPanel.add(titleLabel);
        infoPanel.add(new JLabel());
        infoPanel.add(cultureLabel);
        infoPanel.add(mediumLabel);
        infoPanel.add(artistLabel);
        infoPanel.add(classificationLabel);

        centerPanel.setLayout(new GridLayout(2,1));
        centerPanel.add(infoPanel);
        centerPanel.add(pictureLabel, SwingConstants.CENTER);

        arrowsPanel.setLayout(new GridLayout(1,2));
        arrowsPanel.add(backButton);
        arrowsPanel.add(nextButton);

        add(inputPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(arrowsPanel, BorderLayout.PAGE_END);

        service = new MetServiceFactory().getInstance();

        controller = new MetController(service,
                                       titleLabel,
                                       cultureLabel,
                                       mediumLabel,
                                       artistLabel,
                                       classificationLabel,
                                       pictureLabel);
        controller.getDepartments(deptCombo);
    }

    private void next()
    {
        controller.getNextObject();
    }

    private void back()
    {
        controller.getLastObject();
    }

    private void search()
    {
        DepartmentsFeed.Department selectedDept = (DepartmentsFeed.Department) deptCombo.getSelectedItem();
        controller.getDepartmentObjects(selectedDept.departmentId);
    }

    public static void main(String[] args)
    {
        new MetFrame().setVisible(true);
    }
}
