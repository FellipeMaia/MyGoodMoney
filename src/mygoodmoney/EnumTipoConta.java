/**
* @file EnumTipoConta.java
* @brief Cont�m as constantes para os tipos de contas.
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

/**
* @enum EnumTipoConta
*/
public enum EnumTipoConta {
	CREDITO('C',"Cr�dito"),	 /**< Define 'C' para CREDITO */
	DEBITO('D',"D�bito");	 /**< Define 'D' para DEBITO */
	private final char	 codigo;
	private final String descricao;
	/**
	* @brief Construtor do enum EnumTipoConta.
	* @param pCodigo O c�digo do enum.
	* @param pDescricao A descri��o do enum.
	*/
	private EnumTipoConta( char pCodigo, String pDescricao ) {
		this.codigo	   = pCodigo;
		this.descricao = pDescricao;
	}
	/**
	* @brief Obt�m o c�digo do enum.
	* @return O c�digo do enum.
	*/
	public char getCodigo() {
		return( this.codigo );
	}
	/**
	* @brief Obt�m a descri��o do enum.
	* @return A descri��o do enum.
	*/
	public String getDescricao() {
		return( this.descricao );
	}
	/**
	* @brief Busca um enum por c�digo.
	* @param pCodEnum O c�digo do ennum.
	* @return O enum se encontrado, ou null se n�o.
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
	* @brief Busca um enum por descri��o.
	* @param pDescricaoEnum A descri��o do ennum.
	* @return O enum se encontrado, ou null se n�o.
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
	* @return A descri��o do enum.
	*/
	@Override
	public String toString() {
		return( this.descricao );
	}
}