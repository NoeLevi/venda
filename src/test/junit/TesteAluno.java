package test.junit;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import mz.com.venda.report.util.DateUtils;

public class TesteAluno {

	@Test
	public void test() {
		try{
		assertEquals("11012018", DateUtils.getDateAtualReport());
		assertEquals("'2018-01-11'", DateUtils.formatDateSql(Calendar.getInstance().getTime()));
		assertEquals("2018-01-11", DateUtils.formatDateSqlSimple(Calendar.getInstance().getTime()));

		}catch(Exception e){
		e.printStackTrace();
		fail(e.getMessage());
		
	}
	}
		

}
 