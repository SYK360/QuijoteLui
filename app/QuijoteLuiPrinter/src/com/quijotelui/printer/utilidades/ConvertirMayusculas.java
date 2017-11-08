/*
 * Copyright (C) 2015 jorjoluiso
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
package com.quijotelui.printer.utilidades;

/**
 *
 * @author jorjoluiso
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class ConvertirMayusculas extends DocumentFilter
{
  @Override
  public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr)
    throws BadLocationException
  {
    fb.insertString(offset, text.toUpperCase(), attr);
  }

  @Override
  public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
  {
    fb.replace(offset, length, text.toUpperCase(), attrs);
  }
}