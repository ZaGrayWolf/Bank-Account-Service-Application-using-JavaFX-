package FinalJavaProj;

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

public class ComboProj extends Application {
	TextField accountNumber = new TextField();
	TextField deposits = new TextField();
	TextField withdrawl = new TextField();
	TextField duration = new TextField();
	TextField annualInterestRate = new TextField();
	TextField accountBalance = new TextField();
	TextField cdaccountBalance = new TextField();
	BorderPane borderpane = new BorderPane();
	Label message = new Label();
	Label message1 = new Label();
	Map<Integer, Account> accountMap = new HashMap<Integer, Account>();
	Map<Integer, CDAccount> cdaccountMap = new HashMap<Integer, CDAccount>();

	@Override
	public void start(Stage primaryStage) {
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.setStyle("-fx-color:pink");
		ObservableList<String> items = FXCollections.observableArrayList("Open New Account", "Open CD Account",
				"Deposit", "Withdraw", "Check Balance", "Close Account");
		comboBox.getItems().addAll(items);
		BorderPane paneforcombo = new BorderPane();
		Label lbl = new Label("Select A Service: ");
		Font fontn = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16);
		lbl.setFont(fontn);
		lbl.setTextFill(Color.BLACK);
		// borderpane.setStyle("-fx-background-color: linear-gradient(from 84% 44% to 5% 5%, #87CEFA, DAE6F3)");
		paneforcombo.setLeft(lbl);
		paneforcombo.setCenter(comboBox);
		borderpane.setTop(paneforcombo);
		comboBox.setPrefSize(1050, 30);
		borderpane.setPadding(new Insets(12, 14, 15, 18));
		comboBox.setOnAction(e -> {
			if (comboBox.getValue().equals("Open New Account")) {
				openAcFunction();
			} else if (comboBox.getValue().equals("Open CD Account")) {
				opencdFunction();
			} else if (comboBox.getValue().equals("Deposit")) {
				dpositFunction();
			} else if (comboBox.getValue().equals("Withdraw")) {
				withdrawFunction();
			} else if (comboBox.getValue().equals("Check Balance")) {
				checkBalanceFunction();
			} else if (comboBox.getValue().equals("Close Account")) {
				closeAccountFunction();
			}
		});
		primaryStage.setTitle("Bank Account Services");
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
		Button button;
		Button button1;
		button = new Button("Open Account");
		button1 = new Button("Cancel");
		gridpane.add(button, 0, 5);
		gridpane.add(button1, 1, 5);
		borderpane.setCenter(gridpane);
		//accountNumber.setEditable(false);
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
		System.out.println(id);
		Account account = new Account(id, Balance, interstRate);
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

	private void cancel() {
		accountNumber.clear();
		annualInterestRate.clear();
		accountBalance.clear();
		deposits.clear();
		message.setText(null);
	}
	public void opencdFunction() {
		GridPane cdgridpane = new GridPane();
		cdgridpane.setAlignment(Pos.CENTER);
		cdgridpane.setHgap(10);
		cdgridpane.setVgap(10);
		cdgridpane.setPadding(new Insets(20, 15, 20, 15));
		cdgridpane.add(new Label("Account Number:"), 0, 0);
		cdgridpane.add(accountNumber, 1, 0);
		cdgridpane.add(new Label("Deposit Amount:"), 0, 1);
		cdgridpane.add(deposits, 1, 1);
		cdgridpane.add(new Label("CD Durations(Month):"), 0, 2);
		cdgridpane.add(duration, 1, 2);
		cdgridpane.add(new Label("CDAnnual Interst Rate:"), 0, 3);
		cdgridpane.add(annualInterestRate, 1, 3);
		cdgridpane.add(new Label("Account Maturation  Balance:"), 0, 4);
		cdgridpane.add(accountBalance, 1, 4);
		Button button;
		Button button1;
		button = new Button("Open Account");
		button1 = new Button("Cancel");
		cdgridpane.add(button, 0, 6);
		cdgridpane.add(button1, 1, 6);
		cdgridpane.add(message, 0, 7);
		borderpane.setCenter(cdgridpane);
		borderpane.setCenter(cdgridpane);
		//clearText();
		button.setOnAction(e -> calculateCDBalance());
		button1.setOnAction(e -> cancelcd());
		annualInterestRate.setText(String.format("%.2f", 3.0));
	}

	private void cancelcd() {
		accountNumber.clear();
		annualInterestRate.clear();
		accountBalance.clear();
		deposits.clear();
		duration.clear();
		message.setText(null);
	}

	private void calculateCDBalance() {
		double deposit2 = Double.parseDouble(deposits.getText());
		double interstRate = (annualInterestRate.getText() != "") ? Double.parseDouble(annualInterestRate.getText())
				: 3.0;
		double Balance = 0.0;
		int id = (int) (1000 + Math.random() * 5);
		int durations = Integer.parseInt(duration.getText());
		
		System.out.println(id);
		CDAccount cdaccount = new CDAccount(id, Balance, durations, interstRate);
		Balance=(deposit2*cdaccount.getCDAnnualInterestRate()*durations)/1200 +deposit2;
		cdaccount.deposit(deposit2);
		cdaccount.setDuration(durations);
		cdaccount.setCDAnnualInterestRate(interstRate);
		cdaccountMap.put(cdaccount.getId(), cdaccount);
		cdaccount.setBalance(Balance);
		//double monthsInterestRate = (cdaccount.getDuration()/3)*0.5;
		//double monthlyInterest  = Math.pow(1 + cdaccount.getCDAnnualInterestRate()+monthsInterestRate, 1.0/12);
		accountNumber.setText(String.format("%d", cdaccount.getId()));
		accountBalance.setText(String.format("$%.2f",cdaccount.getBalance()));
		annualInterestRate.setText(String.format("$%.2f", cdaccount.getCDAnnualInterestRate()));
		duration.setText(String.format("%d", cdaccount.getDuration()));
		message.setText("CDAccount Created");
		System.out.print("CDAccount Created");
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
		message.setText(null);
		message1.setText(null);
	}

