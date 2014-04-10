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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package mygoodmoney;

/**
* @class Conta
*/
public class Conta
{
  int       iCodConta; /**< O código da Conta */
  String    sNome;     /**< O nome da Conta */
  Character cTipo;     /**< O tipo da Conta (D ou C) */
  
  /**
  * @brief Contrutor da classe Conta.
  */
  public Conta()
  {
    this.iCodConta = 0;
    this.sNome     = "";
    this.cTipo     = ' ';
  }
  /**
  * @brief Obtém o código da Conta.
  * @return O código da conta.
  */
  public int getICodConta()
  {
    return( this.iCodConta );
  }
  /**
  * @brief Obtém o nome da Conta.
  * @return O nome da Conta.
  */
  public String getSNome()
  {
    return( this.sNome );
  }
  /**
  * @brief Obtém o tipo da Conta.
  * @return O Tipo da Conta (D ou C).
  */
  public Character getCTipo()
  {
    return( this.cTipo );
  }
  /**
  * @brief Seta o código da Conta.
  * @param piCodConta O código da Conta.
  */
  public void setICodConta( int piCodConta )
  {
    this.iCodConta = piCodConta;
  }
  /**
  * @brief Seta o nome da Conta.
  * @param psNome O nome da Conta.
  */
  public void setSNome( String psNome )
  {
    this.sNome = psNome;
  }
  /**
  * @brief Seta o tipo da Conta (D ou C).
  * @param pcTipo O tipo da Conta.
  */
  public void setCTipo( Character pcTipo )
  {
    this.cTipo = pcTipo;
  }
}