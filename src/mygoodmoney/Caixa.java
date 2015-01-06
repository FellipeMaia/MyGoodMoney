/**
* @file Caixa.java
* @brief Contém métodos de acesso ao objeto Caixa.
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
* @class Caixa
*/
public class Caixa {
	private Integer	  codCaixa;	   /**< O código do Caixa */
	private String	  nome;		   /**< O nome do Caixa */
	private Double	  saldo;	   /**< O Saldo do Caixa */
	private Character limite;	   /**< S se possuir limite ou N se não */
	private Double	  valorLimite; /**< Valor do limite */
	/**
	 * @brief Contrutor da classe Caixa
	 */
	public Caixa() {
		this.codCaixa	 = 0;
		this.nome		 = "";
		this.saldo		 = 0D;
		this.limite		 = 'N';
		this.valorLimite = 0D;
	}
	/**
	 * @brief Obtém o código do Caixa.
	 * @return O código do Caixa.
	 */
	public Integer getCodCaixa() {
		return( this.codCaixa );
	}
	/**
	 * @brief Obtém o nome do Caixa.
	 * @return O nome do Caixa.
	 */
	public String getNome() {
		return( this.nome );
	}
	/**
	 * @brief Obtém o saldo do Caixa.
	 * @return O saldo do caixa.
	 */
	public Double getSaldo() {
		return( this.saldo );
	}
	/**
	 * @brief Verifica se um caixa usa limite.
	 * @return S se sim, N caso contrário.
	 */
	public Character getLimite() {
		return( this.limite );
	}
	/**
	 * @brief Obtém o valor do limite de um caixa.
	 * @return O valor do limite.
	 */
	public Double getValorLimite() {
		return( this.valorLimite );
	}
	/**
	 * @brief Seta o código do Caixa.
	 * @param pCodCaixa O código do Caixa.
	 */
	public void setCodCaixa( Integer pCodCaixa ) {
		this.codCaixa = pCodCaixa;
	}
	/**
	 * @brief Seta o nome do Caixa.
	 * @param pNome O nome do Caixa.
	 */
	public void setNome( String pNome ) {
		this.nome = pNome;
	}
	/**
	 * @brief Seta o saldo do Caixa.
	 * @param pSaldo O saldo do Caixa;
	 */
	public void setSaldo( Double pSaldo ) {
		this.saldo = pSaldo;
	}
	/**
	 * @brief Seta se um caixa usa limite.
	 * @param pLimite S para definir Sim ou N caso contrário.
	 */
	public void setLimite( Character pLimite ) {
		this.limite = pLimite;
	}
	/**
	 * @brief Seta o valor do limite de um caixa.
	 * @param pValor O valor do limite.
	 */
	public void setValorLimite( Double pValor ) {
		this.valorLimite = pValor;
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
		final Caixa c = (Caixa)obj;
		return( getNome().equals( c.getNome() ) );
	}
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + Objects.hashCode(this.codCaixa);
		hash = 41 * hash + Objects.hashCode(this.nome);
		hash = 41 * hash + Objects.hashCode(this.saldo);
		return hash;
	}
}