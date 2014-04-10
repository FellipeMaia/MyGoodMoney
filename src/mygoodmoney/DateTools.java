/**
* @file DateTools.java
* @brief Contém métodos auxiliares para tratamentos de Data.
* @copyright 2014 Ricardo Montania. Todos os Direitos Reservados.
* @license Este projeto encontra-se sob a licensa GNU.
*/

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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @class DateTools
 */
public class DateTools
{
  public static final DateFormat formatterBR = new SimpleDateFormat( "dd/MM/yyyy" ); /**< Formato para data do Brasil */
  public static final DateFormat formatterUS = new SimpleDateFormat( "yyyy/MM/dd" ); /**< Formato para data do banco de dados */
  
  /**
  * @brief Otém o ano atual.
  * @return O ano atual.
  */
  public static String getSAnoAtual()
  {
    DateFormat f = new SimpleDateFormat( "yyyy" );
    String anoAtual = f.format( new Date() );
    return( anoAtual );
  }

  /**
  * @brief Formata uma data do tipo java.util.Date para o formato do Brasil.
  * @param pdtData A data a ser formatada.
  * @return A data formatada.
  */
  public static String formatDateToStringBR( Date pdtData )
  {
    return( formatterBR.format( pdtData ) );
  }

  /**
  * @brief Formata uma data do tipo java.util.Date para o formato norte americano.
  * @param pdtData A data a ser formatada.
  * @return A data formatada.
  */
  public static String formatDateToStringUS( Date pdtData )
  {
    return( formatterUS.format( pdtData ) );
  }

  /**
  * @brief Formata uma data do tipo java.util.Date para o formato do banco de dados.
  * @param pdtData A data a ser formatada.
  * @return A data formatada.
  */
  public static String formatDateToBD( Date pdtData )
  {
    return( formatterUS.format( pdtData ).toString().replaceAll( "/", "-"  ) );
  }

  /**
  * @brief Converte uma java.util.Date para o formato inteiro yyyyMMdd.
  * @param pdtData A data a ser convertida.
  * @return A data convertida, ou 0 se erro.
  */
  public static int parseDateToInteger( Date pdtData )
  {
    String sData = formatDateToStringBR( pdtData );
    sData = sData.replaceAll( "/", "" );
    sData = sData.substring(4,8) + sData.substring(2,4) + sData.substring(0,2);
    try
    {
      int iData = Integer.parseInt( sData );
      return( iData );
    }
    catch( NumberFormatException p )
    {
      System.out.println( "Impossivel obter int de: " + sData );
      return( 0 );
    }
  }

  /**
  * @brief Converte uma data inteira (yyyyMMdd) para java.util.Date.
  * @param piData A data a ser convertida.
  * @return A data convertida ou null se erro.
  */
  public static Date parseDataIntToDate( int piData )
  {
    String sData = piData + "";
    sData = sData.substring(0,4) + "/" + sData.substring(4,6) + "/" + sData.substring(6,8);
    try
    {
      Date dtData = formatterUS.parse( sData );
      return( dtData );
    }
    catch(ParseException ex)
    {
      System.out.println( "Impossivel obter data de: " + sData );
      return( null );
    }
  }

  /**
  * @brief Formata uma data inteira (yyyyMMdd) para o formato do Brasil.
  * @param piData A data a ser formatada.
  * @return A data formatada ou "00/00/00" se data inválida.
  */
  public static String formatDataIntToStringBR( int piData )
  {
    if( piData == 0 )
    {
      return( "00/00/00" );
    }
    
    String sData = piData + "";
    
    if( sData.length() == 7 )
      sData = "0" + sData;
    
    sData = sData.substring(6,8) + "/" + sData.substring(4,6) + "/" + sData.substring(0,4);
    return( sData );
  }

  /**
  * @brief Converte uma data formatada do Brasil para o formato inteiro (yyyyMMdd).
  * @param psData A data a ser convertida.
  * @return A data convertida.
  */
  public static int parseDataStringBRToInteger( String psData )
  {
    if( psData.indexOf( "/" ) != -1 )
      psData = psData.replace( "/", "" );
    
    psData = psData.substring(4,8) + psData.substring(2,4) + psData.substring(0,2);
    
    return( Integer.parseInt( psData ) );
  }

  /**
  * @brief Soma ou subtrai um mês em uma java.util.Date.
  * @param pdtData A data a base.
  * @param pcOperacao A operação a ser efetuada ('+' ou '-').
  * @return A data resultante.
  */
  public static Date somarSubtrairUmMes( Date pdtData, char pcOperacao )
  {
    Date dtDataRetorno = new Date();
    String sDataAtual = formatterBR.format( pdtData );
    
    Integer iProximoMes = Integer.parseInt( sDataAtual.substring(3,5) );
    
    if( pcOperacao == '+' )
    {
      iProximoMes++;
      
      if( iProximoMes > 12 )
      {
        iProximoMes = 1;
        int iAno = Integer.parseInt( sDataAtual.substring( 6,10 ) );
        iAno++;
        sDataAtual = sDataAtual.substring(0, 6) + iAno;
      }
    }
    else if( pcOperacao == '-' )
    {
      iProximoMes--;
      
      if( iProximoMes < 1 )
      {
        iProximoMes = 12;
        int iAno = Integer.parseInt( sDataAtual.substring( 6,10 ) );
        iAno--;
        sDataAtual = sDataAtual.substring(0, 6) + iAno;
      }
    }

    String sMes = (iProximoMes < 10)? "0"+iProximoMes : iProximoMes+"";
    String sDataSeguinte = sDataAtual.substring(0,2) + "/" + sMes + "/" + sDataAtual.substring(6,10);
    
    try
    {
      if( !sDataSeguinte.startsWith( "01" ) )
        sDataSeguinte = DateTools.getUltimaDataMes( sDataSeguinte );
      
      dtDataRetorno = formatterBR.parse( sDataSeguinte );
      
      return( dtDataRetorno );
    }
    catch( ParseException e )
    {
      System.out.println( "Erro ao somar ou subtrair mes: " + e.getLocalizedMessage() );
      return( dtDataRetorno );
    }
  }

