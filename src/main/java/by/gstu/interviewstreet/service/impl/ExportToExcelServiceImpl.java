package by.gstu.interviewstreet.service.impl;

import by.gstu.interviewstreet.bean.StatisticData;
import by.gstu.interviewstreet.domain.Interview;
import by.gstu.interviewstreet.service.ExportToExcelService;
import by.gstu.interviewstreet.service.StatisticsService;
import by.gstu.interviewstreet.util.DateUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ExportToExcelServiceImpl implements ExportToExcelService {

    private static final Logger LOG = LoggerFactory.getLogger(ExportToExcelServiceImpl.class);

    @Autowired
    StatisticsService statisticsService;

    @Override
    public XSSFWorkbook exportAllStatistics(Interview interview) throws IOException {
         /*NULL is correct value*/
        List<StatisticData> statistics = statisticsService.getInterviewStatistics(interview, null, null);
        try (XSSFWorkbook book = new XSSFWorkbook()) {
            XSSFSheet sheet = book.createSheet(DateUtils.YYYY_MM_DD_HH_MM_SS.format(DateUtils.getToday()));
            int rowNumber = 0;

            XSSFRow mainHeader = sheet.createRow(rowNumber++);
            XSSFCell cell = mainHeader.createCell(0);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue("Статистика по анкете \"" + interview.getName() + "\"");
            makeCellAutosizeAndBold(book, mainHeader);

            rowNumber++;

            for (StatisticData statistic : statistics) {
                /*Add question text*/
                XSSFRow row = sheet.createRow(rowNumber++);
                XSSFCell questionText = row.createCell(0);
                questionText.setCellType(Cell.CELL_TYPE_STRING);
                questionText.setCellValue("Вопрос: " + statistic.getQuestionText());

                XSSFRow tableHeader = sheet.createRow(rowNumber++);
                XSSFCell c1 = tableHeader.createCell(0);
                XSSFCell c2 = tableHeader.createCell(1);
                XSSFCell c3 = tableHeader.createCell(2);

                c1.setCellType(Cell.CELL_TYPE_STRING);
                c2.setCellType(Cell.CELL_TYPE_NUMERIC);
                c3.setCellType(Cell.CELL_TYPE_NUMERIC);

                c1.setCellValue("Ответы");
                c2.setCellValue("Ответило, чел");
                c3.setCellValue("Ответило, %");

                makeCellAutosizeAndBold(book, tableHeader);

                Map<String, Object[]> answerData = statistic.getAnswerData();

                for (String key : answerData.keySet()) {
                    XSSFRow answer = sheet.createRow(rowNumber++);
                    XSSFCell answerText = answer.createCell(0);
                    XSSFCell peopleResponded = answer.createCell(1);
                    XSSFCell percentResponded = answer.createCell(2);

                    answerText.setCellType(Cell.CELL_TYPE_STRING);
                    peopleResponded.setCellType(Cell.CELL_TYPE_NUMERIC);
                    percentResponded.setCellType(Cell.CELL_TYPE_NUMERIC);

                    answerText.setCellValue(key);
                    Object[] values = answerData.get(key);

                    peopleResponded.setCellValue(values[0].toString());
                    percentResponded.setCellValue(values[1].toString().replace(",", "."));
                }

                rowNumber++;
            }
            return book;
        }
    }

    private void makeCellAutosizeAndBold(Workbook wb, Row row) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);

        for (int i = 0; i < row.getLastCellNum(); i++) {
            row.getCell(i).setCellStyle(style);
            wb.getSheetAt(0).autoSizeColumn(i);
        }
    }

}
