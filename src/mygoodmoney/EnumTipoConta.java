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
public enum EnumTipoConta
{
  CREDITO('C',"Crédito"),  /**< Define 'C' para CREDITO */
  DEBITO('D',"Débito");    /**< Define 'D' para DEBITO */
  
  private final char   cCodigo;
  private final String sDescricao;

  /**
  * @brief Construtor do enum EnumTipoConta.
  * @param pcCodigo O código do enum.
  * @param psDescricao A descrição do enum.
  */
  private EnumTipoConta( char pcCodigo, String psDescricao )
  {
    this.cCodigo    = pcCodigo;
    this.sDescricao = psDescricao;
  }

  /**
  * @brief Obtém o código do enum.
  * @return O código do enum.
  */
  public char getCCodigo()
  {
    return( this.cCodigo );
  }

  /**
  * @brief Obtém a descrição do enum.
  * @return A descrição do enum.
  */
  public String getSDescricao()
  {
      return( this.sDescricao );
  }

  /**
  * @brief Busca um enum por código.
  * @param pcCodEnum O código do ennum.
  * @return O enum se encontrado, ou null se não.
  */
  public static EnumTipoConta getPorCodigo( char pcCodEnum )
  {
    for( EnumTipoConta valor : EnumTipoConta.values() )
    {
      if( valor.cCodigo == pcCodEnum )
      {
        return( valor );
      }
    }
    return( null );
  }

  /**
  * @brief Busca um enum por descrição.
  * @param psDescricaoEnum A descrição do ennum.
  * @return O enum se encontrado, ou null se não.
  */
  public static EnumTipoConta getPorDescricao( String psDescricaoEnum )
  {
    for( EnumTipoConta valor : EnumTipoConta.values() )
    {
      if( valor.sDescricao.equals( psDescricaoEnum ) )
      {
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
  public String toString()
  {
    return( this.sDescricao );
  }
}