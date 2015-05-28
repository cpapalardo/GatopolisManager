package br.com.farofa.gm.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import br.com.farofa.gm.model.Group;
import br.com.farofa.gm.model.Student;

public class ExcelUtil {
	private static int MAX_COLUMNS = 6;

	private static int STUDENT_NAME = 0;
	private static int BIRTHDAY = 1;
	private static int GENRE = 2;
	private static int GROUP_NAME = 3;
	private static int PERIOD = 4;
	private static int SERIE = 5;
	
	private static String FILE_NAME = "GatopolisManager.xls";
	private String path;
	
	private List<Student> students = new ArrayList<Student>();
	private List<Group> groups = new ArrayList<Group>();
	
	public ExcelUtil () {}

	public ExcelUtil (String path) {
		if (!path.contains(".xls")) {
			throw new RuntimeException ("O arquivo deve conter a extes�o .xls");
		}
		this.path = path;
	}
	
	public void write() throws IOException, WriteException {
		File file = new File(path + FILE_NAME);
		
		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setEncoding("Cp1252");
		WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
		workbook.createSheet("Manager", 0);
		WritableSheet excelSheet = workbook.getSheet(0);

		addCaption(excelSheet, 0, 0, "Nome Completo do Aluno");
		addCaption(excelSheet, 1, 0, "Data de Nascimento");
		addCaption(excelSheet, 2, 0, "Sexo");
		addCaption(excelSheet, 3, 0, "Nome da Turma");
		addCaption(excelSheet, 4, 0, "Periodo");
		addCaption(excelSheet, 5, 0, "Serie");

		workbook.write();
		workbook.close();
	}
	
	public void read(InputStream stream) throws IOException {
		//File inputWorkbook = file;//new File(path);
		Workbook w;
		try {
			w = Workbook.getWorkbook(stream);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines

			for (int i = 1; i < sheet.getRows(); i++) {
				Student student = new Student ();
				Group group = new Group ();
				
				for (int j = 0; j < MAX_COLUMNS; j++) {
					
					Cell cell = sheet.getCell(j, i);
					CellType type = cell.getType();
					
					//Preenchimento obrigat�rio
					if (type == CellType.EMPTY) {
						throw new RuntimeException ("Campo vazio na coluna = "+ (j+1) + ", linha = " + (i+1) + " de preenchimento obrigat�rio");
					}
					
					if (STUDENT_NAME == j) {
						student.setName(cell.getContents());
					}
					if (BIRTHDAY == j) {
						try {
							Date date = new SimpleDateFormat("dd/MM/yyyy").parse(cell.getContents());
							student.setBirth_date(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					if (GENRE == j) {
						//student.setGender(cell.getContents());
					}
					if (GROUP_NAME == j) {
						group.setName(cell.getContents());
					}
					if (PERIOD == j) {
						//group.setPeriod(cell.getContents());
					}
					if (SERIE == j) {
						group.setSerie(cell.getContents());
					}
				}
				students.add(student);
				groups.add(group);
			}
			
			
			//Resolvendo redundancia com referencias
			for (int i = 0; i < groups.size(); i++) {
				for (int j = groups.size() - 1; j > 0; j--) {
					if (compareGroup (groups.get(i), groups.get(j))) {
						groups.set(j, groups.get(i));
					}
				}
			}
			
			//Atribui os respectivos relacionamentos conforme a linha do excel
			for (int i = 0; i < groups.size(); i++) {
				//students.get(i).setStudent_group(groups.get(i));
			}
			
			//Ajusta Nome e Sobrenome
			for (Student student : students) {
				String completeName = student.getName();
				
				if (!completeName.contains(" ")) {
					student.setName(completeName);
				} else {
					student.setName(completeName.substring(0, completeName.indexOf(" ")));
				}
			}
			
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}
	
	private void addCaption(WritableSheet sheet, int column, int row, String s)
			throws RowsExceededException, WriteException {
		WritableFont font = new WritableFont(WritableFont.ARIAL);
		font.setBoldStyle(WritableFont.BOLD);
		WritableCellFormat cell = new WritableCellFormat(font);

		Label label = new Label(column, row, s, cell);
		sheet.addCell(label);
	}
	
	private boolean compareGroup (Group g1, Group g2) {
		if (g1.getName().trim().equals(g2.getName().trim()) && 
				g1.getPeriod().equals(g2.getPeriod()) && 
				g1.getSerie().trim().equals(g2.getSerie().trim())) {
			return true;
		}else{
			return false;
		}
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
}
