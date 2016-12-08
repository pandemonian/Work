package Lesson_7.ClassWork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by admin on 07.12.2016.
 */
public class MainFrame extends JFrame {

    private NumberArea inputArea;
    private NumberArea outputArea;

    public MainFrame() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        initComponents();
    }

    private void initComponents() {
        inputArea = new NumberArea();
        outputArea = new NumberArea();
        add(inputArea);
        JButton sortButton = new JButton("Сортировать");
        sortButton.addActionListener(e -> {
            ArrayList<String> values = inputArea.getValues();
            ArrayList<String> numbers = new ArrayList<>();
            for (String value: values) {
                if (value.matches("-?[0-9]+"))
                    numbers.add(value);
            }

             Collections.sort(numbers, (o1, o2) -> Integer.valueOf(o1.compareTo(o2)));



            outputArea.setValues(numbers);

        });
        add(sortButton);
        add(outputArea);
        pack();
    }

    private class NumberArea extends JTextArea {
        public NumberArea() {
            super(10,10);
        }

        public ArrayList<String> getValues() {
            return new ArrayList<>(Arrays.asList(getText().split("\n")));
        }

        public void setValues(ArrayList<String> values){
            StringBuilder stringBuilder = new StringBuilder();
            for (String value: values) {
                stringBuilder.append(value).append("\n");
            }
            setText(stringBuilder.toString());
        }
    }






}

