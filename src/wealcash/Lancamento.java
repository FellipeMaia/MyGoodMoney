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

package wealcash;

/**
 *
 * @author Ricardo
 */
public class Lancamento
{
  private int       codLancamento;
  private int       dataEmissao;
  private int       dataVencimento;
  private int       dataQuitacao;
  private String    descricao;
  private Double    valor;
  private int       codConta;
  private int       codCaixa;
  private Character tipo;
  
  public Lancamento()
  {
    this.codLancamento = 0;
    this.dataEmissao = 0;
    this.dataVencimento = 0;
    this.dataQuitacao = 0;
    this.descricao = "";
    this.valor = Double.NaN;
    this.codConta = 0;
    this.codCaixa = 0;
    this.tipo = ' ';
  }
  
  public int getCodLancamento()
  {
    return( this.codLancamento );
  }
  public int getDataEmissao()
  {
    return( this.dataEmissao );
  }
  public int getDataVencimento()
  {
    return( this.dataVencimento );
  }
  public int getDataQuitacao()
  {
    return( this.dataQuitacao );
  }
  public String getDescricao()
  {
    return( this.descricao );
  }
  public Double getValor()
  {
    return( this.valor );
  }
  public Character getTipo()
  {
    return( this.tipo );
  }
  public void setCodLancamento( int codParam )
  {
    this.codLancamento = codParam;
  }
  public void setDataEmissao( int dataParam )
  {
    this.dataEmissao = dataParam;
  }
  public void setDataVencimento( int pData )
  {
    this.dataVencimento = pData;
  }
  public void setDataQuitacao( int pData )
  {
    this.dataQuitacao = pData;
  }
  public void setDescricao( String descricaoParam )
  {
    this.descricao = descricaoParam;
  }
  public void setValor( Double valorParam )
  {
    this.valor = valorParam;
  }
  public void setTipo( char pTipo )
  {
    this.tipo = pTipo;
  }
  /**
   * @return the cod_conta
   */
  public int getCodConta()
  {
    return codConta;
  }
  /**
   * @return the cod_caixa
   */
  public int getCodCaixa()
  {
    return codCaixa;
  }
  /**
   * @param codConta the codConta to set
   */
  public void setCodConta(int codConta)
  {
    this.codConta = codConta;
  }
  /**
   * @param codCaixa the codCaixa to set
   */
  public void setCodCaixa(int codCaixa)
  {
    this.codCaixa=codCaixa;
  }
}