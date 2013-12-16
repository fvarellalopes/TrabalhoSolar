package br.edu.utfpr;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import org.vaadin.sebastian.dock.Alignment;
import org.vaadin.sebastian.dock.Dock;
import org.vaadin.sebastian.dock.DockItem;
import org.vaadin.sebastian.dock.LabelPosition;
import org.vaadin.sebastian.dock.events.DockClickEvent;
import org.vaadin.sebastian.dock.events.DockClickListener;
import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardProgressBar;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;

import br.edu.utfpr.ui.composite.TelaPergunta;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI implements DockClickListener {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private HorizontalLayout layoutTopo;
	private HorizontalLayout layH;
	private Wizard wizard;
	private CssLayout layoutMenu;
	public static int numAcertos;

	@Override
	protected void init(VaadinRequest request) {

		VerticalLayout layout = new VerticalLayout();
		layH = new HorizontalLayout();
		layH.setWidth("100%");
		layH.setHeight("600px");

		layout.addComponent(topo());
		layout.addComponent(layH);

		layH.addComponent(menu());
		// layH.addComponent(perguntas());
		layH.setExpandRatio(layoutMenu, 1);
		questionario(request);

		setContent(layout);
	}

	private Component menu() {
		layoutMenu = new CssLayout();

		Dock dock = new Dock();
		// dock.addStyleName("dock_bottom");
		dock.setSize(50);
		dock.setSizeMax(75);
		dock.setAlignment(Alignment.LEFT);
		dock.setLabelPosition(LabelPosition.TOP_CENTER);
		dock.addClickListener(this);

		DockItem item = new DockItem(new ThemeResource("logout.png"),
				"Questionário");

		dock.addItem(item);

		layoutMenu.addComponent(dock);
		return layoutMenu;
	}

	private HorizontalLayout topo() {
		layoutTopo = new HorizontalLayout();
		layoutTopo.setHeight("50px");
		layoutTopo.addStyleName("borda");
		layoutTopo.addComponent(new Label("Topo"));

		return layoutTopo;
	}

	@Override
	public void dockItemClicked(DockClickEvent event) {
		if (event.getItem().getDescription().equals("Questionário")) {
			if (layH.getComponentCount() > 1) {
				layH.removeComponent(layH.getComponent(1));
			}
			layH.addComponent(wizard);
			layH.setExpandRatio(wizard, 13);
			numAcertos=0;
		}

	}

	private Component questionario(VaadinRequest req) {
		if (wizard == null) {
			
			wizard = new Wizard(){
				@Override
				public void finish() {
				
					        if (isLastStep(currentStep) && currentStep.onAdvance()) {
					            // next (finish) allowed -> fire complete event
					        	Notification n = new Notification("Acertos:" + (MyVaadinUI.this.numAcertos*25) + "%", Notification.Type.HUMANIZED_MESSAGE);
					        	n.setDelayMsec(5000);
					        	n.show(getPage());
					        }
					

				}
			};
			wizard.getNextButton().setCaption("Próxima");
			wizard.getBackButton().setCaption("Anterior");
			wizard.getFinishButton().setCaption("Terminar");
			wizard.getCancelButton().setCaption("Cancelar");
			
//			WizardProgressBar progressBar = new WizardProgressBar(wizard);
//	        wizard.addListener(progressBar);
//	        progressBar.getl
	        
//	        wizard.setHeader(progressBar);
			// wizard.setUriFragmentEnabled(true);

			VerticalLayout vl = (VerticalLayout) wizard.getBackButton()
					.getParent().getParent();
			vl.setComponentAlignment(wizard.getBackButton().getParent(),
					com.vaadin.ui.Alignment.BOTTOM_CENTER);

			wizard.addStep(new TelaPergunta(
					"O evento observado na seguinte imagem é chamado de:",
					new ArrayList<String>() {
						{
							add("Ejeção de Massa Coronal");
							add("Explosão solar");
							add("Mancha Solar");
							add("Ciclo solar");
						}
					}, "images/1.png", req, false));
			wizard.addStep(new TelaPergunta(
					"A imagem mostra um fenômeno que ocorre na superfície do sol. Quais fenômenos estão sendo exibidos?",
					new ArrayList<String>() {
						{
							add("Ejeção de Massa Coronal");
							add("Granulação solar");
							add("Mancha Solar");
							add("Ciclo solar");
						}
					}, "images/2.png", req, true));
		
			wizard.addStep(new TelaPergunta(
					"O evento observado na seguinte imagem é chamado de:",
					new ArrayList<String>() {
						{
							add("Ejeção de Massa Coronal");
							add("Explosão solar");
							add("Mancha Solar");
							add("Ciclo solar");
						}
					}, "images/4.gif", req, false));
			wizard.addStep(new TelaPergunta(
					"Na seguinte imagem, as áreas pretas e brancas nas manchas representam:",
					new ArrayList<String>() {
						{
							add("Polos norte e sul");
							add("Plasma quente e frio");
							add("Mancha Solar");
							add("Ejeção de massa coronal");
						}
					}, "images/7.jpg", req, false));

		}
		return wizard;
	}

}
