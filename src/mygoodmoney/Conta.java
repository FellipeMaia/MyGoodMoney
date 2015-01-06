/**
* @file Conta.java
* @brief Contém métodos de acesso ao objeto Conta.
* @copyright 2014 Ricardo Montania. Todos os Direitos Reservados.
* @license Este projeto encontra-se sob a licensa GNU.
*/

/*
* Copyright (C) 2014 Ricardo
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.	If not, see <http://www.gnu.org/licenses/>.
*/

package mygoodmoney;

import java.util.Objects;

/**
* @class Conta
*/
public class Conta {
	Integer	  codConta; /**< O código da Conta */
	String	  nome;		/**< O nome da Conta */
	Character tipo;		/**< O tipo da Conta (D ou C) */
	/**
	* @brief Contrutor da classe Conta.
	*/
	public Conta() {
		this.codConta = 0;
		this.nome	  = "";
		this.tipo	  = ' ';
	}
	/**
	* @brief Obtém o código da Conta.
	* @return O código da conta.
	*/
	public Integer getCodConta() {
		return( this.codConta );
	}
	/**
	* @brief Obtém o nome da Conta.
	* @return O nome da Conta.
	*/
	public String getNome() {
		return( this.nome );
	}
	/**
	* @brief Obtém o tipo da Conta.
	* @return O Tipo da Conta (D ou C).
	*/
	public Character getTipo() {
		return( this.tipo );
	}
	/**
	* @brief Seta o código da Conta.
	* @param pCodConta O código da Conta.
	*/
	public void setCodConta( Integer pCodConta ) {
		this.codConta = pCodConta;
	}
	/**
	* @brief Seta o nome da Conta.
	* @param pNome O nome da Conta.
	*/
	public void setNome( String pNome ) {
		this.nome = pNome;
	}
	/**
	* @brief Seta o tipo da Conta (D ou C).
	* @param pTipo O tipo da Conta.
	*/
	public void setTipo( Character pTipo ) {
		this.tipo = pTipo;
	}
	@Override
	public String toString() {
		return( this.nome );
	}
	@Override
	public boolean equals(Object obj) {
		if( obj == null || getClass() != obj.getClass() ) {
			return( false );
		}
		final Conta c = (Conta)obj;
		return( getNome().equals( c.getNome() ) );
	}
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 43 * hash + Objects.hashCode(this.codConta);
		hash = 43 * hash + Objects.hashCode(this.nome);
		hash = 43 * hash + Objects.hashCode(this.tipo);
		return hash;
	}
}