package ca.usherbrooke.ift232.actuRSS.view.actulist;

import javax.swing.event.ChangeEvent;

import ca.usherbrooke.ift232.actuRSS.News;


public class ActuSelectedEvent extends ChangeEvent
{

	private News _selectedActu;
	
	/**Permet de sélectionner une actualité
	 * @param objSource
	 * @param selectedActu
	 */
	public ActuSelectedEvent(Object objSource, News selectedActu) 
	{
		super(objSource);
	
		_selectedActu = selectedActu;
	}
	
	/**Permet d'obtenir l'actu séléctionné
	 * @return this._selectedActu
	 */
	public News getSelectedActu()
	{
		return this._selectedActu;
	}
	
}