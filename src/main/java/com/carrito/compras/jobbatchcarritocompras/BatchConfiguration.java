package com.carrito.compras.jobbatchcarritocompras;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.carrito.compras.entities.CatalogoEntity;
import com.carrito.compras.listener.JobListener;
import com.carrito.compras.processor.CatalogoProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public FlatFileItemReader<CatalogoEntity> reader() throws UnexpectedInputException, ParseException, Exception{
		FlatFileItemReader<CatalogoEntity> read = new FlatFileItemReaderBuilder<CatalogoEntity>()
				.name("catalogoItemReader")
				.resource(new ClassPathResource("Productos.csv"))
				.linesToSkip(1)
				.delimited().delimiter(",")
				.names(new String[] {"nombre", "marca", "precio", "stock", "estado", "descuento"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<CatalogoEntity>() {{
					setTargetType(CatalogoEntity.class);
				}})
				.build();		
		return read;
	}
	
     
	
	@Bean
	public CatalogoProcessor processor() {
		return new CatalogoProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<CatalogoEntity> writer(DataSource datasource){
		return new JdbcBatchItemWriterBuilder<CatalogoEntity>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO CATALOGO (nombre, marca, precio, stock, estado, descuento) VALUES (:nombre, :marca, :precio,"
						+ " :stock, :estado, :descuento)")
				.dataSource(datasource).build();
	}
	
	@Bean
	public Job importCatalogoJob(JobListener listener, Step step1) {
		return jobBuilderFactory.get("importCatalogoJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1)
				.end()
				.build();
	}
	
	@Bean
	public Step step1(JdbcBatchItemWriter<CatalogoEntity> writer) throws UnexpectedInputException, ParseException, Exception {
		return stepBuilderFactory.get("step1")
				.<CatalogoEntity, CatalogoEntity> chunk(50)
				.reader(reader())
				.processor(processor())
				.writer(writer)
				.build();
	}
	

}
