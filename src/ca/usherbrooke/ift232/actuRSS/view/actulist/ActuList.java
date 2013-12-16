package ca.usherbrooke.ift232.actuRSS.view.actulist;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.view.filter.AllFilter;
import ca.usherbrooke.ift232.actuRSS.view.filter.Filter;
import ca.usherbrooke.ift232.actuRSS.view.sorter.DefaultSorter;
import ca.usherbrooke.ift232.actuRSS.view.sorter.Sorter;

public class ActuList extends JList implements ListSelectionListener
{	

	private Sorter _sorter;
	
	/**Constructeur de la liste d'actualit�
	 * @param news
	 */
	public ActuList(List<News> news) {

		super(buildListModelNews(news, new AllFilter(), new DefaultSorter()));


		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setSelectedIndex(0);
		this.addListSelectionListener( this);
		this.setVisibleRowCount(5);

		this.setCellRenderer(new ActuRenderer());

	}

	public ActuList() 
	{

		this(new ArrayList<News>());
	}



	public News getSelectedNew()
	{
		return (News) this.getSelectedValue();

	}

	private static ListModel buildListModelNews(List<News> news, Filter filtre, Sorter sorter) {
		final DefaultListModel listModelNews = new DefaultListModel();
		fillNews(listModelNews, news, filtre, sorter);

		return listModelNews;
	}

	/**Change l'�tat d'une news
	 * @param news
	 * @param state
	 */
	public void changeNews(List<News> news, Filter filter, Sorter sorter)
	{
		ListModel modelNews = buildListModelNews(news, filter, sorter);

		this.setModel(modelNews);
	}

	/**Permet d'initialiser les �tats de toutes les news de la liste
	 * @param listModel
	 * @param news
	 * @param state
	 */
	private static void fillNews(DefaultListModel listModel, List<News> news,Filter filter, Sorter sorter) {

		
		
		listModel.clear();
		
		ArrayList<News> newNewsList = new ArrayList<News>(filter.filtrer(news));
		
		
		
		for(News element : sorter.sort(newNewsList))
		{
			listModel.addElement(element);
		}
		
		
		/*for (News element : news) 
		{
			
			if(state.equals("Favorite")){
				if(element.isFavorite()){
					//System.out.println(element);
					listModel.addElement(element);
				}
			}else if(state.equals("Read")){
				if(element.isRead()){
					listModel.addElement(element);
					//System.out.println(element);
				}
			}else if(state.equals("Not Read")){
				if(element.isRead() == false){
					listModel.addElement(element);
					//System.out.println(element);
				}
			}else{
				listModel.addElement(element);
				//System.out.println(element);
			}	
		}*/

	}


	// #region ActuSelectedEvent

	private final EventListenerList listenerList = new EventListenerList();

	/**Permet d'ajouter une news � l'instance de suivi des news
	 * @param actuSelectedListener
	 */
	public void addActuSelectedListener(
			ActuSelectedListener actuSelectedListener) {
		listenerList.add(ActuSelectedListener.class, actuSelectedListener);
	}

	/**Permet de supprimer une news � l'instance de suivi des news
	 * @param l
	 */
	public void removeActuSelectedListener(ActuSelectedListener l) {
		listenerList.remove(ActuSelectedListener.class, l);
	}

	protected void fireActuSelectedEvent(ActuSelectedEvent event) {
		for (ActuSelectedListener l : listenerList
				.getListeners(ActuSelectedListener.class)) {
			l.onActuSelected(event);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		fireActuSelectedEvent(new ActuSelectedEvent(this, (News) this.getSelectedValue()));

	}

	// #endregion


}
