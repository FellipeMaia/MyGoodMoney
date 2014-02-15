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
public enum EnumTipoConta
{
  CREDITO('C',"Crédito"),
  DEBITO('D',"Débito");
  
  private final char codigo;
  private final String descricao;
  
  private EnumTipoConta( char codigo, String descricao )
  {
    this.codigo = codigo;
    this.descricao = descricao;
  }
  
  public char getCodigo()
  {
    return( this.codigo );
  }
  
  public String getDescricao()
  {
      return( this.descricao );
  }
  
  public static EnumTipoConta getPorCodigo( char codigo )
  {
    for( EnumTipoConta valor : EnumTipoConta.values() )
    {
      if( valor.codigo == codigo )
      {
        return( valor );
      }
    }
    return( null );
  }
  
  public static EnumTipoConta getPorDescricao( String descr )
  {
    for( EnumTipoConta valor : EnumTipoConta.values() )
    {
      if( valor.descricao.equals( descr ) )
      {
        return( valor );
      }
    }
    return( null );
  }
  
  @Override
  public String toString()
  {
    return( this.descricao );
  }
}
