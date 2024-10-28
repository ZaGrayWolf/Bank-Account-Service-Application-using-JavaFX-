
import java.util.ArrayList;

import java.math.*;
import javafx.application.Application;
import javax.swing.*;

import java.awt.*;

import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class Account {
	private double annualInterestRate = 3.0;
	private int Id;
	private double balance;
	private String pass;

	Account() {
	}

	Account(int id, double bal, double nannualInterestRate,String pass) {
		Id = id;
		balance = bal;
		annualInterestRate = nannualInterestRate;
		this.pass= pass;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getId() {
		return Id;
	}

	public void setBalance(double bal) {
		balance = bal;
	}

	public double getBalance() {
		return balance;
	}

	public String getpass() {
		return pass;
	}
public boolean checkPass(String pass1){
if(pass1.equals(pass))
{
	return true;
}
else 
return false;
}

	public void setAnnualInterestRate(double AnnualInterestRate) {
		annualInterestRate = AnnualInterestRate;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}



	public void withdraw(double amount) {
		balance=balance-amount;
	}
	public boolean withdrawCheck(double amount){
		if(balance>amount)
		return true;
		else 
		return false;
	}

	public void deposit(double amount) {
		balance = balance + amount;
	}
}



public class ComboProj extends Application {
	TextField accountNumber = new TextField();
	TextField accountNumber1 = new TextField();
	TextField deposits = new TextField();
	TextField pass = new TextField();
	TextField withdrawl = new TextField();
	TextField annualInterestRate = new TextField();
	TextField accountBalance = new TextField();
	Button button3;
	BorderPane borderpane = new BorderPane();
	Label message = new Label();
	Label message1 = new Label();
	Map<Integer, Account> accountMap = new HashMap<Integer, Account>();

	@Override
	public void start(Stage primaryStage) {
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.setStyle("-fx-color:pink");
		ObservableList<String> items = FXCollections.observableArrayList("Open New Account",
				"Deposit", "Withdraw", "Check Balance","Fund Transfer", "Close Account");
		comboBox.getItems().addAll(items);
		BorderPane paneforcombo = new BorderPane();
		Label lbl = new Label("Select A Service: ");
		Font fontn = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16);
		lbl.setFont(fontn);
		lbl.setTextFill(Color.BLACK);
		Font font1 = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);
			message.setFont(font1);
			message.setText("TERA MERA BANK");
			
		paneforcombo.setLeft(lbl);
		paneforcombo.setCenter(comboBox);
		borderpane.setTop(paneforcombo);
		borderpane.setBottom(message);
		comboBox.setPrefSize(1050, 30);
		borderpane.setPadding(new Insets(12, 14, 15, 18));
		comboBox.setOnAction(e -> {
			if (comboBox.getValue().equals("Open New Account")) {
				openAcFunction();
			}  else if (comboBox.getValue().equals("Deposit")) {
				dpositFunction();
			} else if (comboBox.getValue().equals("Withdraw")) {
				withdrawFunction();
			} else if (comboBox.getValue().equals("Check Balance")) {
				checkBalanceFunction();
			}else if (comboBox.getValue().equals("Fund Transfer")) {
				fundTransferFunction();
			} else if (comboBox.getValue().equals("Close Account")) {
				closeAccountFunction();
			}
		});
		primaryStage.setTitle("Tera Mera Bank");
		
		Scene scene = new Scene(borderpane, 650, 350);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void openAcFunction() {
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setPadding(new Insets(20, 15, 20, 15));
		gridpane.setHgap(10);
		gridpane.setVgap(10);
		gridpane.add(new Label("Account Number:"), 0, 0);
		gridpane.add(accountNumber, 1, 0);
		gridpane.add(new Label("Deposit Amount:"), 0, 1);
		gridpane.add(deposits, 1, 1);
		gridpane.add(new Label("Annual Interst Rate:"), 0, 2);
		gridpane.add(annualInterestRate, 1, 2);
		gridpane.add(new Label("Account Balance:"), 0, 3);
		gridpane.add(accountBalance, 1, 3);
		gridpane.add(new Label("Password:"), 0, 4);
		gridpane.add(pass, 1, 4);
		accountNumber.clear();
		annualInterestRate.clear();
		accountBalance.clear();
		pass.clear();
		deposits.clear();
		Button button;
		Button button1;
		button = new Button("Open Account");
		button1 = new Button("Cancel");
		gridpane.add(button, 0, 5);
		gridpane.add(button1, 1, 5);
		borderpane.setCenter(gridpane);
		accountNumber.setEditable(false);
		annualInterestRate.setEditable(false);
		accountBalance.setEditable(false);
		button.setOnAction(e -> calculateBalance());
		button1.setOnAction(e -> cancel());
		gridpane.add(message, 0, 8);
		borderpane.setCenter(gridpane);
		accountNumber.setText("");
		annualInterestRate.setText(String.format("%.2f", 3.0));
	}

	public void calculateBalance() {
		double deposit2 = Double.parseDouble(deposits.getText());
		double interstRate = (annualInterestRate.getText() != "") ? Double.parseDouble(annualInterestRate.getText())
				: 3.0;
		double Balance = 0.0;
		int id = (int) (1000 + Math.random() * 5);
		String pass1 = String.valueOf(pass.getText());
		
		if(pass1.length()<8)
		{
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Password cannot be less than 8 characters.");
		}
		else{
			System.out.println(id);
		Account account = new Account(id, Balance, interstRate,pass1);
		account.deposit(deposit2);
		account.setAnnualInterestRate(interstRate);
		accountNumber.setText(String.format("%d", account.getId()));
		accountBalance.setText(String.format("$%.2f", account.getBalance()));
		annualInterestRate.setText(String.format("%.2f", account.getAnnualInterestRate()));
		Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
		message.setFont(fontn);
		accountMap.put(account.getId(), account);
		message.setText("Account Created");
		System.out.print("Account Created");
		}
	}

	private void cancel() {
		accountNumber.clear();
		annualInterestRate.clear();
		accountBalance.clear();
		pass.clear();
		deposits.clear();
		message.setText(null);
	}
	

	

	

	public void dpositFunction() {
		GridPane depositdgridpane = new GridPane();
		depositdgridpane.setAlignment(Pos.CENTER);
		depositdgridpane.setHgap(10);
		depositdgridpane.setVgap(10);
		depositdgridpane.setPadding(new Insets(20, 15, 20, 15));
		depositdgridpane.add(new Label("Account Number:"), 0, 0);
		depositdgridpane.add(accountNumber, 1, 0);
		depositdgridpane.add(new Label("Deposit Amount:"), 0, 1);
		depositdgridpane.add(deposits, 1, 1);
		depositdgridpane.add(new Label("Account  Balance:"), 0, 2);
		depositdgridpane.add(accountBalance, 1, 2);
		depositdgridpane.add(new Label("Password:"), 0, 3);
		depositdgridpane.add(pass, 1, 3);
		accountNumber.clear();
		accountBalance.clear();
		deposits.clear();
		pass.clear();
		Button button;
		Button button1;
		button = new Button("Make Deposit");
		button1 = new Button("Cancel");
		depositdgridpane.add(button, 0, 6);
		depositdgridpane.add(button1, 1, 6);
		depositdgridpane.add(message, 0, 9);
		depositdgridpane.add(message1, 0, 10);
		borderpane.setCenter(depositdgridpane);
		accountNumber.setEditable(true);
		accountBalance.setEditable(false);
		button.setOnAction(e -> calculatedepositBalance());
		button1.setOnAction(e -> canceldeposit());
	}

	private void canceldeposit() {
		accountNumber.clear();
		accountBalance.clear();
		deposits.clear();
		pass.clear();
		message.setText(null);
		message1.setText(null);
	}

	public void calculatedepositBalance() { 
		int id = Integer.parseInt(accountNumber.getText());
		String pass1= String.valueOf(pass.getText());
		Account objAccount = null;
		if (accountMap.containsKey(id)) {
			objAccount = accountMap.get(id);
			double amount = Double.parseDouble(deposits.getText());
			if(objAccount.checkPass(pass1))
			{
			objAccount.deposit(amount);
			accountMap.put(objAccount.getId(), objAccount);
			accountBalance.setText(String.format("$%.2f", objAccount.getBalance()));
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Deposit Transaction Completed");
			}
			else{
				Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Incorrect password. Try again.");
			}
		}  else {
			System.out.println("Account not exist for account number:" + id);
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message1.setFont(fontn);
			message1.setText("Invalid Account Number..Please Try Again!");
			accountBalance.setText(String.format("$%.2f", 0.0));
		}
	}

	public void withdrawFunction() {
		GridPane withdrawlgridpane = new GridPane();
		withdrawlgridpane.setAlignment(Pos.CENTER);
		withdrawlgridpane.setHgap(10);
		withdrawlgridpane.setVgap(10);
		withdrawlgridpane.setPadding(new Insets(20, 15, 20, 15));
		withdrawlgridpane.add(new Label("Account Number:"), 0, 0);
		withdrawlgridpane.add(accountNumber, 1, 0);
		withdrawlgridpane.add(new Label("Withdraw Amount:"), 0, 1);
		withdrawlgridpane.add(withdrawl, 1, 1);
		withdrawlgridpane.add(new Label("Account  Balance:"), 0, 2);
		withdrawlgridpane.add(accountBalance, 1, 2);
		withdrawlgridpane.add(new Label("Password:"), 0, 3);
		withdrawlgridpane.add(pass, 1, 3);
		accountNumber.clear();
		withdrawl.clear();
		pass.clear();
		accountBalance.clear();
		Button button;
		Button button1;
		button = new Button("Make Withdraw");
		button1 = new Button("Cancel");
		withdrawlgridpane.add(button, 0, 6);
		withdrawlgridpane.add(button1, 1, 6);
		borderpane.setCenter(withdrawlgridpane);
		accountNumber.setEditable(true);
		accountBalance.setEditable(false);
		withdrawlgridpane.add(message, 0, 9);
		withdrawlgridpane.add(message1, 0, 10);
		button.setOnAction(e -> calculatewithdrawBalance());
		button1.setOnAction(e -> cancelwithdraw());
	}

	private void cancelwithdraw() {
		accountNumber.clear();
		withdrawl.clear();
		pass.clear();
		accountBalance.clear();
		message.setText(null);
		message1.setText(null);
	}

	private void calculatewithdrawBalance() {
		int id = Integer.parseInt(accountNumber.getText());
		String pass1= String.valueOf(pass.getText());
		Account objAccount = null;
		
		double amount = Double.parseDouble(withdrawl.getText());
	if(accountMap.containsKey(id)) {
			objAccount = accountMap.get(id);
			if(objAccount.checkPass(pass1))
			{
			
			if(objAccount.withdrawCheck(amount))
			{
				objAccount.withdraw(amount);
			accountMap.put(objAccount.getId(), objAccount);
			accountBalance.setText(String.format("$%.2f", objAccount.getBalance()));
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Withdraw Transaction Completed");
			}
			else
			{
				Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Insufficent Balance. Cannot withdraw requested amount.");
			}
		}else{
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Incorrect Password. Try again.");
		}
		}
		  else {
			System.out.println("Account not exist for account number:" + id);
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message1.setText("Invalid Account Number..Please Try Again!");
			accountBalance.setText(String.format("$%.2f", 0.0));
		}
	}
	public void fundTransferFunction() {
		GridPane ftgridpane = new GridPane();
		ftgridpane.setAlignment(Pos.CENTER);
		ftgridpane.setHgap(10);
		ftgridpane.setVgap(10);
		ftgridpane.setPadding(new Insets(20, 15, 20, 15));
		ftgridpane.add(new Label("Account Number of sender:"), 0, 0);
		ftgridpane.add(accountNumber, 1, 0);
		ftgridpane.add(new Label("Account Number of receiever:"), 0, 1);
		ftgridpane.add(accountNumber1, 1, 1);
		ftgridpane.add(new Label("Transfer amount:"), 0, 2);
		ftgridpane.add(deposits, 1, 2);
		ftgridpane.add(new Label("Password of sender:"), 0, 3);
		ftgridpane.add(pass, 1, 3);
		accountNumber.clear();
		accountNumber1.clear();
		deposits.clear();
		pass.clear();
		Button button;
		Button button1;
		button = new Button("Transfer fund");
		button1 = new Button("Cancel");
		ftgridpane.add(button, 0, 6);
		ftgridpane.add(button1, 1, 6);
		ftgridpane.add(message, 0, 9);
		ftgridpane.add(message1, 0, 10);
		borderpane.setCenter(ftgridpane);
		accountNumber.setEditable(true);
		button.setOnAction(e -> calculateft());
		button1.setOnAction(e -> cancelft());
	}

	private void calculateft() {
		int id1 = Integer.parseInt(accountNumber.getText());
		int id2 = Integer.parseInt(accountNumber1.getText());
		String pass1= String.valueOf(pass.getText());
		Account objAccount = null;
		Account objAccount1 = null;
		
		double amount = Double.parseDouble(deposits.getText());
	if(accountMap.containsKey(id1)&&accountMap.containsKey(id2)) {
			objAccount = accountMap.get(id1);
			objAccount1 = accountMap.get(id2);
			if(objAccount.checkPass(pass1))
			{
			if(objAccount.withdrawCheck(amount))
			{
				objAccount.withdraw(amount);
				accountMap.put(objAccount.getId(), objAccount);
				objAccount1.deposit(amount);
				accountMap.put(objAccount1.getId(), objAccount1);
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Fund Transfer Completed");
			}
			else
			{
				Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Insufficent Balance. Cannot transfer requested amount.");
			}
		}else{
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Incorrect Password. Try again.");
		}
		}
		  else {
			System.out.println("Account not exist for given account numbers");
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message1.setText("Invalid Account Number..Please Try Again!");
			accountBalance.setText(String.format("$%.2f", 0.0));
		}
	}

	private void cancelft() {
		accountNumber.clear();
		accountNumber1.clear();
		deposits.clear();
		pass.clear();
		message.setText(null);
		message1.setText(null);
	}
	public void checkBalanceFunction() {
		GridPane checkbalgridpane = new GridPane();
		checkbalgridpane.setAlignment(Pos.CENTER);
		checkbalgridpane.setHgap(10);
		checkbalgridpane.setVgap(10);
		checkbalgridpane.setPadding(new Insets(20, 15, 20, 15));
		checkbalgridpane.add(new Label("Account Number:"), 0, 0);
		checkbalgridpane.add(accountNumber, 1, 0);
		checkbalgridpane.add(new Label("Account  Balance:"), 0, 1);
		checkbalgridpane.add(accountBalance, 1, 1);
		checkbalgridpane.add(new Label("Password:"), 0, 2);
		checkbalgridpane.add(pass, 1, 2);
		accountNumber.clear();
		accountBalance.clear();
		pass.clear();
		Button button;
		Button button1;
		button = new Button("Check Balance");
		button1 = new Button("Cancel");
		checkbalgridpane.add(button, 0, 6);
		checkbalgridpane.add(button1, 1, 6);
		borderpane.setCenter(checkbalgridpane);
		accountNumber.setEditable(true);
		accountBalance.setEditable(false);
		checkbalgridpane.add(message, 0, 8);
		button.setOnAction(e -> calculatecheckBalance());
		button1.setOnAction(e -> cancelcheckaccount());
	}

	private void cancelcheckaccount() {
		accountNumber.clear();
		accountBalance.clear();
		pass.clear();
		message.setText(null);
	}

	private void calculatecheckBalance() {
		int id = Integer.parseInt(accountNumber.getText());
		String pass1= String.valueOf(pass.getText());
		if (accountMap.containsKey(id)) {
			Account objAccount = accountMap.get(id);
			if(objAccount.checkPass(pass1)){
			accountBalance.setText(String.format("$%.2f", objAccount.getBalance()));
			}
			else{
				Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Incorrect password. Try again.");
			}
		} else {
			System.out.println("Account not exist for account number:" + id);
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Account not exist for account number");
			accountBalance.setText(String.format("$%.2f", 0.0));
		}
	}

	public void closeAccountFunction() {
		GridPane closeaccountgridpane = new GridPane();
		closeaccountgridpane.setAlignment(Pos.CENTER);
		closeaccountgridpane.setHgap(10);
		closeaccountgridpane.setVgap(10);
		closeaccountgridpane.setPadding(new Insets(20, 15, 20, 15));
		closeaccountgridpane.add(new Label("Account Number:"), 0, 0);
		closeaccountgridpane.add(accountNumber, 1, 0);
		closeaccountgridpane.add(new Label("Account  Balance:"), 0, 1);
		closeaccountgridpane.add(accountBalance, 1, 1);
		closeaccountgridpane.add(new Label("Password:"), 0, 2);
		closeaccountgridpane.add(pass, 1, 2);
		accountNumber.clear();
		accountBalance.clear();
		pass.clear();
		Button button;
		Button button1;
		
		button = new Button("Close Account");
		button3 = new Button("Confirm");
		button1 = new Button("Cancel");
		closeaccountgridpane.add(button, 0, 3);
		closeaccountgridpane.add(button3, 1, 3);
		closeaccountgridpane.add(button1, 2, 3);
		closeaccountgridpane.add(message, 0, 6);
		borderpane.setCenter(closeaccountgridpane);
		button3.setDisable(true);
		accountNumber.setEditable(true);
		accountBalance.setEditable(false);
		accountBalance.setEditable(false);
		button.setOnAction(e -> calculatecloseaccountBalance());
		button1.setOnAction(e -> cancelcloseaccount());
		button3.setOnAction(e -> cancelcloseconfirmaccount());
		
	}

	private void cancelcloseaccount() {
		accountNumber.clear();
		accountBalance.clear();
		pass.clear();
		button3.setDisable(true);
		message.setText(null);
	}

	private void calculatecloseaccountBalance() {
		int id = Integer.parseInt(accountNumber.getText());
		String pass1= String.valueOf(pass.getText());
			if (accountMap.containsKey(id)) {
				Account objAccount = accountMap.get(id);
				if(objAccount.checkPass(pass1))
				{
					objAccount.getBalance();
					accountBalance.setText(String.format("$%.2f", objAccount.getBalance()));
					Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
					message.setFont(fontn);
					button3.setDisable(false);
					message.setText("Please click the Confirm Button to close Account");
			}
			else{
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Incorrect password. Try again.");
			}
		}else{
			System.out.println("Account not exist for account number:" + id);
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Account not exist for account number");
			accountBalance.setText(String.format("$%.2f", 0.0));

		}
		
	}

	private void cancelcloseconfirmaccount() {
		int id = Integer.parseInt(accountNumber.getText());
		String pass1= String.valueOf(pass.getText());
		if (accountMap.containsKey(id)) {
			Account objAccount = accountMap.get(id);
			if(objAccount.checkPass(pass1))
			{
				objAccount.getBalance();
			accountMap.remove(id);
			System.out.println("Your Account is closed");
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Your Account is closed");
			}else{
				Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Incorrect password. Try again.");
			}
		}  else {
			System.out.println("Account not exist for account number:" + id);
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Account not exist for account number");
			accountBalance.setText(String.format("$%.2f", 0.0));
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
