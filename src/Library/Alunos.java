/*
 * Projeto Integrado 3° Semestre CCO
 * 
 *  Author: Lucas Lacerda
 */
package Library;

import java.util.Vector;

public class Alunos {
	
	private String name;
	private String RA; 
	private String token;
	private Vector<?> books;
	
	public Alunos() {
	
	}
	
	public Alunos(String name, String rA, String token) {
		this.name = name;
		this.RA = rA;
		this.token = token;
	}

	public String getName(){
		return this.name;
	}
	
	public String getRA(){
		return this.RA;
	}
	
	public String getToken(){
		return this.token;
	}

	public void setName(String name){
		this.name = name; 
	}
	
	public void setRA(String ra){
		this.RA = ra; 
	}
	
	public  void setToken(String token){
		this.token = token;
	}
	
	
	
}
