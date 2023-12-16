package com.example.semesterproject;


import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class GemData {

    private static  final  GemData instance = new GemData();

    private final ArrayList<Button> savedButtons = new ArrayList<>();

    private  final ArrayList<ChoiceBox> savedChoicebox = new ArrayList<>();

    private GemData(){}

    public static GemData getInstance(){


        return instance;
    }
    public ArrayList<Button> getSavedButton() {
        return savedButtons;
    }

    public void setSavedButton(Button button) {
        savedButtons.add(button);
    }

    public ArrayList<ChoiceBox> getSavedChoicebox() {
        return savedChoicebox;
    }

    public void setSavedChoicebox(ChoiceBox choicebox) {
        savedChoicebox.add(choicebox);

    }
}

