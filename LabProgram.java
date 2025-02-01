/*******************************************************************************
*      file: LabProgram.java
*      Author: Abraham Lopez
*      Class: CS 1400.01-1 - Intro Programming and Problem Solving
*      
*      Assignment: Program 4
*      Date Last Modified: 10/29/2024
*
*      Purpose: calculate owed US taxes
*
*******************************************************************************/
import java.util.Scanner;

public class LabProgram {

	//method: calcAGI
	//purpose: calculates adjusted gross income (AGI) and returns the AGI

	public static int calcAGI(int inputWages, int inputInterest, int inputUnemployment) {
		int calcAGIwages = inputWages;
		int calcAGItaxableInterest = inputInterest;
		int calcAGIunemploymentComp = inputUnemployment;

		if(calcAGIwages < 0) {
			calcAGIwages = Math.abs(calcAGIwages);
		}
		if(calcAGItaxableInterest < 0) {
			calcAGItaxableInterest = Math.abs(calcAGItaxableInterest);
		}
		if(calcAGIunemploymentComp < 0) {
			calcAGIunemploymentComp = Math.abs(calcAGIunemploymentComp);
		}

		return calcAGIwages + calcAGItaxableInterest + calcAGIunemploymentComp;
	}

	//method: getDeduction
	//purpose: calculates deduction based on status and returns the deduction amount

	public static int getDeduction(int inputStatus) {
		int getDeductionStatus = inputStatus;
		if(getDeductionStatus == 0) {
			return 6000;
		}
		else if(getDeductionStatus == 1) {
			return 12000;
		}
		else if(getDeductionStatus == 2) {
			return 24000;
		}
		return 6000;
	}

	//method: calcTaxable
	//purpose: calculates the amount of money taxable by AGI - deduction and returns the amount taxable

	public static int calcTaxable(int inputAGI, int inputDeduction) {
		int taxable = inputAGI - inputDeduction;
		if(taxable >= 0) {
			return taxable;
		}
		else {
			return 0;
		}
	}

	//method: calcTax
	//purpose: calculates the tax based on status and taxable income and returns the tax

	public static int calcTax(int statuss, int taxableIncomess) {
		int calcTaxStatus = statuss;
		int calcTaxTaxableIncome = taxableIncomess;
		double taxableIncome;
		int taxableIncomeInt;
		if((calcTaxStatus == 0) || (calcTaxStatus == 1)) {
			if((calcTaxTaxableIncome >= 0) && (calcTaxTaxableIncome <= 10000)) {
				taxableIncome = Math.round(calcTaxTaxableIncome * 0.10);
			}
			else if((calcTaxTaxableIncome >= 10001) && (calcTaxTaxableIncome <= 40000)) {
				taxableIncome = 1000 + Math.round((calcTaxTaxableIncome - 10000) * 0.12);
			}
			else if((calcTaxTaxableIncome >= 40001) && (calcTaxTaxableIncome <= 85000)) {
				taxableIncome = 4600 + Math.round((calcTaxTaxableIncome - 40000) * 0.22);
			}
			else {
				taxableIncome = 14500 + Math.round((calcTaxTaxableIncome - 85000) * 0.24);
			}
		}
		else {
			if((calcTaxTaxableIncome >= 0) && (calcTaxTaxableIncome <= 20000)) {
				taxableIncome = Math.round(calcTaxTaxableIncome * 0.10);
			}
			else if((calcTaxTaxableIncome >= 20001) && (calcTaxTaxableIncome <= 80000)) {
				taxableIncome = 2000 + Math.round((calcTaxTaxableIncome - 20000) * 0.12);
			}
			else {
				taxableIncome = 9200 + Math.round((calcTaxTaxableIncome - 80000) * 0.22);
			}
		}
		taxableIncomeInt = (int)taxableIncome;
		return taxableIncomeInt;
	}

	//method: calcTaxDue
	//purpose: calculates the tax due by tax - amount withheld and returns the tax due

	public static int calcTaxDue(int taxDue, int withHeld) {
		if(withHeld < 0) {
			withHeld = 0;
		}
		return taxDue - withHeld;
	}

	//method: main
	//purpose: gets tax information input and outputs tax information

	public static void main(String[] args) {

		Scanner scnr = new Scanner(System.in);
		System.out.println("Please enter wages: ");
		int wages = scnr.nextInt();
		System.out.println("Please add taxable interest: ");
		int taxableInterest = scnr.nextInt();
		System.out.println("Please add unemployment compensation: ");
		int unemploymentComp = scnr.nextInt();
		System.out.println("Please add status: ");
		int status = scnr.nextInt();

		int AGI = calcAGI(wages, taxableInterest, unemploymentComp);
		System.out.printf("AGI: $%,d\n", AGI);

		int deduction = getDeduction(status);
		System.out.printf("Deduction: $%,d\n", deduction);

		int taxable = calcTaxable(AGI, deduction);
		System.out.printf("Taxable income: $%,d\n", taxable);

		int tax = calcTax(status, taxable);
		System.out.printf("Federal tax: $%,d\n", tax);

		int taxesWithheld = scnr.nextInt();
		int taxDue = calcTaxDue(tax, taxesWithheld);
		System.out.printf("Tax due: $%,d\n", taxDue);
		
	}

}
