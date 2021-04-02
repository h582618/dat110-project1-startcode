package ExcelService;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Models.Stand;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class StandGenerator {
    private static String path = "/Users/matia/Expo/Stands.xlsx";



    /*
    public static void main(String[] args)
            throws FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException {

        //XLSX
        List<Stand> stands = new ArrayList<Stand>();



        try (InputStream xlsx = new FileInputStream(path)) {

            Workbook wb = new XSSFWorkbook(xlsx);
            Iterator<Sheet> sheetItr = wb.sheetIterator();

            while(sheetItr.hasNext()){
                Iterator<Row> rowitr =sheetItr.next().iterator();
                while (rowitr.hasNext()) {
                    Row row = rowitr.next();
                    Cell id = row.getCell(0);
                    Cell tittel = row.getCell(2);
                    if(id != null && id.getCellTypeEnum() == CellType.STRING
                            && id.getStringCellValue().matches(".*\\d.*") &&
                            !id.getStringCellValue().equals("Nr.")
                            && !id.getStringCellValue().equals(" Sum:")){
                        //id.getStringCellValue().trim().replaceAll("[\n]{2,}", " ");
                        //System.out.println("ID : " + id + " Forklaring : " + tittel);
                        stands.add(new Stand(id.getStringCellValue(),tittel.getStringCellValue()));
                    }

                }
            }

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(
                    "http://localhost:8080/stands/addStands",
                    new StandWrapper(stands),
                    ResponseEntity.class);
        } catch (Exception e) {
            System.out.println(e);
        }
*/
    public void generateStands(InputStream xlsx) throws IOException {

        List<Stand> stands = new ArrayList<Stand>();

        Workbook wb = new XSSFWorkbook(xlsx);
        Iterator<Sheet> sheetItr = wb.sheetIterator();

        while(sheetItr.hasNext()){
            Iterator<Row> rowitr =sheetItr.next().iterator();
            while (rowitr.hasNext()) {
                Row row = rowitr.next();
                Cell id = row.getCell(0);
                Cell tittel = row.getCell(2);
                if(id != null && id.getCellTypeEnum() == CellType.STRING
                        && id.getStringCellValue().matches(".*\\d.*") &&
                        !id.getStringCellValue().equals("Nr.")
                        && !id.getStringCellValue().equals(" Sum:")){
                    stands.add(new Stand(id.getStringCellValue(),tittel.getStringCellValue()));
                }

            }
        }

     for(Stand x :stands){
         System.out.println(x.toString());
     }

     /*
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(
                "http://localhost:8080/stands/addStands",
                new StandWrapper(stands),
                ResponseEntity.class);

      */
    }
}
