package org.example.txtfilemaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class AppController {
    public TextField fileTypeField;
    @FXML
    private TextField fileNameField;
    @FXML
    private TextArea fileContentField;
    @FXML
    private Label statusLabel;
    public static FileChooser fileChooser = new FileChooser();
    public static File sfile;


    @FXML
    private void createButton(ActionEvent actionEvent) {
        if(fileTypeField.getText().isEmpty() || fileNameField.getText().isEmpty()){
            showAlert("Ошибка", "Пожайлуста, введите информацию о файле.");
            return;
        }
        boolean status = FileGenLogin.genFile(sfile, fileContentField.getText());
        if (status){
            statusLabel.setText("Файл успешно создан в " + sfile.getAbsolutePath());
        }
        else{
            statusLabel.setText("Файл не получилось создать.");
        }
    }
    public void initialize(){
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    public void chooseFilePath(ActionEvent actionEvent) {

        Window stage = fileTypeField.getScene().getWindow();

        fileChooser.setTitle("Сохранить файл");

        String defaultName = fileNameField.getText().trim();
        if (defaultName.isEmpty()) {
            defaultName = "new_file";
        }

        fileChooser.setInitialFileName(defaultName + fileTypeField.getText());

        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        fileTypeField.getText() + " файлы",
                        "*" + fileTypeField.getText()
                )
        );


        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            sfile = file;
            if (fileNameField.getText().trim().isEmpty()) {
                String name = file.getName();
                int dotIndex = name.lastIndexOf('.');
                if (dotIndex > 0) {
                    fileNameField.setText(name.substring(0, dotIndex));
                }
            }
            statusLabel.setText("Выбран файл: " + file.getAbsolutePath());

            fileChooser.setInitialDirectory(file.getParentFile());
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
