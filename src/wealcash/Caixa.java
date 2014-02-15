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
/**
 *
 * @author Ricardo
 */
public class Caixa
{
  Integer cod_caixa;
  String  nome;
  BigDecimal saldo;
  
  public Caixa()
  {
    this.cod_caixa = 0;
    this.nome = "";
    this.saldo = BigDecimal.ZERO;
  }
  
  /**
   * @return the cod_caixa
   */
  public Integer getCod_caixa()
  {
    return cod_caixa;
  }
  /**
   * @return the nome
   */
  public String getNome()
  {
    return nome;
  }
  /**
   * @return the saldo
   */
  public BigDecimal getSaldo()
  {
    return saldo;
  }
  /**
   * @param cod_caixa the cod_caixa to set
   */
  public void setCod_caixa(Integer cod_caixa)
  {
    this.cod_caixa=cod_caixa;
  }
  /**
   * @param nome the nome to set
   */
  public void setNome(String nome)
  {
    this.nome=nome;
  }
  /**
   * @param saldo the saldo to set
   */
  public void setSaldo(BigDecimal saldo)
  {
    this.saldo=saldo;
  }
}
