package ca.usherbrooke.ift232.actuRSS.view.actulist;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import ca.usherbrooke.ift232.actuRSS.News;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ActuRenderer extends JPanel implements ListCellRenderer<News>
{

	private static final Color SELECTED_BACKGROUND =new Color(146, 200, 230);
	private static final Color DARK_BACKGROUND = new Color(181, 181, 181);
	private static final Color BRIGHT_BACKGROUND = new Color(224, 224, 224);
	private JLabel lblTitle;
	private JLabel lblDate;
	private JLabel lblContentQuickView;
	private List<JLabel> mayBeBoldLabels = new ArrayList<JLabel>();
	
	
	/**
	 * Cr�e le panneau d'affichage
	 * @param news
	 */
	public ActuRenderer(News news) {
		setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,}));

		lblTitle = new JLabel(news.getTitle());
		add(lblTitle, "2, 4");

		lblDate = new JLabel(formatCalendar(news.getDate()));
		add(lblDate, "2, 2");

		lblContentQuickView = new JLabel(news.getContents().substring(
				0,
				(news.getContents().length() > 100) ? 100 : news.getContents()
						.length()));
		// On n'affiche que les 100 premiers caract�res

		add(lblContentQuickView, "2, 6, 27, 1");

		mayBeBoldLabels.add(lblTitle);
		//mayBeBoldLabels.add(lblDate);
		mayBeBoldLabels.add(lblContentQuickView);
		
	}

	
	/**Permet de mettre en gras
	 * @param bold
	 */
	public void setBold(boolean bold)
	{
		for(JLabel label : mayBeBoldLabels)
		{

			Font font = label.getFont();
			// same font but bold
			Font boldFont = new Font(font.getFontName(), (bold)?Font.BOLD:Font.PLAIN , font.getSize());
			label.setFont(boldFont);
		}
	}
	
	
	public ActuRenderer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends News> list,
			News value, int index, boolean isSelected, boolean cellHasFocus)
	{
		News news = (News) value;
		
		ActuRenderer result = new ActuRenderer(news);

		result.setBold(!news.isRead());
		
		if (isSelected)
			result.setBackground(SELECTED_BACKGROUND);
		else if (index %2 ==0)//Une cellule sur 2 est clair (lisibilit�)
			result.setBackground(DARK_BACKGROUND);
		else
			result.setBackground(BRIGHT_BACKGROUND);
			
		return result;
	}

	/**Permet d'obtenir une cha�ne date � partir d'une variable en format Calendar
	 * @param cal
	 * @return une date
	 */
	private String formatCalendar(Calendar cal)
	{
		Calendar actualTime = Calendar.getInstance();

		// Si les dates correspondent
		if (cal.get(Calendar.DATE) == actualTime.get(Calendar.DATE)
				&& cal.get(Calendar.MONTH) == actualTime.get(Calendar.MONTH)
				&& cal.get(Calendar.YEAR) == actualTime.get(Calendar.YEAR))
			return "" + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE);
		else
			return "" + cal.get(Calendar.DAY_OF_MONTH) + "/"
					+ cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR)
					+ " " + cal.get(Calendar.HOUR) + ":"
					+ cal.get(Calendar.MINUTE);

	}
}
