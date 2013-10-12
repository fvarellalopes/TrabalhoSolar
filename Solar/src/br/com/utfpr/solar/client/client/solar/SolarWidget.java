package br.com.utfpr.solar.client.client.solar;

import com.google.gwt.user.client.ui.Label;

// TODO extend any GWT Widget
public class SolarWidget extends Label {

	public static final String CLASSNAME = "solar";

	public SolarWidget() {

		// setText("Solar sets the text via SolarConnector using SolarState");
		setStyleName(CLASSNAME);

	}

}