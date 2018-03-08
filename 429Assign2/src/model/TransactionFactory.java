package model;

import userinterface.InsertNewBookView;

public class TransactionFactory {

	public static Transaction createTransaction(String transType) throws Exception {
		
		Transaction retValue = null;
		

		// if (transType.equals("InputNewBook") == true)
		// {
		// retValue = new InsertNewBookView();
		// }
		// else
		// if (transType.equals("") == true)
		// {
		// retValue = new WithdrawTransaction();
		// }
		
		System.out.println(retValue);
		return retValue;

	}
}
