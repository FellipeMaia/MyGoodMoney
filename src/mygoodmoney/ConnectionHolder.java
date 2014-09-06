/**
 * @file ConnectionHolder.java
 * @brief Contém métodos para conexão com banco de dados.
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

import java.sql.Connection;

public class ConnectionHolder {
  private static BD instance;
  
  protected ConnectionHolder() {
    //
  }
  public static Connection getConnection() {
    if( instance == null ) {
      System.out.println( "Requisitando conexão.." );
      instance = new BD();
    }
    return( instance.getConnection() );
  }
  public static void criarTabelas() {
    if( instance == null ) {
      System.out.println( "Requisitando conexão.." );
      instance = new BD();
    }
    try {
      instance.criarBancoDeDados( "" );
    }
    catch( ClassNotFoundException cl ){}
  }
}