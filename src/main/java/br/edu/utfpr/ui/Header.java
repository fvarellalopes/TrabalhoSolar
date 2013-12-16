package br.edu.utfpr.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class Header extends HorizontalLayout {

	// Header Components
//	private final Embedded em = new Embedded("", new Resource(
//			"images/toolkit-logo.png"));
//	private final LoginHandler loginhandler = new LoginHandler();

	public Header() {

		setWidth("100%");
		Panel panel = new Panel();
		panel.setSizeFull();
//		panel.setScrollable(false);
		HorizontalLayout layout = new HorizontalLayout();

		panel.setHeight("150");

		// Create the visual components of the layout.
		Label lbl = new Label("asdasd");
		layout.addComponent(lbl);
		layout.setComponentAlignment(lbl, Alignment.MIDDLE_LEFT);
		layout.setWidth("100%");
panel.setContent(layout);
		// Adds the login-"box" to the header.
		

		
		addComponent(panel);
	}

	/*
	 * Method to change the visuals of the header when a user logs in.
	 */
	public void userLoggedIn() {

		// If for some reason the loginhandler has not been initialized.
		try {
//			loginhandler.setSizeFull();
//			loginhandler.createLogoutComponents();

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Method to change the visuals of the header when a user logs out.
	 */
	public void userLoggedOut() {

		// If for some reason the loginhandler has not been initialized.
		try {

//			loginhandler.createLoginComponents();

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
