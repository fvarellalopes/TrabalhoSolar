package br.edu.utfpr.fernandolopes.solar.ds;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;

public class SolarJPAContainer<T> extends JPAContainer<T> {

	private static final String PERSISTENCE_UNIT = "SolarUnit";
	private static final long serialVersionUID = 6697237831262334398L;

	public SolarJPAContainer(Class<T> entityClass) {
		super(entityClass);

		EntityManager em = JPAContainerFactory
				.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);
		setEntityProvider(new CachingMutableLocalEntityProvider<T>(entityClass,
				em));
		// }
	}

	// public class MercadoriaJPAContainer extends JPAContainer<Mercadoria> {
	//
	// private static final long serialVersionUID = 5832996438848438038L;
	//
	// /**
	// * Nome da unidade persistencia. De acordo com o arquivo
	// <code>persistence.xml</code>.
	// */

	//
	// }
}
