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
public class Conta
{
  Integer   cod_conta;
  String    nome;
  Character tipo;
  
  public Conta()
  {
    this.cod_conta = 0;
    this.nome = "";
    this.tipo = ' ';
  }
  /**
   * @return the cod_conta
   */
  public Integer getCod_conta()
  {
    return cod_conta;
  }
  /**
   * @return the nome
   */
  public String getNome()
  {
    return nome;
  }
  /**
   * @return the tipo
   */
  public Character getTipo()
  {
    return tipo;
  }
  /**
   * @param cod_conta the cod_conta to set
   */
  public void setCod_conta(Integer cod_conta)
  {
    this.cod_conta=cod_conta;
  }
  /**
   * @param nome the nome to set
   */
  public void setNome(String nome)
  {
    this.nome=nome;
  }
  /**
   * @param tipo the tipo to set
   */
  public void setTipo(Character tipo)
  {
    this.tipo=tipo;
  }
}
