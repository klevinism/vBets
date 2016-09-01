package controller;

import model.DeleteAdViewObject;
import model.MainViewInputObject;
import model.globals.Globals;

public class DeleteAdViewController extends Controller {
	private DeleteAdViewObject InputObject;
	
	public DeleteAdViewController(DeleteAdViewObject inputObject){
		InputObject = inputObject;
	}

	@Override
	public void setActionPerformed(String actionPerformer) {
		// TODO Auto-generated method stub
		if(actionPerformer.equals(Globals.actions.DeleteView_DeleteAd)){
			
		}else{
			
		}
	}
	

	
}
