package ca.usherbrooke.ift232.actuRSS.view.actulist;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.usherbrooke.ift232.actuRSS.News;

public class ActuList extends JList implements ListSelectionListener
{
	public ActuList(List<News> news) {

		super(buildListModelNews(news));
		
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setSelectedIndex(0);
		this.addListSelectionListener( this);
		this.setVisibleRowCount(5);
		
		
	}

	public ActuList() 
	{
		
	}
	
	

	private News getSelectedNew()
	{
		return (News) this.getSelectedValue();
		
	}

	private static ListModel buildListModelNews(List<News> news) {
		final DefaultListModel listModelNews = new DefaultListModel();
		fillNews(listModelNews, news);

		return listModelNews;
	}

	public void changeNews(List<News> news)
	{
		ListModel modelNews = buildListModelNews(news);
		
		this.setModel(modelNews);
	}
	
	private static void fillNews(DefaultListModel listModel, List<News> news) {

		System.out.println(news);
		for (News element : news) 
		{
			listModel.addElement(element);
		}

	}
	
	// #region ActuSelectedEvent

		private final EventListenerList listenerList = new EventListenerList();

		public void addActuSelectedListener(
				ActuSelectedListener actuSelectedListener) {
			listenerList.add(ActuSelectedListener.class, actuSelectedListener);
		}

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
