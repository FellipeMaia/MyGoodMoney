/**
* @file Lancamento.java
* @brief Contém métodos de acesso à classe Lancamento.
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
 * @class Lancamento
 */
public class Lancamento
{
  private int    iCodLancamento;  /**< O Código de um Lancamento. */
  private int    iDataEmissao;    /**< A data de emissão de um Lancamento. */
  private int    iDataVencimento; /**< A data de vencimento de um Lancamento. */
  private int    iDataQuitacao;   /**< A data de quitação de um Lancamento (Será 0 para provisão). */
  private String sDescricao;      /**< A descrição de um Lancamento. */
  private double dValor;          /**< O valor de um Lancamento. */
  private int    iCodConta;       /**< O código da conta de um Lancamento. */
  private int    iCodCaixa;       /**< O código do caixa de um Lancamento. */
  private char   cTipo;           /**< O tipo de um Lancamento (D ou C ).*/

  /**
  * @brief Construtor da classe.
  */
  public Lancamento()
  {
    this.iCodLancamento  = 0;
    this.iDataEmissao    = 0;
    this.iDataVencimento = 0;
    this.iDataQuitacao   = 0;
    this.sDescricao      = "";
    this.dValor          = 0;
    this.iCodConta       = 0;
    this.iCodCaixa       = 0;
    this.cTipo           = ' ';
  }

  /**
  * @brief Obtém o código do Lancamento.
  * @return O código do Lancamento.
  */
  public int getICodLancamento()
  {
    return( this.iCodLancamento );
  }

  /**
  * @brief Obtém a data de emissão do Lancamento.
  * @return A data de emissão do Lancamento.
  */
  public int getIDataEmissao()
  {
    return( this.iDataEmissao );
  }

  /**
  * @brief Obtém a data de vencimento do Lancamento.
  * @return A data de vencimento do Lancamento.
  */
  public int getIDataVencimento()
  {
    return( this.iDataVencimento );
  }

  /**
  * @brief Obtém a data de quitação do Lancamento.
  * @return A data de quitação do Lancamento.
  */
  public int getIDataQuitacao()
  {
    return( this.iDataQuitacao );
  }

  /**
  * @brief Obtém a descrição do Lancamento.
  * @return A descrição do Lancamento.
  */
  public String getSDescricao()
  {
    return( this.sDescricao );
  }

  /**
  * @brief Obtém o valor do Lancamento.
  * @return O valor do Lancamento.
  */
  public double getDValor()
  {
    return( this.dValor );
  }

  /**
  * @brief Obtém o tipo do Lancamento.
  * @return O tipo do Lancamento.
  */
  public char getCTipo()
  {
    return( this.cTipo );
  }

  /**
  * @brief Seta o código do Lancamento.
  * @param piCodLancamento O código a ser setado.
  */
  public void setICodLancamento( int piCodLancamento )
  {
    this.iCodLancamento = piCodLancamento;
  }

  /**
  * @brief Seta a data de emissão do Lancamento.
  * @param piDataEmissao A data de emissão a ser setada.
  */
  public void setIDataEmissao( int piDataEmissao )
  {
    this.iDataEmissao = piDataEmissao;
  }

  /**
  * @brief Seta a data de vencimento do Lancamento.
  * @param piDataVencimento A data de vencimento a ser setada.
  */
  public void setIDataVencimento( int piDataVencimento )
  {
    this.iDataVencimento = piDataVencimento;
  }

  /**
  * @brief Seta a data de quitação do Lancamento.
  * @param piDataQuitacao A data de quitação a ser setada.
  */
  public void setIDataQuitacao( int piDataQuitacao )
  {
    this.iDataQuitacao = piDataQuitacao;
  }

  /**
  * @brief Seta a descrição do Lancamento.
  * @param psDescricao A descrição a ser setada.
  */
  public void setDescricao( String psDescricao )
  {
    this.sDescricao = psDescricao;
  }

  /**
  * @brief Seta o valor do Lancamento.
  * @param pdValor O valor a ser setado.
  */
  public void setDValor( double pdValor )
  {
    this.dValor = pdValor;
  }

  /**
  * @brief Seta o tipo do Lancamento.
  * @param pcTipo O tipo a ser setado.
  */
  public void setCTipo( char pcTipo )
  {
    this.cTipo = pcTipo;
  }

  /**
  * @brief Obtém o código da conta do Lancamento.
  * @return O código da conta do Lancamento.
  */
  public int getICodConta()
  {
    return( this.iCodConta );
  }

  /**
  * @brief Obtém o código do caixa do Lancamento.
  * @return O código do caixa do Lancamento.
  */
  public int getICodCaixa()
  {
    return( this.iCodCaixa );
  }

  /**
  * @brief Seta o código da conta do Lancamento.
  * @param piCodConta O código da conta a ser setado.
  */
  public void setICodConta( int piCodConta )
  {
    this.iCodConta = piCodConta;
  }

  /**
  * @brief Seta o código do caixa do Lancamento.
  * @param piCodCaixa O código do caixa a ser setado.
  */
  public void setICodCaixa( int piCodCaixa )
  {
    this.iCodCaixa = piCodCaixa;
  }
}