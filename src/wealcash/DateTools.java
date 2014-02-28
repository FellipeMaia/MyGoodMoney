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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 *
 * @author Ricardo
 */
public class DateTools
{
  public static final DateFormat formatterBR = new SimpleDateFormat( "dd/MM/yyyy" );
  public static final DateFormat formatterUS = new SimpleDateFormat( "yyyy/MM/dd" );
  
  public static String getAnoAtual()
  {
    DateFormat f = new SimpleDateFormat( "yyyy" );
    String anoAtual = f.format( new Date() );
    return( anoAtual );
  }
  public static String formatDateToStringBR( Date dataParam )
  {
    return( formatterBR.format( dataParam ) );
  }
  public static String formatDateToStringUS( Date dataParam )
  {
    return( formatterUS.format( dataParam ) );
  }
  public static String formatDateToBD( Date pDate )
  {
    return( formatterUS.format( pDate ).toString().replaceAll( "/", "-"  ) );
  }
  public static int parseDateToInteger( Date pDate )
  {
    String data = formatDateToStringBR( pDate );
    data = data.replaceAll( "/", "" );
    data = data.substring(4,8) + data.substring(2,4) + data.substring(0,2);
    try
    {
      int dataInt = Integer.parseInt( data );
      return( dataInt );
    }
    catch( NumberFormatException p )
    {
      System.out.println( "Impossivel obter int de: " + data );
      return( 0 );
    }
  }
  public static Date parseDataIntToDate( int data )
  {
    String dataStr = data + "";
    dataStr = dataStr.substring(0,4) + "/" + dataStr.substring(4,6) + "/" + dataStr.substring(6,8);
    try
    {
      Date datar = formatterUS.parse( dataStr );
      return( datar );
    }
    catch(ParseException ex)
    {
      System.out.println( "Impossivel obter data de: " + dataStr );
      return( null );
    }
  }
  public static String formatDataIntToStringBR( int pData )
  {
    if( pData == 0 )
    {
      return( "00/00/00" );
    }
    
    String data = pData + "";
    
    if( data.length() == 7 )
      data = "0" + data;
    
    data = data.substring(6,8) + "/" + data.substring(4,6) + "/" + data.substring(0,4);
    return( data );
  }
  public static int parseDataStringBRToInteger( String pDate )
  {
    if( pDate.indexOf( "/" ) != -1 )
      pDate = pDate.replace( "/", "" );
    
    pDate = pDate.substring(4,8) + pDate.substring(2,4) + pDate.substring(0,2);
    
    return( Integer.parseInt( pDate ) );
  }
  public static Date somarSubtrairUmMes( Date pData, char pOperacao )
  {
    Date dataRetorno = new Date();
    String dataAtual = formatterBR.format( pData );
    
    Integer proximoMes = Integer.parseInt( dataAtual.substring(3,5) );
    
    if( pOperacao == '+' )
    {
      proximoMes++;
      
      if( proximoMes > 12 )
      {
        proximoMes = 1;
        int ano = Integer.parseInt( dataAtual.substring( 6,10 ) );
        ano++;
        dataAtual = dataAtual.substring(0, 6) + ano;
      }
    }
    else if( pOperacao == '-' )
    {
      proximoMes--;
      
      if( proximoMes < 1 )
      {
        proximoMes = 12;
        int ano = Integer.parseInt( dataAtual.substring( 6,10 ) );
        ano--;
        dataAtual = dataAtual.substring(0, 6) + ano;
      }
    }

    String mes = (proximoMes < 10)? "0"+proximoMes : proximoMes+"";
    String dataSeguinte = dataAtual.substring(0,2) + "/" + mes + "/" + dataAtual.substring(6,10);
    
    try
    {
      if( !dataSeguinte.startsWith( "01" ) )
        dataSeguinte = DateTools.getUltimaDataMes( dataSeguinte );
      
      dataRetorno = formatterBR.parse( dataSeguinte );
      
      return( dataRetorno );
    }
    catch( ParseException e )
    {
      System.out.println( "Erro ao somar ou subtrair mes: " + e.getLocalizedMessage() );
      return( dataRetorno );
    }
  }
  public static String getUltimaDataMes( String pData )
  {
    switch( pData.substring( 3,5 ) )
    {
      case "01":
      {
        pData = "31" + pData.substring( 2, pData.length() );
        return( pData );
      }
      case "02":
      {
        pData = "28" + pData.substring( 2, pData.length() );
        return( pData );
      }
      case "03":
      {
        pData = "31" + pData.substring( 2, pData.length() );
        return( pData );
      }
      case "04":
      {
        pData = "30" + pData.substring( 2, pData.length() );
        return( pData );
      }
      case "05":
      {
        pData = "31" + pData.substring( 2, pData.length() );
        return( pData );
      }
      case "06":
      {
        pData = "30" + pData.substring( 2, pData.length() );
        return( pData );
      }
      case "07":
      {
        pData = "31" + pData.substring( 2, pData.length() );
        return( pData );
      }
      case "08":
      {
        pData = "31" + pData.substring( 2, pData.length() );
        return( pData );
      }
      case "09":
      {
        pData = "30" + pData.substring( 2, pData.length() );
        return( pData );
      }
      case "10":
      {
        pData = "31" + pData.substring( 2, pData.length() );
        return( pData );
      }
      case "11":
      {
        pData = "30" + pData.substring( 2, pData.length() );
        return( pData );
      }
      case "12":
      {
        pData = "31" + pData.substring( 2, pData.length() );
        return( pData );
      }
      default:
      {
        return( pData );
      }
    }
  }
  public static ArrayList<Integer> calcularVencimentos( int pVenc, int pVezes, int pPeriodo )
  {
    ArrayList<Integer> vencList = new ArrayList<>();
    
    for( int i=0; i<pVezes; i++ )
    {
      int dataVcto = somarDiasData( pVenc, (i*pPeriodo) );
      vencList.add( dataVcto );
    }
    
    return( vencList );
  }
  public static int getMesFromDataInt( int pData )
  {
    String mes = ("" + pData).substring( 4, 6 );
    return( Integer.parseInt( mes ) );
  }
  public static int getDiaFromDataInt( int pData )
  {
    String dia = ("" + pData).substring( 6, 8 );
    return( Integer.parseInt( dia ) );
  }
  public static int getAnoFromDataInt( int pData )
  {
    String ano = ("" + pData).substring( 0, 4 );
    return( Integer.parseInt( ano ) );
  }
  public static int somarDiasData( int pVenc, int pDias )
  {
    if( pDias == 0 )
    {
      return( pVenc );
    }
    
    System.out.println( "somando " + pDias + " dias a data " + pVenc );
    
    DateFormat sd = formatterBR;
    
    int dia = getDiaFromDataInt( pVenc );
    int mes = getMesFromDataInt( pVenc );
    int ano = getAnoFromDataInt( pVenc );
    
    Calendar c = new GregorianCalendar( ano, mes-1, dia );
    
    c.add( Calendar.DAY_OF_MONTH, pDias );
    
    String dataFinal = sd.format( c.getTime() );
    
    System.out.println( "resultado: " + dataFinal );
    return( parseDataStringBRToInteger( dataFinal ) );
  }
}