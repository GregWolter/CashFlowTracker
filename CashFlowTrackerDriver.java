// Creates a GUI for user to input income and expense items
// that are typical for a rental property business.
// The GUI displays various income and expense totals
// as labeled.

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class CashFlowTrackerDriver extends JFrame implements ActionListener {

	// Instance Variables
	private JLabel income, expense, incomeType, expenseType, annualIncomeLabel, annualExpenseLabel, monthIncomeLabel,
			monthExpenseLabel, allMortgageLabel, allPropertyTaxLabel, annualCashFlowLabel, monthCashFlowLabel,
			totalMortgageLabel, totalPropertyTaxLabel;
	private JTextField incomeTF, expenseTF, annualCashFlowTF, monthCashFlowTF, totalMortgageTF, totalPropertyTaxTF;
	private JButton incomeButton, expenseButton, viewReportButton;
	String[] incomeMenu = { "Rent", "Fees", "Utility_Rebate", "Claims" };
	String[] expenseMenu = { "Property_Tax", "Mortgage", "Insurance", "Utilities", "Repair" };
	private JComboBox incomeBox, expenseBox;
	private JTextArea annualIncomeDisplay, annualExpenseDisplay, currentMonthIncomeDisplay, currentMonthExpenseDisplay,
			allMortgageDisplay, allPropertyTaxDisplay;
	private double expenseEntry, incomeEntry;
	LocalDate timestamp = LocalDate.now();
	private JScrollPane scrollPane1, scrollPane2, scrollPane3, scrollPane4, scrollPane5, scrollPane6;

	public CashFlowTrackerDriver() {

		// Create title
		JFrame frame = new JFrame("Rental Property Cash Flow Tracker");
		frame.setLayout(null);

		// Creates labels
		income = new JLabel("Enter income amount:");
		income.setBounds(30, 35, 150, 20);
		expense = new JLabel("Enter expense amount:");
		expense.setBounds(30, 90, 150, 20);
		incomeType = new JLabel("Select Description:");
		incomeType.setBounds(400, 35, 150, 20);
		expenseType = new JLabel("Select Description:");
		expenseType.setBounds(400, 90, 150, 20);
		annualIncomeLabel = new JLabel("Annual Income");
		annualIncomeLabel.setBounds(110, 220, 120, 20);
		annualExpenseLabel = new JLabel("Annual Expense");
		annualExpenseLabel.setBounds(390, 220, 120, 20);
		monthIncomeLabel = new JLabel("Current Month Income");
		monthIncomeLabel.setBounds(650, 220, 200, 20);
		monthExpenseLabel = new JLabel("Current Month Expense");
		monthExpenseLabel.setBounds(925, 220, 200, 20);
		allMortgageLabel = new JLabel("All Mortgage Payments");
		allMortgageLabel.setBounds(1205, 220, 250, 20);
		allPropertyTaxLabel = new JLabel("All Property Tax Payments");
		allPropertyTaxLabel.setBounds(1480, 220, 250, 20);
		annualCashFlowLabel = new JLabel("Net Cash Flow for Year");
		annualCashFlowLabel.setBounds(150, 750, 250, 20);
		monthCashFlowLabel = new JLabel("Net Cash Flow for Month");
		monthCashFlowLabel.setBounds(700, 750, 250, 20);
		totalMortgageLabel = new JLabel("Total Mortgage");
		totalMortgageLabel.setBounds(1175, 750, 250, 20);
		totalPropertyTaxLabel = new JLabel("Total Property Tax");
		totalPropertyTaxLabel.setBounds(1450, 750, 150, 20);

		// Create text fields
		incomeTF = new JTextField();
		incomeTF.setBounds(160, 30, 60, 30);
		expenseTF = new JTextField();
		expenseTF.setBounds(165, 85, 60, 30);
		annualCashFlowTF = new JTextField();
		annualCashFlowTF.setBounds(310, 750, 125, 20);
		annualCashFlowTF.setEnabled(false);
		annualCashFlowTF.setDisabledTextColor(Color.BLACK);
		monthCashFlowTF = new JTextField();
		monthCashFlowTF.setBounds(870, 750, 125, 20);
		monthCashFlowTF.setEnabled(false);
		monthCashFlowTF.setDisabledTextColor(Color.BLACK);
		totalMortgageTF = new JTextField();
		totalMortgageTF.setBounds(1275, 750, 100, 20);
		totalMortgageTF.setEnabled(false);
		totalMortgageTF.setDisabledTextColor(Color.BLACK);
		totalPropertyTaxTF = new JTextField();
		totalPropertyTaxTF.setBounds(1560, 750, 100, 20);
		totalPropertyTaxTF.setEnabled(false);
		totalPropertyTaxTF.setDisabledTextColor(Color.BLACK);

		// Creates buttons
		incomeButton = new JButton("Add Income");
		incomeButton.addActionListener(this);
		incomeButton.setBounds(250, 35, 125, 20);
		expenseButton = new JButton("Add Expense");
		expenseButton.addActionListener(this);
		expenseButton.setBounds(250, 90, 125, 20);
		viewReportButton = new JButton("View Reports");
		viewReportButton.addActionListener(this);
		viewReportButton.setBounds(795, 190, 120, 20);

		// Creates text area
		annualIncomeDisplay = new JTextArea();
		annualIncomeDisplay.setEnabled(false);
		annualIncomeDisplay.setDisabledTextColor(Color.BLACK);
		scrollPane1 = new JScrollPane(annualIncomeDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setBounds(30, 250, 260, 470);
		annualExpenseDisplay = new JTextArea();
		annualExpenseDisplay.setEnabled(false);
		annualExpenseDisplay.setDisabledTextColor(Color.BLACK);
		scrollPane2 = new JScrollPane(annualExpenseDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane2.setBounds(310, 250, 260, 470);
		currentMonthIncomeDisplay = new JTextArea();
		currentMonthIncomeDisplay.setEnabled(false);
		currentMonthIncomeDisplay.setDisabledTextColor(Color.BLACK);
		scrollPane3 = new JScrollPane(currentMonthIncomeDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane3.setBounds(590, 250, 260, 470);
		currentMonthExpenseDisplay = new JTextArea();
		currentMonthExpenseDisplay.setEnabled(false);
		currentMonthExpenseDisplay.setDisabledTextColor(Color.BLACK);
		scrollPane4 = new JScrollPane(currentMonthExpenseDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane4.setBounds(870, 250, 260, 470);
		allMortgageDisplay = new JTextArea();
		allMortgageDisplay.setEnabled(false);
		allMortgageDisplay.setDisabledTextColor(Color.BLACK);
		scrollPane5 = new JScrollPane(allMortgageDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane5.setBounds(1150, 250, 260, 470);
		allPropertyTaxDisplay = new JTextArea();
		allPropertyTaxDisplay.setEnabled(false);
		allPropertyTaxDisplay.setDisabledTextColor(Color.BLACK);
		scrollPane6 = new JScrollPane(allPropertyTaxDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane6.setBounds(1430, 250, 260, 470);

		// Creates Combo Box
		incomeBox = new JComboBox(incomeMenu);
		incomeBox.addActionListener(this);
		incomeBox.setBounds(530, 30, 150, 30);
		expenseBox = new JComboBox(expenseMenu);
		expenseBox.addActionListener(this);
		expenseBox.setBounds(530, 85, 150, 30);

		// Adds component to frame
		frame.getContentPane().add(scrollPane1);
		frame.getContentPane().add(scrollPane2);
		frame.getContentPane().add(scrollPane3);
		frame.getContentPane().add(scrollPane4);
		frame.getContentPane().add(scrollPane5);
		frame.getContentPane().add(scrollPane6);
		frame.add(income);
		frame.add(incomeTF);
		frame.add(expense);
		frame.add(expenseTF);
		frame.add(incomeButton);
		frame.add(expenseButton);
		frame.add(viewReportButton);
		frame.add(incomeBox);
		frame.add(incomeType);
		frame.add(expenseType);
		frame.add(expenseBox);
		frame.add(annualIncomeLabel);
		frame.add(annualExpenseLabel);
		frame.add(monthIncomeLabel);
		frame.add(monthExpenseLabel);
		frame.add(allMortgageLabel);
		frame.add(allPropertyTaxLabel);
		frame.add(annualCashFlowLabel);
		frame.add(annualCashFlowTF);
		frame.add(monthCashFlowLabel);
		frame.add(monthCashFlowTF);
		frame.add(totalMortgageLabel);
		frame.add(totalMortgageTF);
		frame.add(totalPropertyTaxLabel);
		frame.add(totalPropertyTaxTF);

		frame.setSize(1725, 900);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	// Returns the total amounts of a file for the current year
	public double sumFileAmounts(File filename) {
		double totalAmount = 0;
		Scanner myReader = null;
		try {
			myReader = new Scanner(filename);
			// Loops through the file line by line
			while (myReader.hasNextLine()) {
				String entryLine = myReader.nextLine();
				String[] tokens = entryLine.split("\\s+");
				double entryAmount = Double.parseDouble(tokens[0]);
				String entryDate = tokens[2];
				// Checks if the date in the line of the file is the current year
				if (entryDate.substring(0, 4).equals(timestamp.toString().substring(0, 4))) {
					totalAmount = totalAmount + entryAmount;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (myReader != null)
				myReader.close();
		}
		return totalAmount;
	}

	// Returns the total amounts of a file for the current month
	public double sumMonthlyAmount(File filename) {
		double monthlyTotal = 0;
		Scanner myReader = null;
		try {
			myReader = new Scanner(filename);
			// Loops through the file line by line
			while (myReader.hasNextLine()) {
				String entryLine = myReader.nextLine();
				String[] tokens = entryLine.split("\\s+");
				String entryDate = tokens[2];
				double entryAmount = Double.parseDouble(tokens[0]);
				// Checks if the date in the line of the file is the current month and year
				if (entryDate.substring(0, 7).equals(timestamp.toString().substring(0, 7))) {
					monthlyTotal = monthlyTotal + entryAmount;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (myReader != null)
				myReader.close();
		}
		return monthlyTotal;
	}

	// Displays all entries of the current month to the currentMonthIncomeDisplay
	public void printMonthlyIncome(File filename) {
		currentMonthIncomeDisplay.setText("");
		Scanner myReader = null;
		try {
			myReader = new Scanner(filename);
			// Loops through the file line by line
			while (myReader.hasNextLine()) {
				String entryLine = myReader.nextLine();
				String[] tokens = entryLine.split("\\s+");
				String entryDate = tokens[2];
				// Checks if the date in the line of the file is the current month and year
				if (entryDate.substring(0, 7).equals(timestamp.toString().substring(0, 7))) {
					currentMonthIncomeDisplay.append(tokens[0] + "\t" + tokens[1] + "\t" + tokens[2] + "\n");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (myReader != null)
				myReader.close();
		}

	}

	// Displays all entries of the current month to the currentMonthExpenseDisplay
	public void printMonthlyExpense(File filename) {
		currentMonthExpenseDisplay.setText("");
		Scanner myReader = null;
		try {
			myReader = new Scanner(filename);
			// Loops through the file line by line
			while (myReader.hasNextLine()) {
				String entryLine = myReader.nextLine();
				String[] tokens = entryLine.split("\\s+");
				String entryDate = tokens[2];
				// Checks if the date in the line of the file is the current month and year
				if (entryDate.substring(0, 7).equals(timestamp.toString().substring(0, 7))) {
					currentMonthExpenseDisplay.append(tokens[0] + "\t" + tokens[1] + "\t" + tokens[2] + "\n");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (myReader != null)
				myReader.close();
		}
	}

	// Displays all entries of the current year to the JTextArea passed as a
	// parameter
	public void printAnnualAmounts(File filename, JTextArea jTextArea) {
		jTextArea.setText("");
		Scanner myReader = null;
		try {
			myReader = new Scanner(filename);
			// Loops through the file line by line
			while (myReader.hasNextLine()) {
				String entryLine = myReader.nextLine();
				String[] tokens = entryLine.split("\\s+");
				String entryDate = tokens[2];
				// Checks if the date in the line of the file is the current year
				if (entryDate.substring(0, 4).equals(timestamp.toString().substring(0, 4))) {
					jTextArea.append(tokens[0] + "\t" + tokens[1] + "\t" + tokens[2] + "\n");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (myReader != null)
				myReader.close();
		}
	}

	// Displays all entries of the current year with the description parameter
	// passed to the JTextArea passed as a parameter
	public void printAllOfOneExpense(File filename, String description, JTextArea jTextArea) {
		jTextArea.setText("");
		Scanner myReader = null;
		try {
			myReader = new Scanner(filename);
			// Loops through the file line by line
			while (myReader.hasNextLine()) {
				String entryLine = myReader.nextLine();
				String[] tokens = entryLine.split("\\s+");
				String entryDescription = tokens[1];
				String entryDate = tokens[2];
				// Checks if the description in the line of the file is the parameter passed and
				// if the date in the line of the file is the current year
				if (entryDescription.equals(description)
						&& entryDate.substring(0, 4).equals(timestamp.toString().substring(0, 4))) {
					jTextArea.append(tokens[0] + "\t" + tokens[1] + "\t" + tokens[2] + "\n");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (myReader != null)
				myReader.close();
		}
	}

	// Returns the total amount of the description passed that is within the current
	// year
	public double sumAllOfOneExpense(File filename, String description) {
		double sumOneExpense = 0;
		Scanner myReader = null;
		try {
			// Loops through the file line by line
			myReader = new Scanner(filename);
			while (myReader.hasNextLine()) {
				String entryLine = myReader.nextLine();
				String[] tokens = entryLine.split("\\s+");
				String entryDescription = tokens[1];
				String entryDate = tokens[2];
				double entryAmount = Double.parseDouble(tokens[0]);
				// Checks if the description in the line of the file is the parameter passed and
				// if the date in the line of the file is the current year
				if (entryDescription.equals(description)
						&& entryDate.substring(0, 4).equals(timestamp.toString().substring(0, 4))) {
					sumOneExpense = sumOneExpense + entryAmount;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (myReader != null)
				myReader.close();
		}
		return sumOneExpense;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton clickedButton = (JButton) e.getSource();
			DecimalFormat decimalFormat = new DecimalFormat("#.00");
			// Handles when the incomeButton is clicked
			if (clickedButton == incomeButton) {
				try {
					incomeEntry = Double.parseDouble(incomeTF.getText());
					// Handles range of entry
					if (incomeEntry > 20000 || incomeEntry < -20000) {
						JOptionPane.showMessageDialog(null,
								"Error, Number is greater than 20,000 or less than -20,000. Please try again.", "alert",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					// https://www.java67.com/2015/07/how-to-append-text-to-existing-file-in-java-example.html#:~:text=You%20can%20append%20text%20into%20an%20existing%20file,new%20content%20at%20the%20end%20of%20the%20file.
					FileWriter fileWriter = null;
					BufferedWriter bufferedWriter = null;
					PrintWriter printWriter = null;

					try {
						fileWriter = new FileWriter("income_report.txt", true);
						bufferedWriter = new BufferedWriter(fileWriter);
						printWriter = new PrintWriter(bufferedWriter);
						// Adds the entry to the income_report.txt file
						printWriter.println(decimalFormat.format(incomeEntry) + "\t" + incomeBox.getSelectedItem()
								+ "\t" + timestamp);
						printWriter.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						try {
							printWriter.close();
							bufferedWriter.close();
							fileWriter.close();
						} catch (IOException io) {

						}
					}
					// Handles if entry was not a number
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Error, not a number. Please try again.", "alert",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			// Handles when the expenseButton is clicked
			if (clickedButton == expenseButton) {
				try {
					expenseEntry = Double.parseDouble(expenseTF.getText());
					// Handles range of entry
					if (expenseEntry > 20000 || expenseEntry < -20000) {
						JOptionPane.showMessageDialog(null,
								"Error, Number is greater than 20,000 or less than -20,000. Please try again.", "alert",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					FileWriter fileWriter = null;
					BufferedWriter bufferedWriter = null;
					PrintWriter printWriter = null;

					try {
						fileWriter = new FileWriter("expense_report.txt", true);
						bufferedWriter = new BufferedWriter(fileWriter);
						printWriter = new PrintWriter(bufferedWriter);
						// Adds entry to expense_report.txt file
						printWriter.println(decimalFormat.format(expenseEntry) + "\t" + expenseBox.getSelectedItem()
								+ "\t" + timestamp);
						printWriter.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						try {
							printWriter.close();
							bufferedWriter.close();
							fileWriter.close();
						} catch (IOException io) {

						}
					}
					// Handles if entry was not a number
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Error, not a number. Please try again.", "alert",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			// Handles when the viewReportButton is clicked
			if (clickedButton == viewReportButton) {
				File incomeFile = new File("income_report.txt");
				printMonthlyIncome(incomeFile);
				File expenseFile = new File("expense_report.txt");
				printMonthlyExpense(expenseFile);
				printAllOfOneExpense(expenseFile, "Property_Tax", allPropertyTaxDisplay);
				printAllOfOneExpense(expenseFile, "Mortgage", allMortgageDisplay);
				printAnnualAmounts(incomeFile, annualIncomeDisplay);
				printAnnualAmounts(expenseFile, annualExpenseDisplay);
				double annualCashFlow;
				// Calculates annual cashflow
				annualCashFlow = sumFileAmounts(incomeFile) - sumFileAmounts(expenseFile);
				annualCashFlowTF.setText("" + decimalFormat.format(annualCashFlow));
				monthCashFlowTF.setText(
						"" + decimalFormat.format(sumMonthlyAmount(incomeFile) - sumMonthlyAmount(expenseFile)));
				totalMortgageTF.setText("" + decimalFormat.format(sumAllOfOneExpense(expenseFile, "Mortgage")));
				totalPropertyTaxTF.setText("" + decimalFormat.format(sumAllOfOneExpense(expenseFile, "Property_Tax")));
			}
		}
	}

	public static void main(String[] args) {
		CashFlowTrackerDriver cash = new CashFlowTrackerDriver();
	}

}