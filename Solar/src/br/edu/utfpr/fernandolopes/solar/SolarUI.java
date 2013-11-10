package br.edu.utfpr.fernandolopes.solar;

import java.io.Serializable;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("solar")
public class SolarUI extends UI {
	public class Bean implements Serializable { 
	    String name; 
	    double energy; // Energy content in kJ/100g 
	     
	    public Bean(String name, double energy) { 
	        this.name   = name; 
	        this.energy = energy; 
	    } 
	     
	    public String getName() { 
	        return name; 
	    } 
	     
	    public void setName(String name) { 
	        this.name = name; 
	    } 
	     
	    public double getEnergy() { 
	        return energy; 
	    } 
	     
	    public void setEnergy(double energy) { 
	        this.energy = energy; 
	    } 
	} 
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = SolarUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

	
		BeanItemContainer<Bean> beans = 
		    new BeanItemContainer<Bean>(Bean.class); 
		     
		// Add some beans to it 
		beans.addBean(new Bean("Mung bean",   1452.0)); 
		beans.addBean(new Bean("Chickpea",    686.0)); 
		beans.addBean(new Bean("Lentil",      1477.0)); 
		beans.addBean(new Bean("Common bean", 129.0)); 
		beans.addBean(new Bean("Soybean",     1866.0)); 
		 
		// Bind a table to it 
		Table table = new Table("Beans of All Sorts", beans); 
		table.setEditable(true);
		layout.addComponent(table); 
	}

}