/**
* @file EnumPeriodo.java
* @brief Contém as constantes para cada período.
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
 * @enum EnumPeriodo
 */
public enum EnumPeriodo
{
  DIAS   ( 1, "Dia(s)" ),    /**< Define o valor 1 para DIAS */
  SEMANAS( 7, "Semana(s)" ), /**< Define o valor 7 para SEMANAS */
  MESES  ( 30, "Mês(es)" ),  /**< Define o valor 30 para MESES */
  ANOS   ( 365, "Ano(s)");   /**< Define o valor 365 para ANOS */
  
  private final int    iDias;
  private final String sDescricao;
  
  /**
  * @brief Construtor do EnumPeriodo.
  * @param piDias O número de dias no período.
  * @param psDescricao A descrição do período.
  */
  private EnumPeriodo( int piDias, String psDescricao )
  {
    this.iDias = piDias;
    this.sDescricao = psDescricao;
  }

  /**
  * @brief Obtém o número de dias do enum.
  * @return O número de dias.
  */
  public int getIDias()
  {
    return( this.iDias );
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
  * @brief Converte o enum para String.
  * @return A descrição do enum.
  */
  @Override
  public String toString()
  {
    return( this.sDescricao );
  }
}