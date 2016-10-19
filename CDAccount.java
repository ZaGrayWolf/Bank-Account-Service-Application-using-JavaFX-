package FinalJavaProj;

import java.util.ArrayList;

import java.math.*;

public class CDAccount extends Account {
	private int duration;
	private double CDAnnualInterestRate = 0.0;

	public CDAccount() {
	}

	public CDAccount(int duration) {
		this.duration = duration;
	}

	public CDAccount(int Id, double balance, int duration, double CDAnnualInterestRate) {
		this.duration = duration;
		setId(Id);
		setBalance(balance);
		this.CDAnnualInterestRate = CDAnnualInterestRate;
		
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getCDAnnualInterestRate() {
		return CDAnnualInterestRate;
	}

	public void setCDAnnualInterestRate(double CDAnnualInterestRate) {
		this.CDAnnualInterestRate = CDAnnualInterestRate;
	}

	// override method
	public double getMonthlyInterest() {
		double monthlyInterest = ((getCDAnnualInterestRate() / 1200) * getBalance());
		return monthlyInterest;
	}
}