	public void calculatedepositBalance() {
		int id = Integer.parseInt(accountNumber.getText());
		Account objAccount = null;
		CDAccount cd = null;
		if (accountMap.containsKey(id)) {
			objAccount = accountMap.get(id);
			double amount = Double.parseDouble(deposits.getText());
			objAccount.deposit(amount);
			accountMap.put(objAccount.getId(), objAccount);
			accountBalance.setText(String.format("$%.2f", objAccount.getBalance()));
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Deposit Transaction Completed");
		} else if (cdaccountMap.containsKey(id)) {
			cd = cdaccountMap.get(id);
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("You can't make a Deposit to an Existing CD Account..Please Try Again.");
		} else {
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
		accountBalance.clear();
		message.setText(null);
		message1.setText(null);
	}

	private void calculatewithdrawBalance() {
		int id = Integer.parseInt(accountNumber.getText());
		Account objAccount = null;
		CDAccount cd = null;
		if (accountMap.containsKey(id)) {
			objAccount = accountMap.get(id);
			double amount = Double.parseDouble(deposits.getText());
			objAccount.withdraw(amount);
			accountMap.put(objAccount.getId(), objAccount);
			accountBalance.setText(String.format("$%.2f", objAccount.getBalance()));
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Withdraw Transaction Completed");
		} else if (cdaccountMap.containsKey(id)) {
			cd = cdaccountMap.get(id);
			message.setText("You cant withdraw from an Existing CD Account..please Try Again.");
		} else {
			System.out.println("Account not exist for account number:" + id);
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message1.setText("Invalid Account Number..Please Try Again!");
			accountBalance.setText(String.format("$%.2f", 0.0));
		}
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
		message.setText(null);
	}

	private void calculatecheckBalance() {
		int id = Integer.parseInt(accountNumber.getText());
		if (accountMap.containsKey(id)) {
			Account objAccount = accountMap.get(id);
			accountBalance.setText(String.format("$%.2f", objAccount.getBalance()));
		} else if (cdaccountMap.containsKey(id)) {
			CDAccount cd = cdaccountMap.get(id);
			accountBalance.setText(String.format("$%.2f", cd.getBalance()));
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
		Button button;
		Button button1;
		Button button2;
		button = new Button("Close Account");
		button2 = new Button("Confirm");
		button1 = new Button("Cancel");
		closeaccountgridpane.add(button, 0, 3);
		closeaccountgridpane.add(button2, 1, 3);
		closeaccountgridpane.add(button1, 2, 3);
		closeaccountgridpane.add(message, 0, 6);
		borderpane.setCenter(closeaccountgridpane);
		accountNumber.setEditable(true);
		accountBalance.setEditable(false);
		accountBalance.setEditable(false);
		button.setOnAction(e -> calculatecloseaccountBalance());
		button1.setOnAction(e -> cancelcloseaccount());
		button2.setOnAction(e -> cancelcloseconfirmaccount());
	}

	private void cancelcloseaccount() {
		accountNumber.clear();
		accountBalance.clear();
		message.setText(null);
	}

	private void calculatecloseaccountBalance() {
		int id = Integer.parseInt(accountNumber.getText());
		if (accountMap.containsKey(id)) {
			Account objAccount = accountMap.get(id);
			// objAccount.getBalance();
			accountBalance.setText(String.format("$%.2f", objAccount.getBalance()));
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Please click the Confirm Button to close Account");
		} else if (cdaccountMap.containsKey(id)) {
			CDAccount cd = cdaccountMap.get(id);
			// cd.getBalance();
			accountBalance.setText(String.format("$%.2f", cd.getBalance()));
			message.setText(
					"You may Lost the Internet if Close a CD Account Before the Mature Date.\nPlease click the Confirm Button to close Account");
		}
		Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
		message.setFont(fontn);
		System.out.println(
				"You may Lost the Internet if Close a CD Account Before the Mature Date.\nPlease click the Confirm Button to close Account");
	}

	private void cancelcloseconfirmaccount() {
		int id = Integer.parseInt(accountNumber.getText());
		if (accountMap.containsKey(id)) {
			Account objAccount = accountMap.get(id);
			objAccount.getBalance();
			accountMap.remove(id);
			System.out.println("Your Account is closed");
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Your Account is closed");
		} else if (cdaccountMap.containsKey(id)) {
			CDAccount cd = cdaccountMap.get(id);
			cd.getBalance();
			cdaccountMap.remove(id);
			System.out.println("Your Account is closed");
			Font fontn = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
			message.setFont(fontn);
			message.setText("Your Account is closed");
		} else {
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
