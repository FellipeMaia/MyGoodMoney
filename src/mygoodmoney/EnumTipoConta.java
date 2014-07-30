/**
* @file EnumTipoConta.java
* @brief Contém as constantes para os tipos de contas.
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package mygoodmoney;

/**
 * @enum EnumTipoConta
 */
public enum EnumTipoConta {
  CREDITO('C',"Crédito"),  /**< Define 'C' para CREDITO */
  DEBITO('D',"Débito");    /**< Define 'D' para DEBITO */
  
  private final char   codigo;
  private final String descricao;

  /**
   * @brief Construtor do enum EnumTipoConta.
   * @param pCodigo O código do enum.
   * @param pDescricao A descrição do enum.
   */
  private EnumTipoConta( char pCodigo, String pDescricao ) {
    this.codigo    = pCodigo;
    this.descricao = pDescricao;
  }

  /**
   * @brief Obtém o código do enum.
   * @return O código do enum.
   */
  public char getCodigo() {
    return( this.codigo );
  }
  /**
   * @brief Obtém a descrição do enum.
   * @return A descrição do enum.
   */
  public String getDescricao() {
      return( this.descricao );
  }
  /**
   * @brief Busca um enum por código.
   * @param pCodEnum O código do ennum.
   * @return O enum se encontrado, ou null se não.
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
   * @brief Busca um enum por descrição.
   * @param pDescricaoEnum A descrição do ennum.
   * @return O enum se encontrado, ou null se não.
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
   * @return A descrição do enum.
   */
  @Override
  public String toString() {
    return( this.descricao );
  }
}