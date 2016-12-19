package Lesson_7.HomeWork;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.TextArea;
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

    public static void main(String[] args) {

        Gui frame = new Gui();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(300, 500);
        frame.setVisible(true);

        JLabel labelWarriorName = new JLabel("Имя война");
        JButton buttonSetTeamNames = new JButton("Задать имена командам");
        JButton buttonAddWarrior = new JButton("Добавить война в отряд");
        JTextArea fieldFirstNameTeam = new JTextArea("Название первой команды", 2, 20);
        JTextArea fieldSecondNameTeam = new JTextArea("Название второй команды", 2, 20);
        JTextArea fieldNameWarrior = new JTextArea(1, 10);
        TextArea fieldFirstTeamWarriorList = new TextArea(10, 15);
        TextArea fieldSecondTeamWarriorList = new TextArea(10, 15);
        JComboBox<String> comboBoxTeam = new JComboBox<>(new String[] {"Первая команда", "Вторая команда"});
        JComboBox<String> comboBoxTypeWarrior =
                new JComboBox<>(new String[] {"Viking", "Archer", "Barbarian"});
        TextArea log = new TextArea(20, 90);

        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.add(fieldFirstNameTeam);
        topPanel.add(buttonSetTeamNames);
        topPanel.add(fieldSecondNameTeam);

        JPanel panelWarriorInfo = new JPanel(new GridLayout(4, 1, 5, 20));
        topPanel.setBackground(Color.LIGHT_GRAY);
        panelWarriorInfo.add(labelWarriorName);
        panelWarriorInfo.add(fieldNameWarrior);
        panelWarriorInfo.add(comboBoxTypeWarrior);
        panelWarriorInfo.add(buttonAddWarrior);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 22, 10));
        topPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel.add(comboBoxTeam);
        centerPanel.add(panelWarriorInfo);
        centerPanel.add(fieldFirstTeamWarriorList);
        fieldFirstTeamWarriorList.setEditable(false);
        centerPanel.add(fieldSecondTeamWarriorList);
        fieldSecondTeamWarriorList.setEditable(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(log);
        log.setEditable(false);


        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.pack();
    }
}
