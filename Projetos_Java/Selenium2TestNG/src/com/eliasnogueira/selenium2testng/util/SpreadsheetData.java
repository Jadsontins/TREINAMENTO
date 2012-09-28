package com.eliasnogueira.selenium2testng.util;

import java.io.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

/**
 * Classe de utilizario para ler dados de uma planilha em Excel. Para que
 * funcione e necessario a utilizacao de uma biblioteca java chamada Apache POI
 * http://poi.apache.org/
 * 
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 * 
 */
public class SpreadsheetData {

	public static String[][] readExcelData(String sheetName, String filePath, String tableName) {
		String[][] testData = null;

		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
			HSSFSheet sheet = workbook.getSheet(sheetName);
			HSSFCell[] boundaryCells = findCell(sheet, tableName);
			HSSFCell startCell = boundaryCells[0];
			HSSFCell endCell = boundaryCells[1];
			int startRow = startCell.getRowIndex() + 1;
			int endRow = endCell.getRowIndex() - 1;
			int startCol = startCell.getColumnIndex() + 1;
			int endCol = endCell.getColumnIndex() - 1;

			testData = new String[endRow - startRow + 1][endCol - startCol + 1];

			for (int i = startRow; i < endRow + 1; i++) {
				for (int j = startCol; j < endCol + 1; j++) {
					testData[i - startRow][j - startCol] = sheet.getRow(i).getCell(j).getStringCellValue();
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Nao pode carregar a planilha Excel");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Nao pode carregar a planilha Excel");
			e.printStackTrace();
		}

		return testData;

	}

	public static HSSFCell[] findCell(HSSFSheet sheet, String text) {

		String pos = "start";

		HSSFCell[] cells = new HSSFCell[2];

		for (Row row : sheet) {
			for (Cell cell : row) {
				if (text.equals(cell.getStringCellValue())) {
					if (pos.equalsIgnoreCase("start")) {
						cells[0] = (HSSFCell) cell;
						pos = "end";
					} else {
						cells[1] = (HSSFCell) cell;
					}
				}

			}
		}
		return cells;
	}
}