package xyz.kholdy;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowComPortOptions {
	
	static int BaudRate, StopBits, DataBits;

	public static void comPortOptions(String titleName) {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(titleName);
		window.setResizable(false);
		
		FlowPane root = new FlowPane(10,10);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 300, 310);
		window.setScene(scene);
//*********************************************************************************************************************************
		
		//Button*******************************************************************************************************************
		Button btnOk = new Button("Ok");
		btnOk.setMinSize(135, 20);
		btnOk.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				window.close();
			}
		});
		
		Button btnCancel = new Button("Cancel");
		btnCancel.setMinSize(135, 20);
		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				window.close();
			}
		});
		//Label*********************************************************************************************************************
		Label lbBaudRate = new Label("Baud Rate:");
		lbBaudRate.setMinSize(290, 5);
		lbBaudRate.setAlignment(Pos.CENTER);
		
		Label lbStopBits = new Label("Stop Bits");
		lbStopBits.setMinWidth(290);
		lbStopBits.setAlignment(Pos.CENTER);
		
		Label lbDataBits = new Label("Data Bits");
		lbDataBits.setMinWidth(290);
		lbDataBits.setAlignment(Pos.CENTER);
		//RadioButton***************************************************************************************************************
		//RadioButton Baud Rate
		RadioButton rb7200 = new RadioButton("7200");
		RadioButton rb9600 = new RadioButton("9600");
		RadioButton rb14400 = new RadioButton("14400");
		RadioButton rb19200 = new RadioButton("19200");
		RadioButton rb38400 = new RadioButton("38400");
		RadioButton rb115200 = new RadioButton("115200");
		RadioButton rb128000 = new RadioButton("128000");
		RadioButton rb230400 = new RadioButton("230400");
		RadioButton rb256000 = new RadioButton("256000");
		
		ToggleGroup tgSpeedBod = new ToggleGroup();
		tgSpeedBod.getToggles().addAll(rb7200, rb9600, rb14400, rb19200, rb38400, rb115200, rb128000, rb230400, rb256000);
		
		tgSpeedBod.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				BaudRate = Integer.parseInt(((ToggleButton) newValue).getText());
			}
		});
		
		//RadioButton Stop Bits
		RadioButton rbOneBit = new RadioButton("1");
		RadioButton rbTwoBits = new RadioButton("2");
		
		ToggleGroup tgStopBits = new ToggleGroup();
		tgStopBits.getToggles().addAll(rbOneBit, rbTwoBits);
		
		tgStopBits.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				StopBits = Integer.parseInt(((ToggleButton) newValue).getText());
			}
		});
		
		//RadioButton Data Bits
		RadioButton rbDB_5 = new RadioButton("5");
		RadioButton rbDB_6 = new RadioButton("6");
		RadioButton rbDB_7 = new RadioButton("7");
		RadioButton rbDB_8 = new RadioButton("8");
		
		ToggleGroup tgDataBits = new ToggleGroup();
		tgDataBits.getToggles().addAll(rbDB_5, rbDB_6, rbDB_7, rbDB_8);
		
		tgDataBits.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub
				DataBits = Integer.parseInt(((ToggleButton) newValue).getText());
			}
		});
		//Separator****************************************************************************************************************
		Separator spDataRateTop = new Separator();
		Separator spDataRateBottom = new Separator();
		Separator spStopBitsBottom = new Separator();
		Separator spDataBitsBottom = new Separator();
		
		spDataRateTop.setMinWidth(280);
		spDataRateBottom.setMinWidth(280);
		spStopBitsBottom.setMinWidth(280);
		spDataBitsBottom.setMinWidth(280);
//*********************************************************************************************************************************
		root.getChildren().addAll(spDataRateTop, lbBaudRate, rb7200, rb9600, rb14400, rb19200, rb38400, rb115200, rb128000, 
				rb230400, rb256000, spDataRateBottom, lbStopBits, rbOneBit, rbTwoBits, spDataBitsBottom, lbDataBits,
				rbDB_5, rbDB_6, rbDB_7, rbDB_8, spStopBitsBottom, btnOk, btnCancel);
		window.showAndWait();
	}
	
}
