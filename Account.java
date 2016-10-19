package FinalJavaProj;

public class Account {
	private double annualInterestRate = 3.0;
	private int Id;
	private double balance;

	Account() {
	}

	Account(int id, double bal, double nannualInterestRate) {
		Id = id;
		balance = bal;
		annualInterestRate = nannualInterestRate;
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

	public void setAnnualInterestRate(double AnnualInterestRate) {
		annualInterestRate = AnnualInterestRate;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public double getMonthlyInterestRate() {
		return (annualInterestRate / 12);
	}

	public double getMonthlyInterest() {
		double monthlyInterest = Math.pow(1 + annualInterestRate, 1.0 / 12);
		return balance * monthlyInterest;
	}

	public void withdraw(double amount) {
		balance = balance - amount;
	}

	public void deposit(double amount) {
		balance = balance + amount;
	}
}
