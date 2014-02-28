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
public class Caixa
{
  int     codCaixa;
  String  nome;
  Double  saldo;
  
  public Caixa()
  {
    this.codCaixa = 0;
    this.nome = "";
    this.saldo = Double.NaN;
  }
  
  /**
   * @return the codCaixa
   */
  public int getCodCaixa()
  {
    return codCaixa;
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
  public Double getSaldo()
  {
    return saldo;
  }
  /**
   * @param codCaixa the codCaixa to set
   */
  public void setCodCaixa(int codCaixa)
  {
    this.codCaixa=codCaixa;
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
  public void setSaldo(Double saldo)
  {
    this.saldo=saldo;
  }
}