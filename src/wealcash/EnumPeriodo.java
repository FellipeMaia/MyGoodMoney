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
public enum EnumPeriodo
{
  DIAS   ( 1, "Dia(s)" ),
  SEMANAS( 7, "Semana(s)" ),
  MESES  ( 30, "Mês(es)" ),
  ANOS   ( 365, "Ano(s)");
  
  private final int    dias;
  private final String descricao;
  
  private EnumPeriodo( int diasParam, String descricaoParam )
  {
    this.dias = diasParam;
    this.descricao = descricaoParam;
  }
  
  public int getDias()
  {
    return( this.dias );
  }
  
  public String getDescricao()
  {
    return( this.descricao );
  }
  
  @Override
  public String toString()
  {
    return( this.descricao );
  }
}