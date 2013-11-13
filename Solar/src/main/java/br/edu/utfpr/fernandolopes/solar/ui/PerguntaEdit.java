package br.edu.utfpr.fernandolopes.solar.ui;

import br.edu.utfpr.fernandolopes.solar.ds.SolarJPAContainer;
import br.edu.utfpr.fernandolopes.solar.entity.Pergunta;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.easyuploads.UploadField;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class PerguntaEdit extends Window implements Button.ClickListener {

	private static final long serialVersionUID = -2996627404786697495L;

	private FormLayout layout;
	private BeanFieldGroup<Pergunta> binder;
	private HorizontalLayout buttons;
	private Button bSalvar;
	private Button bCancelar;
	private Button bExcluir;

	private SolarJPAContainer<Pergunta> datasource;

	private UploadField uploadField;

	public PerguntaEdit(SolarJPAContainer<Pergunta> datasource) {
		this.datasource = datasource;
		init();
		setModal(true);
	}

	public PerguntaEdit() {
		// TODO Auto-generated constructor stub
	}

	private void init() {
		layout = new FormLayout();
		layout.setSizeFull();
		layout.setSpacing(true);

		bSalvar = new Button("Salvar");
		bSalvar.addClickListener(this);
		bSalvar.setClickShortcut(KeyCode.ENTER);

		bCancelar = new Button("Cancelar");
		bCancelar.addClickListener(this);
		bCancelar.setClickShortcut(KeyCode.ESCAPE);

		bExcluir = new Button("Excluir");
		bExcluir.addClickListener(this);
		bExcluir.setVisible(false);

		buttons = new HorizontalLayout();
		buttons.addComponent(bSalvar);
		buttons.addComponent(bCancelar);
		buttons.addComponent(bExcluir);

		uploadField = new UploadField();
		layout.addComponent(uploadField);

		setContent(layout);

		setHeight("370");
		setWidth("400");
	}

	/**
	 * Apresenta a <code>Window</code> em forma de edição.
	 * 
	 * @param id
	 */
	public void edit(Integer id) {
		try {
			setCaption("Editar Mercadoria");
			Pergunta m = datasource.getItem(id).getEntity();
			bindingFields(m);
			bExcluir.setVisible(true);
			UI.getCurrent().addWindow(this);
		} catch (Exception ex) {
			Notification.show("Não consegui abrir a mercadoria para edição!\n"
					+ ex.getMessage(), Type.ERROR_MESSAGE);
		}
	}

	/**
	 * Apresenta a <code>Window</code> em forma de edição.
	 */
	public void create() {
		setCaption("Nova Mercadoria");
		bindingFields(new Pergunta());
		UI.getCurrent().addWindow(this);
	}

	/**
	 * Cria o formulário com os campos para preenchimento.
	 * 
	 * @param m
	 */
	private void bindingFields(Pergunta m) {
		binder = new BeanFieldGroup<Pergunta>(Pergunta.class);
		binder.setItemDataSource(m);
		Field<?> field = null;
		field = binder.buildAndBind("texto", "texto");
		field.setWidth("140");
		layout.addComponent(field);

		field = binder.buildAndBind("Descrição", "descricao");
		field.setWidth("200");
		layout.addComponent(field);

		layout.addComponent(binder.buildAndBind("Id", "id"));

		layout.addComponent(buttons);
	}

	/**
	 * Implementa tratadores para os eventos dos <code>Button</code> dessa tela.
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == bSalvar) {
			try {
				Object value = uploadField.getValue();
				binder.commit();
			} catch (CommitException e) {
				Notification.show("Preencha o formulário corretamente");
				return;
			}

			try {
				datasource.addEntity(binder.getItemDataSource().getBean());
				// log.debug("Mercadoria persistida!");
				Notification.show("Mercadoria persistida!",
						Type.HUMANIZED_MESSAGE);
			} catch (Exception e) {
				Notification.show(
						"Nao consegui salvar a mercadoria!\n" + e.getMessage(),
						Type.ERROR_MESSAGE);
				return;
			}
		} else if (event.getButton() == bExcluir) {
			ConfirmDialog.show(this.getUI(), "Confirma a exclusão?",
					new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {
							if (dialog.isConfirmed()) {
								try {
									datasource.removeItem(binder
											.getItemDataSource().getBean()
											.getId());
									// log.debug("Excluiu a Mercadoria!");
								} catch (Exception e) {

									Notification.show(
											"Nao consegui remover a Mercadoria!\n"
													+ e.getMessage(),
											Type.ERROR_MESSAGE);
								}
								close();
							}
						}
					});
			return;
		} else if (event.getButton() == bCancelar) {
			binder.discard();
		}
		close();
	}

}
