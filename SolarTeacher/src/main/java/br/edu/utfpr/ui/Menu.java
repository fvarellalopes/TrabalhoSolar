package br.edu.utfpr.ui;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;


public class Menu extends VerticalLayout implements ValueChangeListener {

	private Tree menuTree;
	private Panel panel;

	public Menu() {

		// Visual modifiers of the menu.

		setHeight("100%");
		setMargin(true);

		// Add a tree.
		menuTree = new Tree();
		menuTree.setMultiSelect(false);

		// Instantly receive value update events.
		menuTree.setImmediate(true);

		// Add objects to the tree.
		populate(menuTree);

		panel = new Panel("Channels");
//		panel.addComponent(menuTree);
		panel.setHeight("100%");

		addComponent(panel);
		
		menuTree.addListener((ValueChangeListener) this);
		
	}

	/*
	 * Populates the tree with hard coded data.
	 * @param anyTree the tree we want to populate with data.
	 */
	public void populate(Tree anyTree) {
		final Object[][] channels = new Object[][] { new Object[] { "User View",
				"#java", "#ubuntu" } };

		for (int i = 0; i < channels.length; i++) {
			String temp = (String) (channels[i][0]);
			anyTree.addItem(temp);
			
			if (channels[i].length == 1) {
				anyTree.setChildrenAllowed(temp, false);

			} else {
				for (int j = 1; j < channels[i].length; j++) {
					String temp1 = (String) (channels[i][j]);
					anyTree.addItem(temp1);
					anyTree.setParent(temp1, temp);
					anyTree.setChildrenAllowed(temp1, false);
				}

			}

		}

	}


	/*
	 *  Removes an item from menuTree.
	 */
	public void removeCurrentTreeSelection() {

		if (!menuTree.isRoot(menuTree.getValue())) {

			Object id = menuTree.getParent(menuTree.getValue());
			Object id2 = menuTree.getValue();
			menuTree.select(id);
			menuTree.removeItem(id2);


		}

	}

	public Tree getTree(){
		return menuTree;
	}

	public void valueChange(ValueChangeEvent event) {
		
		String clickedView = menuTree.getValue().toString();
		
//		ExampleApplication.getProject().getUiHandler().switchView(clickedView);
	}

}
