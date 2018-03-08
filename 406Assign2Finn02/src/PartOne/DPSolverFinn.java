//James Finn

package PartOne;

public class DPSolverFinn{
	
	public DPSolverFinn() {
		
	}//end constructor
	
	public void DPReader(String fileName) {
		
		FormulaFinn newForm = new FormulaFinn();
		newForm.read(fileName);
		solve(newForm);
		
	}//end DPReader
	
	public void solve(FormulaFinn f) {
		
		if(dp(f)) {
			System.out.println("The formula was satisfied.");
			f.printAssignment();
		}
		
		else {
			System.out.println("The formula could not be satisfied.");
		}
		
	}//end solve
	
	public boolean dp(FormulaFinn f){

               if (f.isFormulaEmpty()) {
            	   
                      return true;
               }//end if
               
               else if (f.hasEmptyClause()) {
            	   
                   return false;
               }//end else if
               
               else {
            	   
                      int var = f.firstAvailable();
                      f.assign(var, 1);
                      
                      if (dp(f)) { 
                    	  return true;
                      }
                      
                      else {

                            f.unset(var);
                            f.assign(var, -1);

                            if (dp(f)) {       	
                            	return true;
                            }
                            else {
                            	
                            	f.unset(var);
                            	
                            	return false;
                            }//end else

                      }//end else

               }//end else

       }//end dp
	
}//end class