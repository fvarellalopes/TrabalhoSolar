package br.edu.utfpr.fernandolopes.solar;

import javax.servlet.annotation.WebServlet;

import br.edu.utfpr.fernandolopes.solar.ds.SolarJPAContainer;
import br.edu.utfpr.fernandolopes.solar.entity.Pergunta;
import br.edu.utfpr.fernandolopes.solar.ui.PerguntaEdit;
import br.edu.utfpr.fernandolopes.solar.ui.PerguntaView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

@SuppressWarnings("serial")
@Theme("solar")
public class SolarUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = SolarUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		SolarJPAContainer<Pergunta> container = new SolarJPAContainer<Pergunta>(
				Pergunta.class);
		PerguntaView perg = new PerguntaView(container);

		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		setContent(content);

		VerticalLayout vertical = putAllOnVertical(perg,
				buildBarButtons(container));
		content.addComponent(vertical);
		content.setComponentAlignment(vertical, Alignment.MIDDLE_CENTER);

	}private VerticalLayout putAllOnVertical(Component... components) {
    	VerticalLayout vertical = new VerticalLayout();
    	for (Component c: components) {
    		if (c != null) {
    			vertical.addComponent(c);
        		vertical.setComponentAlignment(c, Alignment.MIDDLE_CENTER);
    		}
    	}
    	return vertical;
    }

	private HorizontalLayout buildBarButtons(
			final SolarJPAContainer<Pergunta> datasource) {
		Button bIncluir = new Button("Incluir");
		bIncluir.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				PerguntaEdit window = new PerguntaEdit(datasource);
				window.create();
			}
		});

		Button bAtualizar = new Button("Atualizar");
		bAtualizar.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				datasource.refresh();
			}
		});

		Button[] buttons = { bIncluir, bAtualizar };
		HorizontalLayout barButton = new HorizontalLayout();
		barButton.setHeight("50");

		for (Button b : buttons) {
			b.setStyleName(Runo.BUTTON_BIG);
			barButton.addComponent(b);
			barButton.setComponentAlignment(b, Alignment.MIDDLE_CENTER);
		}

		return barButton;
	}

}