  /**
  * @brief Obtém o último dia de um mês.
  * @param psData A data contendo o mês a ser obtido o dia.
  * @return O último dia do mês.
  */
  public static String getUltimaDataMes( String psData )
  {
    switch( psData.substring( 3,5 ) )
    {
      case "01":
      {
        psData = "31" + psData.substring( 2, psData.length() );
        return( psData );
      }
      case "02":
      {
        psData = "28" + psData.substring( 2, psData.length() );
        return( psData );
      }
      case "03":
      {
        psData = "31" + psData.substring( 2, psData.length() );
        return( psData );
      }
      case "04":
      {
        psData = "30" + psData.substring( 2, psData.length() );
        return( psData );
      }
      case "05":
      {
        psData = "31" + psData.substring( 2, psData.length() );
        return( psData );
      }
      case "06":
      {
        psData = "30" + psData.substring( 2, psData.length() );
        return( psData );
      }
      case "07":
      {
        psData = "31" + psData.substring( 2, psData.length() );
        return( psData );
      }
      case "08":
      {
        psData = "31" + psData.substring( 2, psData.length() );
        return( psData );
      }
      case "09":
      {
        psData = "30" + psData.substring( 2, psData.length() );
        return( psData );
      }
      case "10":
      {
        psData = "31" + psData.substring( 2, psData.length() );
        return( psData );
      }
      case "11":
      {
        psData = "30" + psData.substring( 2, psData.length() );
        return( psData );
      }
      case "12":
      {
        psData = "31" + psData.substring( 2, psData.length() );
        return( psData );
      }
      default:
      {
        return( psData );
      }
    }
  }

  /**
  * @brief Cálculo das parcelas vincendas a partir de um vencimento,
  * @param piVencBase O primeiro vencimento, a ser usado como base.
  * @param piNumDeParcelas O número total de parcelas, incluindo a parcela base.
  * @param piPeriodo O período de repetição (dias, semanas, meses).
  * @return Uma lista contendo os vencimentos calculados.
  */
  public static ArrayList<Integer> calcularVencimentos( int piVencBase, int piNumDeParcelas, int piPeriodo )
  {
    ArrayList<Integer> vencList = new ArrayList<>();

    for( int i=0; i<piNumDeParcelas; i++ )
    {
      int dataVcto = somarDiasData( piVencBase, (i*piPeriodo) );
      vencList.add( dataVcto );
    }
    return( vencList );
  }

  /**
  * @brief Obtém o mês de uma data inteira (yyyyMMdd).
  * @param piData A data a ser obtida o mês.
  * @return O mês da data informada.
  */
  public static int getMesFromDataInt( int piData )
  {
    String sMes = ("" + piData).substring( 4, 6 );
    return( Integer.parseInt( sMes ) );
  }

  /**
  * @brief Obtém o dia de uma data inteira (yyyyMMdd).
  * @param piData A data a ser obtida o mês.
  * @return O dia da data informada.
  */
  public static int getDiaFromDataInt( int piData )
  {
    String sDia = ("" + piData).substring( 6, 8 );
    return( Integer.parseInt( sDia ) );
  }

  /**
  * @brief Obtém o ano de uma data inteira (yyyyMMdd).
  * @param piData A data a ser obtida o mês.
  * @return O ano da data informada.
  */
  public static int getAnoFromDataInt( int piData )
  {
    String sAno = ("" + piData).substring( 0, 4 );
    return( Integer.parseInt( sAno ) );
  }

  /**
  * @brief Soma um número de dias a uma data.
  * @param piDataBase A data base qual será somada os dias.
  * @param piNumDias O número de dias a ser somado.
  * @return A data resultante da soma, se a data for válida, ou data base.
  */
  public static int somarDiasData( int piDataBase, int piNumDias )
  {
    if( piNumDias == 0 )
    {
      return( piDataBase );
    }
    
    System.out.println( "somando " + piNumDias + " dias a data " + piDataBase );
    
    DateFormat sd = formatterBR;
    
    int iDia = getDiaFromDataInt( piDataBase );
    int iMes = getMesFromDataInt( piDataBase );
    int iAno = getAnoFromDataInt( piDataBase );
    
    Calendar c = new GregorianCalendar( iAno, iMes-1, iDia );
    
    c.add( Calendar.DAY_OF_MONTH, piNumDias );
    
    String sDataFinal = sd.format( c.getTime() );
    
    System.out.println( "resultado: " + sDataFinal );
    return( parseDataStringBRToInteger( sDataFinal ) );
  }
}