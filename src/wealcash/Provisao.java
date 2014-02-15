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
import java.math.BigDecimal;
import java.util.Date;
/**
 *
 * @author Ricardo
 */
public class Provisao
{
  Date       dataEmissao;
  Date       dataVencimento;
  String     descricao;
  BigDecimal valor;
  Integer    codConta;
  Integer    codCaixa;
  
  public Provisao()
  {
    this.dataEmissao = null;
    this.dataVencimento = null;
    this.descricao = null;
    this.valor = null;
    this.codConta = null;
    this.codCaixa = null;
  }
  
  /**
   * @return the dataEmissao
   */
  public Date getDataEmissao()
  {
    return dataEmissao;
  }
  /**
   * @return the dataVencimento
   */
  public Date getDataVencimento()
  {
    return dataVencimento;
  }
  /**
   * @return the descricao
   */
  public String getDescricao()
  {
    return descricao;
  }
  /**
   * @return the valor
   */
  public BigDecimal getValor()
  {
    return valor;
  }
  /**
   * @return the codConta
   */
  public Integer getCodConta()
  {
    return codConta;
  }
  /**
   * @return the codCaixa
   */
  public Integer getCodCaixa()
  {
    return codCaixa;
  }
  /**
   * @param dataEmissao the dataEmissao to set
   */
  public void setDataEmissao(Date dataEmissao)
  {
    this.dataEmissao=dataEmissao;
  }
  /**
   * @param dataVencimento the dataVencimento to set
   */
  public void setDataVencimento(Date dataVencimento)
  {
    this.dataVencimento=dataVencimento;
  }
  /**
   * @param descricao the descricao to set
   */
  public void setDescricao(String descricao)
  {
    this.descricao=descricao;
  }
  /**
   * @param valor the valor to set
   */
  public void setValor(BigDecimal valor)
  {
    this.valor=valor;
  }
  /**
   * @param codConta the codConta to set
   */
  public void setCodConta(Integer codConta)
  {
    this.codConta=codConta;
  }
  /**
   * @param codCaixa the codCaixa to set
   */
  public void setCodCaixa(Integer codCaixa)
  {
    this.codCaixa=codCaixa;
  }
}
