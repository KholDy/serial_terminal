package xyz.kholdy;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class StartApp extends Application{
	
	private SerialPort comPort;
	
	private Button btnSearch, btnOpenPort, btnClosePort, btnSend, btnOk, btnClear, btnComPortOptions;
	private Button btnCancel;
	private TextArea textArea;
	private TextField textFieldEnter;
	
	private String strSend = "";
	private String[] comPortNames;
	
	private ObservableList<String> comPortList;
	private ComboBox<String> listComboBox;
	 
	Label lbBaudRate, lbStopBits, lbDataBits;

	//Чтение из COM порта по событию принятия данных
	private class EventListener implements SerialPortEventListener{

		@Override
		public void serialEvent(SerialPortEvent event) {
			// TODO Auto-generated method stub
			if(event.isRXCHAR() && event.getEventValue() == 8) {
				try {
					textArea.appendText(comPort.readString());
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("comTerminal");
		primaryStage.setResizable(false);
		
		FlowPane root = new FlowPane(10, 10);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 300, 430);
		
		primaryStage.setScene(scene);
//*********************************************************************************************************************************	
		btnSearch = new Button("Search");
		btnSearch.setMinSize(100, 20);
		
		btnOpenPort =  new Button("Open Port");
		btnOpenPort.setMinSize(135, 20);
		
		btnClosePort = new Button("Close Port");
		btnClosePort.setMinSize(135, 20);
		
		btnSend = new Button("Send");
		btnSend.setMinSize(50, 20);
		
		btnOk = new Button("Ok");
		btnOk.setPrefSize(135, 20);
		
		btnCancel = new Button("Cancel");
		btnCancel.setMinSize(135, 20);
		
		btnClear = new Button("Clear");
		btnClear.setMinSize(280, 20);
		
		btnComPortOptions = new Button("Com Port Options");
		btnComPortOptions.setMinSize(280, 20);
		
		textArea = new TextArea();
		textArea.setMaxSize(280, 250);
		textArea.setEditable(false);
		
		textFieldEnter = new TextField();
		textFieldEnter.setPrefSize(220, 20);
		
		lbBaudRate = new Label("Baud Rate: " + WindowComPortOptions.BaudRate);
		lbBaudRate.setMinWidth(100);
		lbStopBits = new Label("Stop Bits: " + WindowComPortOptions.StopBits);
		lbStopBits.setMinWidth(80);
		lbDataBits = new Label("Data Bits: " + WindowComPortOptions.DataBits);
		lbDataBits.setMinWidth(80);
		
		listComboBox = new ComboBox<String>();
		listComboBox.setMinSize(170, 20);
//*********************************************************************************************************************************
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				comPortNames = SerialPortList.getPortNames();
				comPortList = FXCollections.observableArrayList(comPortNames);
				listComboBox.setItems(comPortList);
			}
			
		});
		
		btnComPortOptions.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				WindowComPortOptions.comPortOptions("Com Port Options");
			}
		});
		
		btnOpenPort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				lbBaudRate.setText("Baud Rate: " + WindowComPortOptions.BaudRate);
				lbStopBits.setText("Stop Bits: " + WindowComPortOptions.StopBits);
				lbDataBits.setText("Data Bits: " + WindowComPortOptions.DataBits);
				
				comPort = new SerialPort(listComboBox.getValue());
				
				try {
					comPort.openPort();
					comPort.setParams(WindowComPortOptions.BaudRate, WindowComPortOptions.DataBits, 
							WindowComPortOptions.StopBits, SerialPort.PARITY_NONE);
					comPort.setEventsMask(SerialPort.MASK_RXCHAR);
					comPort.addEventListener(new EventListener());
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		btnClosePort.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					comPort.closePort();
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnClear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				textArea.clear();
			}
		});
		
		btnSend.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				strSend = textFieldEnter.getText();
				textArea.appendText(strSend + "\n");
				try {
					comPort.writeString(strSend);
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		btnOk.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		root.getChildren().addAll(listComboBox, btnSearch, btnComPortOptions, lbBaudRate, lbStopBits, lbDataBits ,
				btnOpenPort, btnClosePort, textArea, btnClear, textFieldEnter, btnSend, btnOk, btnCancel);
		
		primaryStage.show();
	}

}
