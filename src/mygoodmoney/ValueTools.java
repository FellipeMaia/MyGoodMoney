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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class ValueTools {
  public static String format( Double valorParam ) {
    NumberFormat df = new DecimalFormat( "#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR") ) );
    return( "R$ " + df.format( valorParam ) );
  }
  public static String formatToField( double pValor ) {
    // tamanho = 16
    NumberFormat df = new DecimalFormat( "#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR") ) );
    String valorString = df.format( pValor );
    
    while( valorString.length() < 14 ) {
      valorString = " " + valorString;
    }
    return( "R$" + valorString );
  }
  public static String formatToFieldPerc( double pValor ) {
    // tamanho = 16
    NumberFormat df = new DecimalFormat( "#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR") ) );
    String valorString = df.format( pValor );
    while( valorString.length() < 15 ) {
      valorString = " " + valorString;
    }
    return( "%" + valorString );
  }
  public static Double unformat( String valorParam ) {
    if( valorParam.indexOf( "R$" ) != -1 )
      valorParam = valorParam.replace( "R$", "" );
    
    valorParam = valorParam.replaceAll( ",", "." );
    
    String novoValor = "";
    int totalPontos = 0;

    for( int i=valorParam.length()-1; i>=0; i-- ) {
      if( valorParam.charAt(i) == '.' ) {
        totalPontos++;
      }
      
      if( totalPontos > 1 && valorParam.charAt(i) == '.' ) {
        continue;
      }
       
      novoValor = valorParam.charAt(i) + "" + novoValor;
    }
    return( Double.parseDouble( novoValor ) );
  }
}