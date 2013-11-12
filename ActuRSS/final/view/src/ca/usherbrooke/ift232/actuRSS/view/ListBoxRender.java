package ca.usherbrooke.ift232.actuRSS.view;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import ca.usherbrooke.ift232.actuRSS.model.News;

public class ListBoxRender extends JLabel implements ListCellRenderer{
	
	ImageIcon icon;
	News news;
	public ListBoxRender(ImageIcon im, News n){
		icon = im;
		news = n;
		setOpaque(true);
		setAlignmentX(LEFT_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
	}
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int arg2, boolean isSelected, boolean arg4) {
		
		//always valid, so just use the value.)
        int selectedIndex = arg2;//((Integer)value).intValue();

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        if (icon != null) {
        	setIcon(icon);
            setText(news.getTitle());
            setFont(list.getFont());
        } else {
        	setText(news.getTitle());
            setFont(list.getFont());
        }
        
        setText(news.getContents());

        return this;
    }

}
