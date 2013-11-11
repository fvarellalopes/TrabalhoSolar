package br.edu.utfpr.fernandolopes.solar.ui;

import br.edu.utfpr.fernandolopes.solar.ds.SolarJPAContainer;
import br.edu.utfpr.fernandolopes.solar.entity.Pergunta;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Runo;

public class PerguntaView extends Table  {

	public PerguntaView(final SolarJPAContainer<Pergunta> container) {
		setContainerDataSource(container);
		
		addItemClickListener(new ItemClickEvent.ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				PerguntaEdit perg = new PerguntaEdit(container);
				perg.edit(Integer.valueOf(event.getItemId().toString()));
				
			}
		});
	
		
		setWidth("650px");
		configColumns();
		
		
		
	}

	
	private void configColumns() {
		setVisibleColumns(new Object[]{"id", "texto", "descricao", "imagem"});
		setColumnHeaders(new String[] {"#", "Texto", "Descrição", "Imagem"});
	}

	

}
