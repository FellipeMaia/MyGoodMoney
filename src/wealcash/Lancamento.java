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

import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class Lancamento
{
  private Date      dataEmissao;
  private String    descricao;
  private Double    valor;
  private Integer   codConta;
  private Integer   codCaixa;
  
  public Lancamento()
  {
    this.dataEmissao = null;
    this.descricao = null;
    this.valor = null;
    this.codConta = null;
    this.codCaixa = null;
  }
  
  public Date getDataEmissao()
  {
    return( this.dataEmissao );
  }
  public String getDescricao()
  {
    return( this.descricao );
  }
  public Double getValor()
  {
    return( this.valor );
  }
  public void setDataEmissao( Date dataParam )
  {
    this.dataEmissao = dataParam;
  }
  public void setDescricao( String descricaoParam )
  {
    this.descricao = descricaoParam;
  }
  public void setValor( Double valorParam )
  {
    this.valor = valorParam;
  }
  /**
   * @return the cod_conta
   */
  public Integer getCodConta()
  {
    return codConta;
  }
  /**
   * @return the cod_caixa
   */
  public Integer getCodCaixa()
  {
    return codCaixa;
  }
  /**
   * @param cod_conta the cod_conta to set
   */
  public void setCod_conta(Integer cod_conta)
  {
    this.codConta = cod_conta;
  }
  /**
   * @param cod_caixa the cod_caixa to set
   */
  public void setCod_caixa(Integer cod_caixa)
  {
    this.codCaixa=cod_caixa;
  }
}