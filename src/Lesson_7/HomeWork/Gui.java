package Lesson_7.HomeWork;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.JFrame;

/**
 * Created by Gubanov Pavel on 19.12.16.
 */
public class Gui extends JFrame {

    //private Gui frame;
    private JLabel labelWarriorName;
    private JButton buttonSetTeamNames;
    private JButton buttonAddWarrior;
    private JButton buttonStartFight;
    private static JTextArea fieldFirstNameTeam;
    private static JTextArea fieldSecondNameTeam;
    private static JTextArea fieldNameWarrior;
    private static TextArea fieldFirstTeamWarriorList;
    private static TextArea fieldSecondTeamWarriorList;
    private static JComboBox<String> comboBoxTeam;
    private static JComboBox<String> comboBoxTypeWarrior;
    private static TextArea log;
    private static StringBuilder stringBuilderLog1 = new StringBuilder();
    private static StringBuilder stringBuilderLog2 = new StringBuilder();


    Gui() {
        super("Приложение \"Битва\"");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        initComponents();
    }







    private void initComponents() {

        buttonSetTeamNames = new JButton("Задать имена командам");
        buttonStartFight = new JButton("Начать битву");
        labelWarriorName = new JLabel("Имя война");
        buttonAddWarrior = new JButton("Добавить война в отряд");
        fieldFirstNameTeam = new JTextArea("Название первой команды", 2, 20);
        fieldSecondNameTeam = new JTextArea("Название второй команды", 2, 20);
        fieldNameWarrior = new JTextArea(1, 10);
        fieldFirstTeamWarriorList = new TextArea(10, 15);
        fieldSecondTeamWarriorList = new TextArea(10, 15);
        comboBoxTeam = new JComboBox<>(new String[] {"Первая команда", "Вторая команда"});
        comboBoxTypeWarrior = new JComboBox<>(new String[] {"Viking", "Archer", "Barbarian"});
        log = new TextArea(20, 90);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.add(fieldFirstNameTeam);
        topPanel.add(buttonSetTeamNames);
        topPanel.add(fieldSecondNameTeam);

        JPanel panelStartFight = new JPanel(new GridLayout(2, 1, 5, 40));
        panelStartFight.add(comboBoxTeam);
        panelStartFight.add(buttonStartFight);

        JPanel panelWarriorInfo = new JPanel(new GridLayout(4, 1, 5, 20));
        topPanel.setBackground(Color.LIGHT_GRAY);
        panelWarriorInfo.add(labelWarriorName);
        panelWarriorInfo.add(fieldNameWarrior);
        panelWarriorInfo.add(comboBoxTypeWarrior);
        panelWarriorInfo.add(buttonAddWarrior);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 22, 10));
        topPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel.add(panelStartFight);
        centerPanel.add(panelWarriorInfo);
        centerPanel.add(fieldFirstTeamWarriorList);
            fieldFirstTeamWarriorList.setEditable(false);
        centerPanel.add(fieldSecondTeamWarriorList);
            fieldSecondTeamWarriorList.setEditable(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(log);
        log.setEditable(false);

        buttonSetTeamNames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Run.initNameTeams();
                buttonSetTeamNames.setEnabled(false);
            }
        });

        buttonAddWarrior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Run.initNameAndTypeWarriors();
                //fieldNameWarrior.setText("");
            }
        });



        add(bottomPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        pack();
    }

    public static String getFieldFirstNameTeam() {
        return fieldFirstNameTeam.getText();
    }

    public static String getFieldSecondNameTeam() {
        return fieldSecondNameTeam.getText();
    }

    public static int getComboBoxTeam() {
        return comboBoxTeam.getSelectedIndex();
    }

    public static int getComboBoxTypeWarrior() {
        return comboBoxTypeWarrior.getSelectedIndex();
    }

    public static String getFieldNameWarrior() {
        return fieldNameWarrior.getText();
    }

    public static void setFieldFirstTeamWarriorList(String text) {
        fieldFirstTeamWarriorList.setText(text + "\n");
    }

    public static void setFieldSecondTeamWarriorList(String text) {
        fieldSecondTeamWarriorList.setText(text + "\n");
    }

    public static void setLog(String text) {
        stringBuilderLog1.append(text).append("\n");
        log.setText(stringBuilderLog1.toString());
    }

    public static void setLog2(String text1, String text2) {
        stringBuilderLog2.append(text1);
        stringBuilderLog2.append(" ");
        stringBuilderLog2.append(text2);
        //.append("\n");
        log.setText(stringBuilderLog2.toString());
    }

}
