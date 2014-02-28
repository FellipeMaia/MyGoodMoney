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
  int       codConta;
  String    nome;
  Character tipo;
  
  public Conta()
  {
    this.codConta = 0;
    this.nome = "";
    this.tipo = ' ';
  }
  /**
   * @return the codConta
   */
  public int getCodConta()
  {
    return codConta;
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
   * @param codConta the codConta to set
   */
  public void setCodConta(int codConta)
  {
    this.codConta = codConta;
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