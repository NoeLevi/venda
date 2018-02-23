package mz.com.venda.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(value= ElementType.FIELD)
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
public abstract @interface IdentificaCampo {

	String descricaoCampo(); // descricao da tela
	String campoConsulta(); //campo do banco
	int principal() default 1000;
}
