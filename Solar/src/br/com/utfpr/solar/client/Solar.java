package br.com.utfpr.solar.client;

import br.com.utfpr.solar.client.client.solar.SolarClientRpc;
import br.com.utfpr.solar.client.client.solar.SolarServerRpc;
import com.vaadin.shared.MouseEventDetails;
import br.com.utfpr.solar.client.client.solar.SolarState;

public class Solar extends com.vaadin.ui.AbstractComponent {

	private SolarServerRpc rpc = new SolarServerRpc() {
		private int clickCount = 0;

		public void clicked(MouseEventDetails mouseDetails) {
			// nag every 5:th click using RPC
			if (++clickCount % 5 == 0) {
				getRpcProxy(SolarClientRpc.class).alert(
						"Ok, that's enough!");
			}
			// update shared state
			getState().text = "You have clicked " + clickCount + " times";
		}
	};  

	public Solar() {
		registerRpc(rpc);
	}

	@Override
	public SolarState getState() {
		return (SolarState) super.getState();
	}
}
