package br.com.utfpr.solar.client.client.solar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

import br.com.utfpr.solar.client.Solar;
import br.com.utfpr.solar.client.client.solar.SolarWidget;
import br.com.utfpr.solar.client.client.solar.SolarServerRpc;
import com.vaadin.client.communication.RpcProxy;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.client.MouseEventDetailsBuilder;
import br.com.utfpr.solar.client.client.solar.SolarClientRpc;
import br.com.utfpr.solar.client.client.solar.SolarState;
import com.vaadin.client.communication.StateChangeEvent;

@Connect(Solar.class)
public class SolarConnector extends AbstractComponentConnector {

	SolarServerRpc rpc = RpcProxy
			.create(SolarServerRpc.class, this);
	
	public SolarConnector() {
		registerRpc(SolarClientRpc.class, new SolarClientRpc() {
			public void alert(String message) {
				// TODO Do something useful
				Window.alert(message);
			}
		});

		// TODO ServerRpc usage example, do something useful instead
		getWidget().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final MouseEventDetails mouseDetails = MouseEventDetailsBuilder
					.buildMouseEventDetails(event.getNativeEvent(),
								getWidget().getElement());
				rpc.clicked(mouseDetails);
			}
		});

	}

	@Override
	protected Widget createWidget() {
		return GWT.create(SolarWidget.class);
	}

	@Override
	public SolarWidget getWidget() {
		return (SolarWidget) super.getWidget();
	}

	@Override
	public SolarState getState() {
		return (SolarState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		// TODO do something useful
		final String text = getState().text;
		getWidget().setText(text);
	}

}

