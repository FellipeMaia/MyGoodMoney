/**
* @file DateTools.java
* @brief ContXm mXtodos auxiliares para tratamentos de Data.
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
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.	If not, see <http://www.gnu.org/licenses/>.
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
public class DateTools {
	public static final DateFormat formatterBR = new SimpleDateFormat( "dd/MM/yyyy" ); /**< Formato para data do Brasil */
	public static final DateFormat formatterUS = new SimpleDateFormat( "yyyy/MM/dd" ); /**< Formato para data do banco de dados */
	/**
	* @brief ObtXm o ano atual.
	* @return O ano atual.
	*/
	public static String getSAnoAtual() {
		DateFormat f = new SimpleDateFormat( "yyyy" );
		String anoAtual = f.format( new Date() );
		return( anoAtual );
	}
	/**
	* @brief Formata uma data do tipo java.util.Date para o formato do Brasil.
	* @param pData A data a ser formatada.
	* @return A data formatada.
	*/
	public static String formatDateToStringBR( Date pData ) {
		return( formatterBR.format( pData ) );
	}
	/**
	* @brief Formata uma data do tipo java.util.Date para o formato norte americano.
	* @param pData A data a ser formatada.
	* @return A data formatada.
	*/
	public static String formatDateToStringUS( Date pData ) {
		return( formatterUS.format( pData ) );
	}
	/**
	* @brief Formata uma data do tipo java.util.Date para o formato do banco de dados.
	* @param pData A data a ser formatada.
	* @return A data formatada.
	*/
	public static String formatDateToBD( Date pData ) {
		return( formatterUS.format( pData ).toString().replaceAll( "/", "-"	 ) );
	}
	/**
	* @brief Converte uma java.util.Date para o formato inteiro yyyyMMdd.
	* @param pData A data a ser convertida.
	* @return A data convertida, ou 0 se erro.
	*/
	public static int parseDateToInteger( Date pData ) {
		String sData = formatDateToStringBR( pData );
		sData = sData.replaceAll( "/", "" );
		sData = sData.substring(4,8) + sData.substring(2,4) + sData.substring(0,2);
		try {
			int iData = Integer.parseInt( sData );
			return( iData );
		}
		catch( NumberFormatException p ) {
			System.out.println( "NumberFormatException: " + p.getMessage() );
			return( 0 );
		}
	}
	/**
	* @brief Converte uma data inteira (yyyyMMdd) para java.util.Date.
	* @param pData A data a ser convertida.
	* @return A data convertida ou null se erro.
	*/
	public static Date parseDataIntToDate( int pData ) {
		String sData = pData + "";
		sData = sData.substring(0,4) + "/" + sData.substring(4,6) + "/" + sData.substring(6,8);
		try {
			Date dtData = formatterUS.parse( sData );
			return( dtData );
		}
		catch( ParseException ex ) {
			System.out.println( "ParseException: " + ex.getMessage() );
			return( null );
		}
	}
	/**
	* @brief Formata uma data inteira (yyyyMMdd) para o formato do Brasil.
	* @param pData A data a ser formatada.
	* @return A data formatada ou "00/00/00" se data invXlida.
	*/
	public static String formatDataIntToStringBR( int pData ) {
		if( pData == 0 ) {
			return( "00/00/00" );
		}
		String sData = pData + "";
		if( sData.length() == 7 ) {
			sData = "0" + sData;
		}
		sData = sData.substring(6,8) + "/" + sData.substring(4,6) + "/" + sData.substring(0,4);
		return( sData );
	}
	/**
	* @brief Converte uma data formatada do Brasil para o formato inteiro (yyyyMMdd).
	* @param pData A data a ser convertida.
	* @return A data convertida.
	*/
	public static int parseDataStringBRToInteger( String pData ) {
		if( pData.indexOf( "/" ) != -1 ) {
			pData = pData.replace( "/", "" );
		}
		pData = pData.substring(4,8) + pData.substring(2,4) + pData.substring(0,2);
		return( Integer.parseInt( pData ) );
	}
	/**
	* @brief Soma ou subtrai um mXs em uma java.util.Date.
	* @param pData A data a base.
	* @param pOperacao A operaXXo a ser efetuada ('+' ou '-').
	* @return A data resultante.
	*/
	public static Date somarSubtrairUmMes( Date pData, char pOperacao ) {
		Date dataRetorno = new Date();
		String dataAtual = formatterBR.format( pData );
		Integer proximoMes = Integer.parseInt( dataAtual.substring( 3, 5 ) );
		if( pOperacao == '+' ) {
			proximoMes++;
			if( proximoMes > 12 ) {
				proximoMes = 1;
				int ano = Integer.parseInt( dataAtual.substring( 6,10 ) );
				ano++;
				dataAtual = dataAtual.substring( 0, 6 ) + ano;
			}
		}
		else if( pOperacao == '-' ) {
			proximoMes--;
			if( proximoMes < 1 ) {
				proximoMes = 12;
				int ano = Integer.parseInt( dataAtual.substring( 6,10 ) );
				ano--;
				dataAtual = dataAtual.substring( 0, 6 ) + ano;
			}
		}
		String sMes = (proximoMes < 10)? "0" + proximoMes : proximoMes + "";
		String sDataSeguinte = dataAtual.substring( 0, 2 ) + "/" + sMes + "/" + dataAtual.substring( 6, 10 );
		try {
			if( !sDataSeguinte.startsWith( "01" ) ) {
				sDataSeguinte = DateTools.getUltimaDataMes( sDataSeguinte );
			}
			dataRetorno = formatterBR.parse( sDataSeguinte );
			return( dataRetorno );
		}
		catch( ParseException e ) {
			System.out.println( "Erro ao somar ou subtrair mes: " + e.getLocalizedMessage() );
			return( dataRetorno );
		}
	}
	/**
	* @brief ObtXm o Xltimo dia de um mXs.
	* @param pData A data contendo o mXs a ser obtido o dia.
	* @return O Xltimo dia do mXs.
	*/
	public static String getUltimaDataMes( String pData ) {
		switch( pData.substring( 3,5 ) ) {
			case "01":
			case "03":
			case "05":
			case "07":
			case "08":
			case "10":
			case "12": {
				pData = "31" + pData.substring( 2 ); // pData.length()
				return( pData );
			}
			case "02": {
				pData = "28" + pData.substring( 2 );
				return( pData );
			}
			case "04":
			case "06":
			case "09":
			case "11": {
				pData = "30" + pData.substring( 2 );
				return( pData );
			}
			default: {
				return( pData );
			}
		}
	}
	/**
	* @brief CXlculo das parcelas vincendas a partir de um vencimento,
	* @param pVencBase O primeiro vencimento, a ser usado como base.
	* @param pNumDeParcelas O nXmero total de parcelas, incluindo a parcela base.
	* @param pPeriodo O perXodo de repetiXXo (dias, semanas, meses).
	* @return Uma lista contendo os vencimentos calculados.
	*/
	public static ArrayList<Integer> calcularVencimentos( int pVencBase, int pNumDeParcelas, int pPeriodo ) {
		ArrayList<Integer> vencList = new ArrayList<>();
		for( int i=0; i<pNumDeParcelas; i++ ) {
			int dataVcto = somarDiasData( pVencBase, (i * pPeriodo) );
			vencList.add( dataVcto );
		}
		return( vencList );
	}
	/**
	* @brief ObtXm o mXs de uma data inteira (yyyyMMdd).
	* @param pData A data a ser obtida o mXs.
	* @return O mXs da data informada.
	*/
	public static int getMesFromDataInt( int pData ) {
		String sMes = ( "" + pData ).substring( 4, 6 );
		return( Integer.parseInt( sMes ) );
	}
	/**
	* @brief ObtXm o dia de uma data inteira (yyyyMMdd).
	* @param pData A data a ser obtida o mXs.
	* @return O dia da data informada.
	*/
	public static int getDiaFromDataInt( int pData ) {
		String dia = ("" + pData).substring( 6, 8 );
		return( Integer.parseInt( dia ) );
	}
	/**
	* @brief ObtXm o ano de uma data inteira (yyyyMMdd).
	* @param pData A data a ser obtida o mXs.
	* @return O ano da data informada.
	*/
	public static int getAnoFromDataInt( int pData ) {
		String ano = ("" + pData).substring( 0, 4 );
		return( Integer.parseInt( ano ) );
	}
	/**
	* @brief Soma um nXmero de dias a uma data.
	* @param pDataBase A data base qual serX somada os dias.
	* @param pNumDias O nXmero de dias a ser somado.
	* @return A data resultante da soma, se a data for vXlida, ou data base.
	*/
	public static int somarDiasData( int pDataBase, int pNumDias ) {
		if( pNumDias == 0 ) {
			return( pDataBase );
		}
		System.out.println( "Somando " + pNumDias + " dias a data " + pDataBase );
		int dia = getDiaFromDataInt( pDataBase );
		int mes = getMesFromDataInt( pDataBase );
		int ano = getAnoFromDataInt( pDataBase );
		Calendar c = new GregorianCalendar( ano, mes-1, dia );
		c.add( Calendar.DAY_OF_MONTH, pNumDias );
		String sDataFinal = formatterBR.format( c.getTime() );
		System.out.println( "resultado: " + sDataFinal );
		return( parseDataStringBRToInteger( sDataFinal ) );
	}
	public static java.util.Date getPrimeiraDataMesAtual() throws ParseException {
		DateFormat mesAno	 = new SimpleDateFormat( "MM/yyyy" );
		String mesAnoStr	 = mesAno.format( new Date() );
		DateFormat diaMesAno = DateTools.formatterBR;
		return( diaMesAno.parse( "01/" + mesAnoStr ) );
	}
	public static java.util.Date getUltimaDataMesAtual() throws ParseException {
		DateFormat mesAno	 = new SimpleDateFormat( "MM/yyyy" );
		String mesAnoStr	 = mesAno.format( new Date() );
		String ultimoDia	 = "";
		DateFormat diaMesAno = DateTools.formatterBR;
		int mes = Integer.parseInt( mesAnoStr.substring( 0, 2 ) );
		switch( mes ) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:{ ultimoDia = "31/"; break; }
			case 2: { ultimoDia = "28/"; break; }
			case 4:
			case 6:
			case 9:
			case 11:{ ultimoDia = "30/"; break; }
		}
		return( diaMesAno.parse( ultimoDia + mesAnoStr ) );
	}
	public static int getDataAtual() {
		return( parseDateToInteger( new Date() ) );
	}
}