import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CardLayoutDemo {

    public static void main(String[] args) {

    	 JLabel label3 = new JLabel("Media Outlets:");
         Object[] value = new String[]{ "Selected All","Al-Jazeera" , "BBC News" ,"Daily Mail" ,"Fox News","New York Daily News", "New York Times","the Guardian","Wall Street Journal"};  
         Object[] defaultValue = new String[]{ "Selected All" }; 
         label3.setVisible(true);
         MultiComboBox mulit = new MultiComboBox(value, defaultValue);
    }
}