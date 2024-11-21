/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author Aluno
 */
public class Constraints {
    public static void validacaoParaNumerosDecimal(TextField txt) {
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*([\\.]\\d*)?")) {
                return change;
            }
            return null;
        });
        txt.setTextFormatter(formatter);
    }
    
    public static void validacaoParaNumeros(TextField txt) {
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        });
        txt.setTextFormatter(formatter);
    }
    
    public static void validacaoParaNumerosDecimalComLimiteDeDigito(TextField txt) {
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*([\\.]\\d{0,3})?")) {
                return change;
            }
            return null;
        });
        txt.setTextFormatter(formatter);
    }
    
    public static void setRemoveTextFieldDouble(TextField txt) {
        txt.setTextFormatter(null);
    }
}
