package ca.usherbrooke.ift232.actuRSS.view.actulist;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;

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

	/**
	 * Create the panel.
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
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel = new JLabel(news.getAuthor());
		add(lblNewLabel, "2, 2");

		JLabel lblNewLabel_1 = new JLabel(formatCalendar(news.getDate()));
		add(lblNewLabel_1, "28, 2");

		JLabel lblNewLabel_2 = new JLabel(news.getContents().substring(
				0,
				(news.getContents().length() > 100) ? 100 : news.getContents()
						.length()));
		// On n'affiche que les 100 premiers caractères

		add(lblNewLabel_2, "2, 4, 27, 1");

	}

	public ActuRenderer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.add(new ActuRenderer(new News("Plop", "ertyuiiuytre",
				"rtyuioiuy tr", Calendar.getInstance(),
				"rtiytrt  yuiuytrertyu  ytrbntr ertyu rty e", true, true)));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends News> list,
			News value, int index, boolean isSelected, boolean cellHasFocus)
	{
		ActuRenderer result = new ActuRenderer((News) value);

		if (isSelected)
			result.setBackground(SELECTED_BACKGROUND);
		else if (index %2 ==0)//Une cellule sur 2 est clair (lisibilité)
			result.setBackground(DARK_BACKGROUND);
		else
			result.setBackground(BRIGHT_BACKGROUND);
			
		return result;
	}

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
