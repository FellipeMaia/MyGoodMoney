/**
* @file Mensagem.java
* @brief Contém métodos estáticos de mensagens (diálogos) diversos.
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

import javax.swing.JOptionPane;
import java.awt.Component;

/**
 * @class Mensagem
 */
public class Mensagem
{
  /**
  * @brief Exibe uma mensagem do tipo JOptionPane.INFORMATION_MESSAGE. 
  * @param psMensagem A mensagem a ser exibida.
  * @param pComponent O component vinculado ao diálogo.
  */
  public static void info( String psMensagem, Component pComponent )
  {
    JOptionPane.showMessageDialog
    (
      pComponent,
      psMensagem,
      "Informação",
      JOptionPane.INFORMATION_MESSAGE
    );
  }

  /**
  * @brief Exibe uma mensagem to tipo JOptionPane.ERROR_MESSAGE. 
  * @param psMensagem A mensagem a ser exibida.
  * @param pComponent O component vinculado ao diálogo.
  */
  public static void erro( String psMensagem, Component pComponent )
  {
    JOptionPane.showMessageDialog
    (
      pComponent,
      psMensagem,
      "Erro",
      JOptionPane.ERROR_MESSAGE
    );
  }

  /**
  * @brief Exibe uma mensagem do tipo confirmação. 
  * @param psMensagem A mensagem a ser exibida.
  * @param pComponent O component vinculado ao diálogo.
  * @return true se resposta igual a JOptionPane.YES_OPTION ou false se não.
  */
  public static boolean confirmacao( String psMensagem, Component pComponent )
  {
    int resposta =
    JOptionPane.showConfirmDialog
    (
      pComponent,
      psMensagem,
      "Confirmação",
      JOptionPane.YES_NO_OPTION
    );
    
    return( resposta == JOptionPane.YES_OPTION );
  }

  /**
  * @brief Exibe uma mensagem do tipo JOptionPane.WARNING_MESSAGE. 
  * @param psMensagem A mensagem a ser exibida.
  * @param pComponent O component vinculado ao diálogo.
  */
  public static void aviso( String psMensagem, Component pComponent )
  {
    JOptionPane.showMessageDialog
    (
      pComponent,
      psMensagem,
      "Aviso",
      JOptionPane.WARNING_MESSAGE
    );
  }
}