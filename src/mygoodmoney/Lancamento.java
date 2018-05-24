/**
* @file Lancamento.java
* @brief ContXm mXtodos de acesso X classe Lancamento.
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
public class Lancamento {
	private int    codLancamento;  /**< O CXdigo de um Lancamento. */
	private int    dataEmissao;    /**< A data de emissXo de um Lancamento. */
	private int    dataVencimento; /**< A data de vencimento de um Lancamento. */
	private int    dataQuitacao;   /**< A data de quitaXXo de um Lancamento (SerX 0 para provisXo). */
	private String descricao;      /**< A descriXXo de um Lancamento. */
	private double valor;          /**< O valor de um Lancamento. */
	private char   pago;           /**< S se pago ou N se nXo */
	private Conta  conta;
	private Caixa  caixa;

	/**
	 * @brief Construtor da classe.
	 */
	public Lancamento() {
		this.codLancamento  = 0;
		this.dataEmissao    = 0;
		this.dataVencimento = 0;
		this.dataQuitacao   = 0;
		this.descricao      = "";
		this.valor          = 0;
		this.pago           = ' ';
		this.conta          = null;
		this.caixa          = null;
	}

	/**
	 * @brief ObtXm o cXdigo do Lancamento.
	 * @return O cXdigo do Lancamento.
	 */
	public int getCodLancamento() {
		return( this.codLancamento );
	}
	/**
	 * @brief ObtXm a data de emissXo do Lancamento.
	 * @return A data de emissXo do Lancamento.
	 */
	public int getDataEmissao() {
		return( this.dataEmissao );
	}
	/**
	 * @brief ObtXm a data de vencimento do Lancamento.
	 * @return A data de vencimento do Lancamento.
	 */
	public int getDataVencimento() {
		return( this.dataVencimento );
	}
	/**
	 * @brief ObtXm a data de quitaXXo do Lancamento.
	 * @return A data de quitaXXo do Lancamento.
	 */
	public int getDataQuitacao() {
		return( this.dataQuitacao );
	}
	/**
	 * @brief ObtXm a descriXXo do Lancamento.
	 * @return A descriXXo do Lancamento.
	 */
	public String getDescricao() {
		return( this.descricao );
	}
	/**
	 * @brief ObtXm o valor do Lancamento.
	 * @return O valor do Lancamento.
	 */
	public double getValor() {
		return( this.valor );
	}
	/**
	 * @brief Seta o cXdigo do Lancamento.
	 * @param pCodLancamento O cXdigo a ser setado.
	 */
	public void setCodLancamento( int pCodLancamento ) {
		this.codLancamento = pCodLancamento;
	}
	/**
	 * @brief Seta a data de emissXo do Lancamento.
	 * @param pDataEmissao A data de emissXo a ser setada.
	 */
	public void setDataEmissao( int pDataEmissao ) {
		this.dataEmissao = pDataEmissao;
	}
	/**
	 * @brief Seta a data de vencimento do Lancamento.
	 * @param pDataVencimento A data de vencimento a ser setada.
	 */
	public void setDataVencimento( int pDataVencimento ) {
		this.dataVencimento = pDataVencimento;
	}
	/**
	 * @brief Seta a data de quitaXXo do Lancamento.
	 * @param pDataQuitacao A data de quitaXXo a ser setada.
	 */
	public void setDataQuitacao( int pDataQuitacao ) {
	 this.dataQuitacao = pDataQuitacao;
	}
	/**
	 * @brief Seta a descriXXo do Lancamento.
	 * @param pDescricao A descriXXo a ser setada.
	 */
	public void setDescricao( String pDescricao ) {
		this.descricao = pDescricao;
	}
	/**
	 * @brief Seta o valor do Lancamento.
	 * @param pValor O valor a ser setado.
	 */
	public void setValor( double pValor ) {
		this.valor = pValor;
	}
	/**
	 * @brief ObtXm se um lanXamento jX foi pago.
	 * @return S se pago ou N se não.
	 */
	public char getPago() {
	 return( this.pago );
	}
	/**
	 * @brief ObtXm a Conta do lanXamento.
	 * @return A Conta do lanXamento.
	 */
	public Conta getConta() {
		return( this.conta );
	}
	/**
	 * @brief Seta a Conta do lanXamento
	 * @param pConta A Conta a ser setada.
	 */
	public void setConta( Conta pConta ) {
		this.conta = pConta;
	}
	/**
	 * @brief Seta se um lanXamento foi pago ou nXo.
	 * @param pPago O pagamento a ser setado, S ou N.
	 */
	public void setPago( char pPago ) {
		this.pago = pPago;
	}
	/**
	 * @brief ObtXm o Caixa do lanXamento.
	 * @return O Caixa do lanXamento.
	 */
	public Caixa getCaixa() {
		return( this.caixa );
	}
	/**
	 * @brief Seta o Caixa do lanXamento.
	 * @param pCaixa O Caixa a ser setado.
	 */
	public void setCaixa( Caixa pCaixa ) {
		this.caixa = pCaixa;
	}
}