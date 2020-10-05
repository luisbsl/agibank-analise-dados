package br.com.luisbsl.agibankanalisedados.model.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.luisbsl.agibankanalisedados.exception.DadoInvalidoException;
import br.com.luisbsl.agibankanalisedados.model.Dado;

/**
 * Interface utilizada como contrato a cumprir pelos Builders
 * 
 * @see VendedorBuilder
 * @see ClienteBuilder
 * @see VendaBuilder
 * @see ItemBuilder
 * @author luislima
 *
 */
public interface IDadoBuilder {
	
	public Dado build();
	
	default Boolean isDadoValido(Dado dado) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Dado>> violations = validator.validate(dado);
		
		if(violations!=null && Boolean.FALSE.equals(violations.isEmpty())) {
			List<String> mensagensErro = new ArrayList<>();
			violations.stream().forEach(v -> concatenarMensagensErro(mensagensErro, v.getMessage()));
			throw new DadoInvalidoException(mensagensErro.stream().collect(Collectors.joining("\r\n")));
		}
		
		return true;
	}
	
	default void concatenarMensagensErro(List<String> mensagensErro, String mensagemErro) {
		mensagensErro.add(mensagemErro);
	}

}
