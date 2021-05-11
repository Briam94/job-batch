package com.carrito.compras.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.carrito.compras.entities.CatalogoEntity;


public class CatalogoProcessor implements ItemProcessor<CatalogoEntity, CatalogoEntity> {
	
	private static final Logger LOG = LoggerFactory.getLogger(CatalogoProcessor.class);

	@Override
	public CatalogoEntity process(CatalogoEntity item) throws Exception {
		String nombre = item.getNombre() ;		
		String marca = item.getMarca();	
		Long precio = item.getPrecio();
		Long stock = item.getStock();		
		String estado = item.getEstado();		
		Long descuento = item.getDescuento();
		if(!nombre.equals("") && !marca.equals("") && precio != null && stock != null && !estado.equals("") && descuento != null
				&& nombre != null && marca != null && estado != null) {
			LOG.info("Convirtiendo " + item);
			return new CatalogoEntity(nombre, marca, precio, stock, estado, descuento);
		}
		return null;
	}
	
	

}
