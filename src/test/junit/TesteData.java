package test.junit;

import static org.junit.Assert.*;


import org.junit.Test;

public class TesteData {

	@Test
	public void test() {
		
		Aluno aluno = new Aluno();
		aluno.setIdade(11);
		
		
		try{
		assertEquals("as", aluno.getIdade());
		
		
		}catch(Exception e){
		e.printStackTrace();
		fail(e.getMessage());
		
	}
	}
		

}
 