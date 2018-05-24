/**
* @file EnumTipoConta.java
* @brief ContXm as constantes para os tipos de contas.
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

package mygoodmoney.gui;

/**
* @enum EnumTipoConta
*/
public enum EnumTipoConta {
	CREDITO('C',"Credito"),	 /**< Define 'C' para CREDITO */
	DEBITO('D',"Debito");	 /**< Define 'D' para DEBITO */
	private final char	 codigo;
	private final String descricao;
	/**
	* @brief Construtor do enum EnumTipoConta.
	* @param pCodigo O cXdigo do enum.
	* @param pDescricao A descriXXo do enum.
	*/
	private EnumTipoConta( char pCodigo, String pDescricao ) {
		this.codigo	   = pCodigo;
		this.descricao = pDescricao;
	}
	/**
	* @brief ObtXm o cXdigo do enum.
	* @return O cXdigo do enum.
	*/
	public char getCodigo() {
		return( this.codigo );
	}
	/**
	* @brief ObtXm a descriXXo do enum.
	* @return A descriXXo do enum.
	*/
	public String getDescricao() {
		return( this.descricao );
	}
	/**
	* @brief Busca um enum por cXdigo.
	* @param pCodEnum O cXdigo do ennum.
	* @return O enum se encontrado, ou null se nXo.
	*/
	public static EnumTipoConta getPorCodigo( char pCodEnum ) {
		for( EnumTipoConta valor : EnumTipoConta.values() ) {
			if( valor.codigo == pCodEnum ) {
				return( valor );
			}
		}
		return( null );
	}
	/**
	* @brief Busca um enum por descriXXo.
	* @param pDescricaoEnum A descriXXo do ennum.
	* @return O enum se encontrado, ou null se nXo.
	*/
	public static EnumTipoConta getPorDescricao( String pDescricaoEnum ) {
		for( EnumTipoConta valor : EnumTipoConta.values() ) {
			if( valor.descricao.equals( pDescricaoEnum ) ) {
				return( valor );
			}
		}
		return( null );
	}
	/**
	* @brief Converte o enum para String.
	* @return A descriXXo do enum.
	*/
	@Override
	public String toString() {
		return( this.descricao );
	}
}