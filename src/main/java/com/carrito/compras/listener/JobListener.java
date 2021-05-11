package com.carrito.compras.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.carrito.compras.entities.CatalogoEntity;

@Component
public class JobListener extends JobExecutionListenerSupport{
	
	private JdbcTemplate jdbcTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(JobListener.class);
	
	@Autowired
	public JobListener(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOG.info("FINALIZO EL JOB!! verifica los resultados: ");
			jdbcTemplate.query("SELECT nombre, marca, precio , stock, estado, descuento FROM CATALOGO", (rs, row) -> 
			new CatalogoEntity(rs.getString(1), rs.getString(2), rs.getLong(3), rs.getLong(4), rs.getString(5), rs.getLong(6)))
			.forEach(catalogo -> LOG.info("Se registro el catalogo < " + catalogo + " >"));
		}
	}

}
