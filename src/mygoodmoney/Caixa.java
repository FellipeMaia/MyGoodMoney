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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package mygoodmoney;

/**
* @class Caixa
*/
public class Caixa
{
  int     iCodCaixa;  /**< O código do Caixa */
  String  sNome;      /**< O nome do Caixa */
  double  dSaldo;     /**< O Saldo do Caixa */
  
  /**
  * @brief Contrutor da classe Caixa
  */
  public Caixa()
  {
    this.iCodCaixa = 0;
    this.sNome     = "";
    this.dSaldo    = 0;
  }
  
  /**
  * @brief Obtém o código do Caixa.
  * @return O código do Caixa.
  */
  public int getICodCaixa()
  {
    return( this.iCodCaixa );
  }

  /**
  * @brief Obtém o nome do Caixa.
  * @return O nome do Caixa.
  */
  public String getSNome()
  {
    return( this.sNome );
  }
  /**
  * @brief Obtém o saldo do Caixa.
  * @return O saldo do Caixa.
  */
  public double getDSaldo()
  {
    return( this.dSaldo );
  }
  /**
  * @brief Seta o código do Caixa.
  * @param piCodCaixa O código do Caixa.
  */
  public void setICodCaixa( int piCodCaixa )
  {
    this.iCodCaixa = piCodCaixa;
  }
  /**
  * @brief Seta o nome do Caixa.
  * @param psNome O nome do Caixa.
  */
  public void setSNome( String psNome )
  {
    this.sNome = psNome;
  }
  /**
  * @brief Seta o saldo do Caixa.
  * @param pdSaldo O saldo do Caixa;
  */
  public void setDSaldo( double pdSaldo )
  {
    this.dSaldo = pdSaldo;
  }
}