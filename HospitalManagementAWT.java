import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Patient {
    int id;
    String name;
    int age;
    String disease;

    Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }
}

public class HospitalManagementAWT extends Frame implements ActionListener {
    Label lblName, lblAge, lblDisease, lblSearch;
    TextField tfName, tfAge, tfDisease, tfSearch;
    Button btnAdd, btnShowAll, btnSearch;
    TextArea taDisplay;
    ArrayList<Patient> patients = new ArrayList<>();
    int nextId = 1;

    HospitalManagementAWT() {
        setTitle("Hospital Management System");
        setLayout(null);
        setSize(600, 500);
        setVisible(true);

        lblName = new Label("Name:");
        lblName.setBounds(50, 50, 50, 20);
        add(lblName);

        tfName = new TextField();
        tfName.setBounds(120, 50, 150, 20);
        add(tfName);

        lblAge = new Label("Age:");
        lblAge.setBounds(50, 80, 50, 20);
        add(lblAge);

        tfAge = new TextField();
        tfAge.setBounds(120, 80, 150, 20);
        add(tfAge);

        lblDisease = new Label("Disease:");
        lblDisease.setBounds(50, 110, 60, 20);
        add(lblDisease);

        tfDisease = new TextField();
        tfDisease.setBounds(120, 110, 150, 20);
        add(tfDisease);

        btnAdd = new Button("Add Patient");
        btnAdd.setBounds(50, 150, 100, 30);
        btnAdd.addActionListener(this);
        add(btnAdd);

        btnShowAll = new Button("Show All");
        btnShowAll.setBounds(170, 150, 100, 30);
        btnShowAll.addActionListener(this);
        add(btnShowAll);

        lblSearch = new Label("Search ID:");
        lblSearch.setBounds(50, 200, 70, 20);
        add(lblSearch);

        tfSearch = new TextField();
        tfSearch.setBounds(120, 200, 150, 20);
        add(tfSearch);

        btnSearch = new Button("Search");
        btnSearch.setBounds(290, 200, 80, 25);
        btnSearch.addActionListener(this);
        add(btnSearch);

        taDisplay = new TextArea();
        taDisplay.setBounds(50, 240, 500, 200);
        add(taDisplay);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            String name = tfName.getText();
            int age;
            try {
                age = Integer.parseInt(tfAge.getText());
            } catch (NumberFormatException ex) {
                taDisplay.setText("Invalid age input.");
                return;
            }
            String disease = tfDisease.getText();

            Patient p = new Patient(nextId++, name, age, disease);
            patients.add(p);
            taDisplay.setText("Patient Added: " + p.name);
            tfName.setText(""); tfAge.setText(""); tfDisease.setText("");
        }

        if (e.getSource() == btnShowAll) {
            StringBuilder sb = new StringBuilder("ID\tName\tAge\tDisease\n");
            for (Patient p : patients) {
                sb.append(p.id).append("\t")
                  .append(p.name).append("\t")
                  .append(p.age).append("\t")
                  .append(p.disease).append("\n");
            }
            taDisplay.setText(sb.toString());
        }

        if (e.getSource() == btnSearch) {
            int searchId;
            try {
                searchId = Integer.parseInt(tfSearch.getText());
            } catch (NumberFormatException ex) {
                taDisplay.setText("Invalid ID input.");
                return;
            }

            boolean found = false;
            for (Patient p : patients) {
                if (p.id == searchId) {
                    taDisplay.setText("Found Patient:\nID: " + p.id +
                                      "\nName: " + p.name +
                                      "\nAge: " + p.age +
                                      "\nDisease: " + p.disease);
                    found = true;
                    break;
                }
            }

            if (!found) {
                taDisplay.setText("Patient with ID " + searchId + " not found.");
            }
        }
    }

    public static void main(String[] args) {
        new HospitalManagementAWT();
    }
}
